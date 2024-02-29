/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author leand
 */
public class DBConnection {
    private String driverDB;
    private String url;
    private String db;
    private String host;
    private String username;
    private String password;
    
    public Connection con = null;
    
    private boolean local;

    public DBConnection() {
        local = true; // Por ahora: NO CAMBIAR ESTO.
        driverDB = "com.mysql.cj.jdbc.Driver";
        if (local) { // Si se está ejecutando esta aplicación web sobre un servidor local.
            // Cambiar los valores siguientes si se altera la configuración de base de datos MySQL a emplear.
            host =  "localhost:3306";
            db = "apalpro";
            /*
            El parámetro useSSL=false era importante para evitar el siguiente error en Glassfish 5.0:
            error java.sql.SQLNonTransientConnectionException: 
            Cannot open file:C:\Users\leand\GlassFish_Server\glassfish\domains\domain1/config/keystore.jks 
            [Keystore was tampered with, or password was incorrect]|#]
            Dado que Payara Server reemplazó a Glassfish en materia de soporte comercial, es muy posible que este
            parámetro aún se requiera para evitar un error parecido al anterior.
            Así que es mejor dejarlo ahí por si acaso.
            */            
            url = "jdbc:mysql://" + host + "/" + db + "?useSSL=false";            
            // Nota a tener en cuenta: En el peor caso, reiniciar el servicio de MySQL80 en Windows.
            // (Por ejemplo, cuando se presentare algo similar a un rollbackException)
            username = "root"; 
            password = "mysql";
            //
        } else {
            // TODO: Colocar la configuración del servidor de bases de datos MySQL en producción.
        }
        try {
            Class.forName(driverDB); // Asignación del driver de base de datos MySQL
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontró la clase " + driverDB);
        }
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // También se puede indicar este parámetro como 8
            con.setAutoCommit(false); // Esto es importante cuando se necesita hacer el commit de una consulta SQL que modifica datos en un momento específico.
        } catch (SQLException ex) {
            System.out.println("Hubo una excepción a nivel de SQL al tratar de conectarse a la base de datos MySQL: " + ex);
        }
        System.out.println("Conectado a la base de datos " + db);
    }
    
    /* Retornar la conexión */
    public Connection getConnection(){
        return con;
    }
    
    /* Cerrar la conexión */
    public void closeConnection(){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Hubo una excepción a nivel de SQL al tratar de cerrar la conexión: " + ex);
            }
        }
    }
    
    public boolean setAutoCommitBD(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException ex) {
            System.out.println("Hubo una excepción a nivel de SQL al tratar de configurar el auto commit para la conexión a la base de datos MySQL: " + ex);
            return false;
        }
        return true;
    }
    
    public boolean commitBD() {
        try {
            con.commit();
        } catch (SQLException ex) {
            System.out.println("Hubo una excepción a nivel de SQL al tratar de hacer commit a una transacción: " + ex);
            return false;
        }
        return true;
    }
    
    public boolean rollbackBD() {
        try {
            con.rollback();
        } catch (SQLException ex) {
            System.out.println("Hubo una excepción a nivel de SQL al tratar de hacer rollback a una transacción: " + ex);
            return false;
        }
        return true;
    }
}
