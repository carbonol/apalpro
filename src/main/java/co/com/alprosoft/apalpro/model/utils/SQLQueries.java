    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.utils;

import java.util.ArrayList;

/**
 *
 * @author leand
 */
public class SQLQueries {
    
    // Database table names
    private static final String ROLES_TABLE_NAME = "roles";
    private static final String USERS_TABLE_NAME = "users";
    public static final String EXERCISES_TABLE_NAME = "exercises";
    public static final String TEST_CASES_TABLE_NAME = "test_cases";
    private static final String ASSESSMENT_RESULTS_TABLE_NAME = "assessment_results";
    private static final String SOLUTION_PROPOSALS_TABLE_NAME = "solution_proposals";
    private static final String SOLUTION_PROPOSALS_TEST_CASE_ASSESSMENTS_TABLE_NAME = "solution_proposal_test_case_assessments";
    
    // Database column names
    // #### Database column names used in all tables
    private static final String ID_COLUMN_NAME = "id";
    private static final String CREATED_AT_COLUMN_NAME = "created_at";
    private static final String UPDATED_AT_COLUMN_NAME = "updated_at";
    private static final String STATUS_COLUMN_NAME = "status";
    
    // #### Database column names used in most tables (e.g., roles)
    public static final String NAME_COLUMN_NAME = "name";
    public static final String DESCRIPTION_COLUMN_NAME = "description";
    
    // #### Database column names used in specific tables
    // ## Usuarios (users)
    private static final String UNAME_COLUMN_NAME = "uname";
    private static final String PWD_COLUMN_NAME = "pwd";
    private static final String NAMES_COLUMN_NAME = "names";
    private static final String SURNAMES_COLUMN_NAME = "surnames";
    private static final String ROLE_ID_COLUMN_NAME = "role_id";
    
    // ## Casos de prueba (test_cases)
    public static final String EXERCISE_ID_COLUMN_NAME = "exercise_id";
    public static final String INPUT_DATA_COLUMN_NAME = "input_data";
    public static final String EXPECTED_OUTPUT_COLUMN_NAME = "expected_output";
    public static final String IS_VISIBLE_COLUMN_NAME = "is_visible";
    
    // #### Database column names set for inserting new registers of tables
    // ## Usuarios (users)
    private static final String[] INSERT_INTO_USERS_COLUMN_NAMES = {UNAME_COLUMN_NAME, PWD_COLUMN_NAME, NAMES_COLUMN_NAME, SURNAMES_COLUMN_NAME, ROLE_ID_COLUMN_NAME};
    
    // #### Database column names set for getting registers of tables
    // ## Casos de prueba (test_cases)
    private static final String[] GET_ALL_VISIBLE_EXERCISE_TEST_CASES_BY_ID_COLUMN_NAMES = {EXERCISE_ID_COLUMN_NAME, IS_VISIBLE_COLUMN_NAME};
    
    // SQL Queries for database operations
    // #### Roles de usuario (roles)
    /**
     * "SELECT * FROM roles WHERE id = ?;"
     */
    public static final String GET_ROLE_BY_ID_SQL_QUERY_STRING = generateGetByIdSQLQueryString(ROLES_TABLE_NAME, ID_COLUMN_NAME);
    public static final String GET_ALL_ROLES_SQL_QUERY_STRING = generateGetAllSQLQueryString(ROLES_TABLE_NAME);
    
    // #### Usuarios (users)
    public static final String CREATE_USER_SQL_QUERY_STRING = generateInsertIntoTableSQLQueryString(USERS_TABLE_NAME, INSERT_INTO_USERS_COLUMN_NAMES);
    public static final String GET_USER_BY_ID_SQL_QUERY_STRING = generateGetByIdSQLQueryString(USERS_TABLE_NAME, ID_COLUMN_NAME);
    public static final String GET_ALL_USERS_SQL_QUERY_STRING = generateGetAllSQLQueryString(USERS_TABLE_NAME);
    public static final String GET_LAST_INSERTED_ID_IN_TABLE_USERS_SQL_QUERY_STRING = 
            generateGetLastInsertedIdSQLQueryString(USERS_TABLE_NAME, ID_COLUMN_NAME);
    
    // #### Ejercicios (exercises)
    public static final String GET_EXERCISE_BY_ID_SQL_QUERY_STRING = generateGetByIdSQLQueryString(EXERCISES_TABLE_NAME, ID_COLUMN_NAME);
    public static final String GET_LAST_INSERTED_ID_IN_TABLE_EXERCISES_SQL_QUERY_STRING = 
            generateGetLastInsertedIdSQLQueryString(EXERCISES_TABLE_NAME, ID_COLUMN_NAME);
    
    // #### Casos de prueba (test_cases)
    public static final String GET_ALL_TEST_CASES_BY_EXERCISE_ID_SQL_QUERY_STRING = generateGetByIdSQLQueryString(TEST_CASES_TABLE_NAME, EXERCISE_ID_COLUMN_NAME);
    public static final String GET_ALL_VISIBLE_TEST_CASES_BY_EXERCISE_ID_SQL_QUERY_STRING = 
            generateGetByParametersSQLQueryString(TEST_CASES_TABLE_NAME, GET_ALL_VISIBLE_EXERCISE_TEST_CASES_BY_ID_COLUMN_NAMES);
    
    /**
      Obtiene un valor String para cualquier consulta SQL parametrizada simple de tipo INSERT INTO.
      Si no se especifica el nombre de la tabla y al menos 1 campo, entonces se devolverá null.
      En caso contrario, se devolverá el String que represente la consulta SQL para insertar un 
        registro en una tabla, según el nombre de tabla y las columnas de la misma dados e implicados en la inserción.
      El orden en que se insertarán los campos de la tabla será el mismo orden en que se colocan los
        nombres de las columnas de la tabla en el arreglo de String.
    */
    private static String generateInsertIntoTableSQLQueryString (String tableName, String[] columnNames){
        if (tableName != null && tableName.length() >= 1 
                && columnNames != null && columnNames.length >= 1){
            String sqlQueryString = "INSERT INTO " + tableName + " (";
            for (int i = 0; i < columnNames.length - 1; i++) {
                sqlQueryString += columnNames[i] + ", ";
            }
            sqlQueryString += columnNames[columnNames.length - 1] + ") VALUES (";
            for (int i = 0; i < columnNames.length - 1; i++) {
                sqlQueryString += "?, ";
            }
            sqlQueryString += "?);";
            return sqlQueryString;
        } else {
            return null;
        }
    }
    
    public static String generateInsertColumnsIntoTableSQLQueryString (String tableName, ArrayList<String> columnNames){
        if (tableName != null && tableName.length() >= 1 
                && columnNames != null && columnNames.size() >= 1){
            String sqlQueryString = "INSERT INTO " + tableName + " (";
            for (int i = 0; i < columnNames.size() - 1; i++) {
                sqlQueryString += columnNames.get(i) + ", ";
            }
            sqlQueryString += columnNames.get(columnNames.size() - 1) + ") VALUES (";
            for (int i = 0; i < columnNames.size() - 1; i++) {
                sqlQueryString += "?, ";
            }
            sqlQueryString += "?);";
            return sqlQueryString;
        } else {
            return null;
        }
    }
    
    /**
      Obtiene un valor String para cualquier consulta SQL parametrizada simple para obtener el último ID insertado en alguna tabla.
      Si no se especifica el nombre de la tabla y de la llave primaria de esa tabla, entonces se devolverá null.
      En caso contrario, se devolverá el String que represente la consulta SQL para obtener el último ID insertado en la tabla
        cuyo nombre fue dado, considerando, además, el nombre de llave primaria indicado para esta tabla.
    */
    private static String generateGetLastInsertedIdSQLQueryString (
            String tableName, String primaryKeyColumnName){
        if (tableName != null && tableName.length() >= 1){
            return "SELECT LAST_INSERT_ID(" + primaryKeyColumnName + ") FROM " 
                    + tableName + " ORDER BY LAST_INSERT_ID(" 
                    + primaryKeyColumnName + ") DESC LIMIT 1;";
        } else {
            return null;
        }        
    }
    
    /**
      Obtiene un valor String para cualquier consulta SQL parametrizada simple de tipo SELECT * FROM, 
        con la condición de que sólo se obtendrán los registros que coincidan en ID, según la llave primaria que use la tabla.
      Si no se especifica el nombre de la tabla, y el nombre de la llave primaria que usa dicha tabla,
        entonces se devolverá null.
      En caso contrario, se devolverá el String que represente la consulta SQL de acuerdo al nombre
        de tabla y al nombre de llave primaria dados.
    */
    private static String generateGetByIdSQLQueryString (String tableName, 
            String primaryKeyColumnName){
        if (tableName != null && tableName.length() >= 1 && 
                primaryKeyColumnName != null && primaryKeyColumnName.length() >= 1){
            return "SELECT * FROM " + tableName + " WHERE " + primaryKeyColumnName + " = ?;";
        } else {
            return null;
        }        
    }
    
    private static String generateGetByParametersSQLQueryString (String tableName, 
            String[] whereColumnNames){
        if (tableName != null && tableName.length() >= 1 && 
                whereColumnNames != null && whereColumnNames.length >= 1){
            String sqlQueryString = "SELECT * FROM " + tableName + " WHERE ";
            for (int i = 0; i < whereColumnNames.length - 1; i++) {
                sqlQueryString += whereColumnNames[i] + " = ? AND ";
            }
            sqlQueryString += whereColumnNames[whereColumnNames.length - 1] + " = ?;";
            return sqlQueryString;
        } else {
            return null;
        }        
    }
    
    /**
      Obtiene un valor String para cualquier consulta SQL parametrizada simple de tipo SELECT * FROM
        para obtener TODOS los registros de una tabla.
      Si no se especifica el nombre de la tabla, entonces se devolverá null.
      En caso contrario, se devolverá el String que represente la consulta SQL de acuerdo al nombre
        de tabla indicado.
    */
    private static String generateGetAllSQLQueryString (String tableName){
        if (tableName != null && tableName.length() >= 1){
            return "SELECT * FROM " + tableName + ";";
        } else {
            return null;
        }        
    }
    
}
