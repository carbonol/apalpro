/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dao;

import co.com.alprosoft.apalpro.model.connection.DBConnection;
import co.com.alprosoft.apalpro.model.dto.ExercisesDTO;
import co.com.alprosoft.apalpro.model.dto.TestCasesDTO;
import co.com.alprosoft.apalpro.model.utils.CustomErrorException;
import co.com.alprosoft.apalpro.model.utils.CustomErrorMessages;
import co.com.alprosoft.apalpro.model.utils.EntityNames;
import co.com.alprosoft.apalpro.model.utils.SQLQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author leand
 */
public class ExercisesDAO implements GenericDAO<ExercisesDTO, Integer> {
    
    DBConnection cn;
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    public ExercisesDAO() throws SQLException {
        this.cn = new DBConnection();
    }
    
    public void closeDBConnection() {
        this.cn.closeConnection();
    }

    @Override
    public Integer create(ExercisesDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Integer createWithTestCases(ExercisesDTO dto) throws Exception {
        con = cn.getConnection();
        Integer idExercise = null;
        ArrayList<String> exerciseColumnNames = new ArrayList<>();
        // Paso 1: Validar la información obtenida en formato JSON.
        // 1.1. Se ha suministrado una representación válida JSON.
        if (dto == null) {
            throw new CustomErrorException("No se ha detectado ningún dato referente al ejercicio. Por favor, ingrese los campos del ejercicio y los de sus casos de prueba en el formato JSON adecuado.");
        }
        // 1.2. Se ha suministrado un nombre de ejercicio válido.
        if (dto.getName() == null || dto.getName().length() == 0) {
            throw new CustomErrorException("No se ha detectado ningún nombre de ejercicio. Por favor, corrija este campo usando el formato JSON adecuado.");
        } else {
            exerciseColumnNames.add(SQLQueries.NAME_COLUMN_NAME);
        }
        // La columna name de la tabla exercises es obligatoria, pero la columna description es opcional.
        if (dto.getDescription() != null) {
            if (dto.getDescription().length() > 0) {
                exerciseColumnNames.add(SQLQueries.DESCRIPTION_COLUMN_NAME); 
            }
        }
        // Paso 2: Registrar el ejercicio
        try {
            ps = con.prepareStatement(SQLQueries.generateInsertColumnsIntoTableSQLQueryString(
                    SQLQueries.EXERCISES_TABLE_NAME, exerciseColumnNames));
            ps.setString(1, dto.getName());
            // La columna name de la tabla exercises es obligatoria, pero la columna description es opcional.
            // https://www.geeksforgeeks.org/arraylist-contains-java/
            if (exerciseColumnNames.contains(SQLQueries.DESCRIPTION_COLUMN_NAME)) {
                ps.setString(2, dto.getDescription());
            }
            ps.executeUpdate();
        } catch (Exception ex) {
            String errorMessage;
            if (ex instanceof SQLException) {
                errorMessage = CustomErrorMessages.generateSQLExceptionErrorMessageWhenInsertingIntoTable(EntityNames.EXERCISES_SINGULAR_ENTITY_NAME) + ex;
            } else {
                errorMessage = CustomErrorMessages.generateUnrelatedToSQLExceptionErrorMessageWhenInsertingIntoTable(EntityNames.EXERCISES_SINGULAR_ENTITY_NAME) + ex;
            }
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
        // Paso 3: Obtener el ID de ejercicio generado a partir de la inserción
        // Note que aún NO SE HA HECHO COMMIT.
        try {
            ps = con.prepareStatement(SQLQueries.GET_LAST_INSERTED_ID_IN_TABLE_EXERCISES_SQL_QUERY_STRING);
            rs = ps.executeQuery();
            while (rs.next()) {
                idExercise = rs.getInt(1);
            }            
        } catch (Exception ex) {
            String errorMessage;
            if (ex instanceof SQLException) {
                errorMessage = CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingLastInsertedID(EntityNames.EXERCISES_SINGULAR_ENTITY_NAME) + ex;
            } else {
                errorMessage = CustomErrorMessages.generateUnrelatedToSQLExceptionErrorMessageWhenGettingLastInsertedID(EntityNames.EXERCISES_SINGULAR_ENTITY_NAME) + ex;
            }
            System.out.println(errorMessage);
            cn.rollbackBD();
            throw new Exception(errorMessage);
        }
        // Paso 4: Registrar los casos de prueba del ejercicio:
        // Usar el valor de idExercise para enlazar los casos de prueba con el ejercicio.
        try {
            if (dto.getTest_cases() != null && dto.getTest_cases().size() > 0) {
                for (int i = 0; i < dto.getTest_cases().size(); i++) {
                    // Para cada caso de prueba, revise qué columnas se han agregado en el formato JSON para los casos de prueba del ejercicio
                    ArrayList<String> testCaseColumnNames = new ArrayList<>();
                    // El uso de esta columna es obligatoria para cada caso de prueba, aunque su valor no sea dada por el usuario, sino que 
                    //  es generado automáticamente a nivel de base de datos mediante esta aplicación web.
                    testCaseColumnNames.add(SQLQueries.EXERCISE_ID_COLUMN_NAME);
                    // El uso de las demás columnas para registrar casos de prueba son opcionales.
                    // Por defecto, los casos de prueba no son visibles (is_visible = 0 o FALSE).
                    // En este caso, además, el campo is_visible para los casos de prueba (DTO) nunca es null (siempre tendrá algún valor: TRUE o FALSE, por definición).
                    // https://stackoverflow.com/questions/6226290/default-value-of-boolean-and-boolean-in-java
                    // The default value for a Boolean (object) is null.
                    // The default value for a boolean (primitive) is false.
                    if (dto.getTest_cases().get(i).getInput_data() != null) {
                        testCaseColumnNames.add(SQLQueries.INPUT_DATA_COLUMN_NAME);
                    }
                    if (dto.getTest_cases().get(i).getExpected_output() != null) {
                        testCaseColumnNames.add(SQLQueries.EXPECTED_OUTPUT_COLUMN_NAME);
                    }
                    testCaseColumnNames.add(SQLQueries.IS_VISIBLE_COLUMN_NAME);
                    ps = con.prepareStatement(SQLQueries.generateInsertColumnsIntoTableSQLQueryString(
                            SQLQueries.TEST_CASES_TABLE_NAME, testCaseColumnNames));
                    ps.setInt(1, idExercise);
                    int paramNumber = 2;
                    if (testCaseColumnNames.contains(SQLQueries.INPUT_DATA_COLUMN_NAME)) {
                        ps.setString(paramNumber, dto.getTest_cases().get(i).getInput_data());
                        paramNumber++;
                    }
                    if (testCaseColumnNames.contains(SQLQueries.EXPECTED_OUTPUT_COLUMN_NAME)) {
                        ps.setString(paramNumber, dto.getTest_cases().get(i).getExpected_output());
                        paramNumber++;
                    }
                    ps.setBoolean(paramNumber, dto.getTest_cases().get(i).isIs_visible());
                    ps.executeUpdate();
                }                
            } else {
                // No haga nada. Siga al siguiente paso.
            }
            // Paso 5: Operación terminada: Hacer commit.
            cn.commitBD();
        } catch (Exception ex) {
            String errorMessage;
            if (ex instanceof SQLException) {
                errorMessage = "Hubo un error a nivel de bases de datos al tratar de registrar un nuevo caso de prueba dentro de un ejercicio: " + ex;
            } else {
                errorMessage = "Hubo un error NO relacionado con la base de datos al tratar de registrar un nuevo caso de prueba dentro de un ejercicio: " + ex;
            }
            System.out.println(errorMessage);
            cn.rollbackBD();
            throw new Exception(errorMessage);
        }
        return idExercise; // La idea de hacer esto es poder devolver la representación en JSON de la ruta con sus escalas, haciendo una consulta con este id.
    }

    @Override
    public ExercisesDTO getById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ExercisesDTO getByIdWithTestCases (Integer id) throws Exception {
        con = cn.getConnection();
        ExercisesDTO exercise = new ExercisesDTO();
        try {
            // Paso 1: Obtener la información del ejercicio cuyo id sea el que fue solicitado por el cliente.
            ps = con.prepareStatement(SQLQueries.GET_EXERCISE_BY_ID_SQL_QUERY_STRING);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                exercise.setId(rs.getInt(1));
                exercise.setCreated_at(rs.getString(2));
                exercise.setUpdated_at(rs.getString(3));
                exercise.setStatus(rs.getBoolean(4));
                exercise.setName(rs.getString(5));
                exercise.setDescription(rs.getString(6));
            }
            // Paso 2: Obtener la información de los casos de prueba del ejercicio cuyo exercise_id sea el que fue solicitado por el cliente.
            ps = con.prepareStatement(SQLQueries.GET_ALL_TEST_CASES_BY_EXERCISE_ID_SQL_QUERY_STRING);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            ArrayList<TestCasesDTO> exerciseTestCases = new ArrayList<>();
            TestCasesDTO testCase;
            while (rs.next()) {
                testCase = new TestCasesDTO();
                testCase.setId(rs.getInt(1));
                testCase.setCreated_at(rs.getString(2));
                testCase.setUpdated_at(rs.getString(3));
                testCase.setStatus(rs.getBoolean(4));
                testCase.setExercise_id(rs.getInt(5));
                testCase.setInput_data(rs.getString(6));
                testCase.setExpected_output(rs.getString(7));
                testCase.setIs_visible(rs.getBoolean(8));
                exerciseTestCases.add(testCase);
            }
            exercise.setTest_cases(exerciseTestCases);
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo un error a nivel de bases de datos al tratar de obtener un ejercicio con sus casos de prueba, según el ID del ejercicio: " + ex;
            } else {
                mensajeError = "Hubo un error NO relacionado con la base de datos al tratar de obtener un ejercicio con sus casos de prueba, según el ID del ejercicio: " + ex;
            }
            System.out.println(mensajeError);
            throw new Exception(mensajeError);
        }
        return exercise;
    }
    
    public ExercisesDTO getByIdWithVisibleTestCases (Integer id) throws Exception {
        con = cn.getConnection();
        ExercisesDTO exercise = new ExercisesDTO();
        try {
            // Paso 1: Obtener la información del ejercicio cuyo id sea el que fue solicitado por el cliente.
            ps = con.prepareStatement(SQLQueries.GET_EXERCISE_BY_ID_SQL_QUERY_STRING);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                exercise.setId(rs.getInt(1));
                exercise.setCreated_at(rs.getString(2));
                exercise.setUpdated_at(rs.getString(3));
                exercise.setStatus(rs.getBoolean(4));
                exercise.setName(rs.getString(5));
                exercise.setDescription(rs.getString(6));
            }
            // Paso 2: Obtener la información de los casos de prueba del ejercicio cuyo exercise_id sea el que fue solicitado por el cliente.
            ps = con.prepareStatement(SQLQueries.GET_ALL_VISIBLE_TEST_CASES_BY_EXERCISE_ID_SQL_QUERY_STRING);
            ps.setInt(1, id);
            ps.setBoolean(2, true); // Para obtener sólo los casos de prueba que son visibles para los estudiantes.
            rs = ps.executeQuery();
            ArrayList<TestCasesDTO> exerciseTestCases = new ArrayList<>();
            TestCasesDTO testCase;
            while (rs.next()) {
                testCase = new TestCasesDTO();
                testCase.setId(rs.getInt(1));
                testCase.setCreated_at(rs.getString(2));
                testCase.setUpdated_at(rs.getString(3));
                testCase.setStatus(rs.getBoolean(4));
                testCase.setExercise_id(rs.getInt(5));
                testCase.setInput_data(rs.getString(6));
                testCase.setExpected_output(rs.getString(7));
                testCase.setIs_visible(rs.getBoolean(8));
                exerciseTestCases.add(testCase);
            }
            exercise.setTest_cases(exerciseTestCases);
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo un error a nivel de bases de datos al tratar de obtener un ejercicio con sus casos de prueba visibles por estudiantes, según el ID del ejercicio: " + ex;
            } else {
                mensajeError = "Hubo un error NO relacionado con la base de datos al tratar de obtener un ejercicio con sus casos de prueba visibles por estudiantes, según el ID del ejercicio: " + ex;
            }
            System.out.println(mensajeError);
            throw new Exception(mensajeError);
        }
        return exercise;
    }

    @Override
    public ArrayList<ExercisesDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ExercisesDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean disableById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean enableById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
