/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dao;

import co.com.alprosoft.apalpro.model.connection.DBConnection;
import co.com.alprosoft.apalpro.model.dto.SolutionProposalsDTO;
import co.com.alprosoft.apalpro.model.dto.TestCasesDTO;
import co.com.alprosoft.apalpro.model.utils.AutomaticAssessment;
import co.com.alprosoft.apalpro.model.utils.CustomErrorException;
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
public class SolutionProposalsDAO implements GenericDAO<SolutionProposalsDTO, Integer> {
    
    DBConnection cn;
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;

    public SolutionProposalsDAO() throws SQLException {
        this.cn = new DBConnection();
    }
    
    public void closeDBConnection() {
        this.cn.closeConnection();
    }

    @Override
    public Integer create(SolutionProposalsDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /*
    public Integer createWithTestCaseAssessments(SolutionProposalsDTO dto) throws Exception {
        con = cn.getConnection();
        Integer idSolutionProposal = null;
        // Paso 1: Validar información obtenida en formato JSON.
        // 1.1. Se ha suministrado una representación válida JSON.
        if (dto == null) {
            throw new CustomErrorException("No se ha detectado ningún dato referente a la propuesta de solución. Por favor, ingrese los campos de la propuesta de solución en el formato JSON adecuado.");
        }
        // 1.2. Se ha suministrado un id de usuario (user_id diferente de 0)
        if (dto.getUser_id() == 0){
            throw new CustomErrorException("No se ha detectado ningún id de usuario en el formato JSON. Por favor, corrija este campo usando el formato JSON adecuado.");
        }
        // 1.3. Se ha suministrado un id de usuario existente en la base de datos para almacenarlo como el usuario que hizo la propuesta de solución.
        // (El número en user_id coincide con un registro existente en base de datos)
        try {
            ps = con.prepareStatement(SQLQueries.GET_USER_BY_ID_SQL_QUERY_STRING);
            ps.setInt(1, dto.getUser_id());
            rs = ps.executeQuery();
            if (!rs.next()) {
                // Se encontró que el ID suministrado NO tiene correspondencia con un usuario existente en la base de datos.
                throw new CustomErrorException("No se encontró el id de usuario dado, en la base de datos. Por favor, verifique este campo.");
            } else {
                // Se encontró que el ID suministrado tiene corresponencia con un usuario existente en la base de datos.
            }            
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo una excepción a nivel de SQL al tratar de verificar si el id de usuario se encontraba en la base de datos: " + ex;
            } else if (ex instanceof CustomErrorException) {
                mensajeError = "" + ex;
            } else {
                mensajeError = "Hubo una excepción NO relacionada con SQL al tratar de verificar si el id de usuario se encontraba en la base de datos: " + ex;
            }
            System.out.println(mensajeError);
            throw new Exception(mensajeError);
        }               
        // 1.4. Se ha suministrado un id de ejercicio (exercise_id diferente de 0)
        if (dto.getExercise_id() == 0){
            throw new CustomErrorException("No se ha detectado ningún id de ejercicio en el formato JSON. Por favor, verifique este campo.");
        }
        // 1.5. Se ha suministrado un id de ejercicio existente en la base de datos para buscar los casos de prueba que evaluarán la propuesta de solución.
        // (El número en exercise_id coincide con un registro existente en base de datos)
        try {
            ps = con.prepareStatement(SQLQueries.GET_EXERCISE_BY_ID_SQL_QUERY_STRING);
            ps.setInt(1, dto.getExercise_id());
            rs = ps.executeQuery();
            if (!rs.next()) {
                // Se encontró que el ID suministrado NO tiene corresponencia con un ejercicio en la base de datos.
                throw new CustomErrorException("No se encontró el id de ejercicio dado, en la base de datos. Por favor, verifique este campo.");
            } else {
                // Se encontró que el ID suministrado tiene corresponencia con un ejercicio en la base de datos.
            }
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo una excepción a nivel de SQL al tratar de verificar si el id de ejercicio se encontraba en la base de datos: " + ex;
            } else if (ex instanceof CustomErrorException) {
                mensajeError = "" + ex;
            } else {
                mensajeError = "Hubo una excepción NO relacionada con SQL al tratar de verificar si el id de ejercicio se encontraba en la base de datos: " + ex;
            }
            System.out.println(mensajeError);
            throw new Exception(mensajeError);
        }
        
        // Paso 2: Obtener los datos de los casos de prueba que han sido asignados para el ejercicio a evaluar, mediante el exercise_id.
        try {
            ps = con.prepareStatement(SQLQueries.GET_ALL_TEST_CASES_BY_EXERCISE_ID_SQL_QUERY_STRING);
            ps.setInt(1, dto.getExercise_id());
            rs = ps.executeQuery();
            if (!rs.next()) {
                // Se encontró que el ejercicio NO tiene casos de prueba a ser evaluados en un intérprete de Python 3 indicado por esta aplicación web.
                throw new CustomErrorException("No se encontraron casos de prueba para el id de ejercicio dado, en la base de datos. "
                        + "Por lo tanto, no se realizará ninguna evaluación automática. "
                        + "Por favor, pida o asegúrese que se agreguen casos de prueba al ejercicio antes de hacer una evaluación automática respecto al mismo.");
            } else {
                // Se encontró que el ejercicio tiene casos de prueba a ser evaluados en un intérprete de Python 3 indicado por esta aplicación web.
                // Paso 3: Verificar que un directorio esté definido en el servidor para procesar archivos .py y evaluar los casos de prueba correspondientes (con estos archivos).
                if (!AutomaticAssessment.assessmentMainDirectoryExists()) {
                    // Se debe crear el directorio principal de evaluación
                    AutomaticAssessment.createAssessmentMainDirectory();
                } else {
                    // No se hace nada más al respecto
                }
                
                // Paso 4                
                // Verificar que un directorio esté definido en el servidor para procesar los archivos .py QUE ENVÍE UN USUARIO EN PARTICULAR 
                //    y evaluar los casos de prueba correspondientes, con estos archivos. 
                // Esto es IMPORTANTE porque existen diferentes usuarios en el sistema que interactúan en ella al mismo tiempo.
                // Por lo tanto, se debe crear un directorio por usuario, para después colocar el archivo .py a evaluar en este directorio. 
                
                if (!AutomaticAssessment.assessmentUserDirectoryExists(dto.getUser_id())) {
                    // Se debe crear el directorio de evaluación para el usuario que solicitó el cliente, según el id de usuario
                    AutomaticAssessment.createAssessmentUserDirectory(dto.getUser_id());
                } else {
                    // Se debe cancelar la evaluación, diciendo que ya hay una evaluación en proceso.
                    // Este directorio debería eliminarse una vez terminada la evaluación.
                    // Incluso, a futuro, un usuario podría pedir esto también, para eliminar su propia carpeta...
                    throw new CustomErrorException("El directorio para las evaluaciones automáticas del usuario con id " + dto.getUser_id() + " ya existe. " 
                            + "Por lo tanto, se asume que ya hay una evaluación automática en proceso para una propuesta de solución. " 
                            + "Por favor espere que termine dicha evaluación, o, si el problema persiste, comuníquese con el administrador para que el directorio " 
                            + "anteriormente mencionado sea eliminado en el servidor.");
                }
                
                // Paso 5: Crear el archivo .py que corresponda al código en Python 3 enviado por el cliente (e.g., estudiante) al servidor,
                //  en el directorio de evaluación del usuario que indicó el cliente, teniendo en cuenta también el directorio principal de evaluaciones definido para ello.
                AutomaticAssessment.createPython3File(dto.getCode(), dto.getUser_id());
                
                // Paso 6: Ejecutar los n casos de prueba del ejercicio
                // El programa .py se ejecutará n veces en el servidor, pero cada vez, con datos de entrada diferentes, según el caso de prueba a evaluar.
                
                // NOTA: En este punto, ya se tiene referencia del primer registro de caso de prueba a ser evaluado.
                TestCasesDTO testCase = new TestCasesDTO();
                testCase.setId(rs.getInt(1));
                // testCase.setCreated_at(rs.getString(2));
                // testCase.setUpdated_at(rs.getString(3));
                // testCase.setStatus(rs.getBoolean(4));
                testCase.setExercise_id(rs.getInt(5));
                testCase.setInput_data(rs.getString(6));
                testCase.setExpected_output(rs.getString(7));
                testCase.setIs_visible(rs.getBoolean(8));
                
                // Paso 7: Tomar los datos de entrada (texto) del caso de prueba
                String input_text = testCase.getInput_data();
                
                // Paso 8: Ejecutar el programa .py a evaluar con los datos de entrada (texto) del caso de prueba dado.
                
                
            }
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo una excepción a nivel de SQL al tratar de verificar si el id de ejercicio se encontraba en la base de datos: " + ex;
            } else if (ex instanceof CustomErrorException) {
                mensajeError = "" + ex;
            } else {
                mensajeError = "Hubo una excepción NO relacionada con SQL al tratar de verificar si el id de ejercicio se encontraba en la base de datos: " + ex;
            }
            System.out.println(mensajeError);
            throw new Exception(mensajeError);
        }
        
        // Paso ?: Registrar la propuesta de solución
        try {
            ps = con.prepareStatement(SQLQueries.REGISTRAR_RUTA_SQL);
            // Ninguno de los campos de la ruta es opcional.
            ps.setInt(1, dto.getIdAeropuertoOrigen());
            ps.setInt(2, dto.getIdAeropuertoDestino());
            ps.setString(3, new Timestamp(System.currentTimeMillis()).toString());
            // En este momento no se puede habilitar la ruta, puesto que no se han registrado sus escalas ni sus vuelos.
            ps.setBoolean(4, false);
            ps.executeUpdate();
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo una excepción a nivel de SQL al tratar de registrar una nueva ruta: " + ex;
            } else {
                mensajeError = "Hubo una excepción NO relacionada con SQL al tratar de registrar una nueva ruta: " + ex;
            }
            System.out.println(mensajeError);
            throw new Exception(mensajeError);
        }
        // Paso 3: Obtener el ID de ruta generado a partir de la inserción
        // Note que aún NO SE HA HECHO COMMIT.
        try {
            ps = con.prepareStatement(ConsultasSQL.OBTENER_ULTIMO_ID_INSERTADO_RUTAS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                idRuta = rs.getInt(1);
            }
            
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo un error a nivel de bases de datos al tratar de obtener el último ID de ruta insertado: " + ex;
            } else {
                mensajeError = "Hubo un error NO relacionado con la base de datos al tratar de obtener el último ID de ruta insertado: " + ex;
            }
            System.out.println(mensajeError);
            cn.rollbackBD();
            throw new Exception(mensajeError);
        }
        // Paso 4: Registrar las escalas de la ruta:
        // Usar el valor de idRuta para enlazar las escalas con la ruta
        try {
            if (dto.getEscalas() != null && dto.getEscalas().size() > 0) {
                for (int i = 0; i < dto.getEscalas().size(); i++) {
                    ps = con.prepareStatement(ConsultasSQL.REGISTRAR_ESCALA_SQL);
                    // Ninguno de los campos de la escala es opcional.
                    ps.setInt(1, idRuta);
                    ps.setInt(2, i+1); // Los números de las escalas (su orden) van registradas así: 1, 2, ..., n.
                    ps.setInt(3, dto.getEscalas().get(i).getIdAeropuerto());
                    ps.setString(4, new Timestamp(System.currentTimeMillis()).toString());
                    // Si una escala tiene estado_registro = true, este será visible en el frontend.
                    ps.setBoolean(5, true);
                    ps.executeUpdate();
                }                
            } else {
                // No haga nada. Siga al siguiente paso.
            }
            // Paso 5: Operación terminada: Hacer commit.
            cn.commitBD();            
        } catch (Exception ex) {
            String mensajeError;
            if (ex instanceof SQLException) {
                mensajeError = "Hubo un error a nivel de bases de datos al tratar de registrar una nueva escala dentro de una ruta: " + ex;
            } else {
                mensajeError = "Hubo un error NO relacionado con la base de datos al tratar de registrar una nueva escala dentro de una ruta: " + ex;
            }
            System.out.println(mensajeError);
            cn.rollbackBD();
            throw new Exception(mensajeError);
        }
        return idRuta; // La idea de hacer esto es poder devolver la representación en JSON de la ruta con sus escalas, haciendo una consulta con este id.
    }
    */
    @Override
    public SolutionProposalsDTO getById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SolutionProposalsDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(SolutionProposalsDTO dto) throws Exception {
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
