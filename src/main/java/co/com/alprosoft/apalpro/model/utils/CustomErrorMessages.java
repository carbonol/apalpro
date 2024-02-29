/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.utils;

/**
 *
 * @author leand
 */
public class CustomErrorMessages {
    /**

     * @return "Hubo una excepción a nivel de SQL al tratar de registrar un nuevo " + entityName + " a la base de datos: "
    **/
    public static String generateSQLExceptionErrorMessageWhenInsertingIntoTable (String singularEntityName){
        if (singularEntityName != null && singularEntityName.length() >= 1){
            return "Hubo una excepción a nivel de SQL al tratar de registrar un nuevo " + singularEntityName + " a la base de datos: ";
        } else {
            return null;
        }        
    }
    
    /**
    
     * @return "Hubo una excepción NO relacionada con SQL al tratar de registrar un nuevo " + entityName + " a la base de datos: "
    **/
    public static String generateUnrelatedToSQLExceptionErrorMessageWhenInsertingIntoTable (String singularEntityName){
        if (singularEntityName != null && singularEntityName.length() >= 1){
            return "Hubo una excepción NO relacionada con SQL al tratar de registrar un nuevo " + singularEntityName + " a la base de datos: ";
        } else {
            return null;
        }        
    }
    
    /**

     * @return "Hubo una excepción a nivel de SQL al tratar de obtener el último ID de " + entityName + " insertado en la base de datos: "
    **/
    public static String generateSQLExceptionErrorMessageWhenGettingLastInsertedID (String singularEntityName){
        if (singularEntityName != null && singularEntityName.length() >= 1){
            return "Hubo una excepción a nivel de SQL al tratar de obtener el último ID de " + singularEntityName + " insertado en la base de datos: ";
        } else {
            return null;
        }        
    }
    
    /**
    
     * @return "Hubo una excepción NO relacionada con SQL al tratar de obtener el último ID de " + entityName + " insertado en la base de datos: "
    **/
    public static String generateUnrelatedToSQLExceptionErrorMessageWhenGettingLastInsertedID (String singularEntityName){
        if (singularEntityName != null && singularEntityName.length() >= 1){
            return "Hubo una excepción NO relacionada con SQL al tratar de obtener el último ID de " + singularEntityName + " insertado en la base de datos: ";
        } else {
            return null;
        }        
    }
    
    /**
    
     * @return "Hubo una excepción a nivel de SQL al tratar de obtener un " + entityName + " por su identificador (id): "
    **/
    public static String generateSQLExceptionErrorMessageWhenGettingById (String singularEntityName){
        if (singularEntityName != null && singularEntityName.length() >= 1){
            return "Hubo una excepción a nivel de SQL al tratar de obtener un " + singularEntityName + " por su identificador (id): ";
        } else {
            return null;
        }        
    }
    
    /**
    
     * @return "Hubo una excepción NO relacionada con SQL al tratar de obtener un " + entityName + " por su identificador (id): "
    **/
    public static String generateUnrelatedToSQLExceptionErrorMessageWhenGettingById (String singularEntityName){
        if (singularEntityName != null && singularEntityName.length() >= 1){
            return "Hubo una excepción NO relacionada con SQL al tratar de obtener un " + singularEntityName + " por su identificador (id): ";
        } else {
            return null;
        }        
    }
    
    /**

     * @return "Hubo una excepción a nivel de SQL al tratar de obtener el listado de " + pluralEntityName + ": "
    **/
    public static String generateSQLExceptionErrorMessageWhenGettingAll (String pluralEntityName){
        if (pluralEntityName != null && pluralEntityName.length() >= 1){
            return "Hubo una excepción a nivel de SQL al tratar de obtener el listado de " + pluralEntityName + ": ";
        } else {
            return null;
        }        
    }
    
    /**
    
     * @return "Hubo una excepción NO relacionada con SQL al tratar de obtener el listado de " + pluralEntityName + ": "
    **/
    public static String generateUnrelatedToSQLExceptionErrorMessageWhenGettingAll (String pluralEntityName){
        if (pluralEntityName != null && pluralEntityName.length() >= 1){
            return "Hubo una excepción NO relacionada con SQL al tratar de obtener el listado de " + pluralEntityName + ": ";
        } else {
            return null;
        }        
    }
}
