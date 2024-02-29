/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dao;

import co.com.alprosoft.apalpro.model.connection.DBConnection;
import co.com.alprosoft.apalpro.model.dto.UsersDTO;
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
public class UsersDAO implements GenericDAO<UsersDTO, Integer> {
    
    DBConnection cn;
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    public UsersDAO() throws SQLException {
        this.cn = new DBConnection();
    }
    
    public void closeDBConnection() {
        this.cn.closeConnection();
    }

    @Override
    public Integer create(UsersDTO dto) {
        con = cn.getConnection();
        Integer idUser = null;
        // Registrar (crear) nuevo usuario en la base de datos
        try {
            ps = con.prepareStatement(SQLQueries.CREATE_USER_SQL_QUERY_STRING);
            ps.setString(1, dto.getUname());
            ps.setString(2, dto.getPwd());
            ps.setString(3, dto.getNames());
            ps.setString(4, dto.getSurnames());
            ps.setInt(5, dto.getRole_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.
                    generateSQLExceptionErrorMessageWhenInsertingIntoTable(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
            return idUser;
        } catch (Exception ex) {
            System.out.println(CustomErrorMessages.
                    generateUnrelatedToSQLExceptionErrorMessageWhenInsertingIntoTable(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
            return idUser;
        }
        // Obtener el último ID insertado en la tabla users (usuarios) de la base de datos
        try {
            ps = con.prepareStatement(SQLQueries.GET_LAST_INSERTED_ID_IN_TABLE_USERS_SQL_QUERY_STRING);
            rs = ps.executeQuery();
            while (rs.next()) {
                idUser = rs.getInt(1);
            }
            cn.commitBD(); // Confirmar cambios realizados en esta transacción, en la base de datos. 
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.
                    generateSQLExceptionErrorMessageWhenGettingLastInsertedID(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
            cn.rollbackBD(); // Si hubiere un problema durante este proceso, y hubo cambios registrados pero no confirmados a nivel de base de datos, en esta transacción,
            // rechazar estos cambios, haciendo que no se actualice la base de datos.
        } catch (Exception ex) {
            System.out.println(CustomErrorMessages.
                    generateUnrelatedToSQLExceptionErrorMessageWhenGettingLastInsertedID(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
            cn.rollbackBD(); // Si hubiere un problema durante este proceso, y hubo cambios registrados pero no confirmados a nivel de base de datos, en esta transacción,
            // rechazar estos cambios, haciendo que no se actualice la base de datos.
        }
        return idUser;
    }

    @Override
    public UsersDTO getById(Integer id) {
        con = cn.getConnection();
        UsersDTO user = new UsersDTO(); // El objeto usado para transferir datos de la base de datos al usuario.
        try {
            ps = con.prepareStatement(SQLQueries.GET_USER_BY_ID_SQL_QUERY_STRING);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setCreated_at(rs.getString(2));
                user.setUpdated_at(rs.getString(3));
                user.setStatus(rs.getBoolean(4));
                user.setUname(rs.getString(5));
                user.setPwd(rs.getString(6));
                user.setNames(rs.getString(7));
                user.setSurnames(rs.getString(8));
                user.setRole_id(rs.getInt(9));
            }            
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.
                    generateSQLExceptionErrorMessageWhenGettingById(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
        } catch (Exception ex) {
            System.out.println(CustomErrorMessages.
                    generateUnrelatedToSQLExceptionErrorMessageWhenGettingById(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
        }
        return user;
    }

    @Override
    public ArrayList<UsersDTO> getAll() {
        con = cn.getConnection();
        ArrayList<UsersDTO> usersList = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQLQueries.GET_ALL_USERS_SQL_QUERY_STRING);
            rs = ps.executeQuery();
            while (rs.next()) {
                UsersDTO user = new UsersDTO();
                user.setId(rs.getInt(1));
                user.setCreated_at(rs.getString(2));
                user.setUpdated_at(rs.getString(3));
                user.setStatus(rs.getBoolean(4));
                user.setUname(rs.getString(5));
                user.setPwd(rs.getString(6));
                user.setNames(rs.getString(7));
                user.setSurnames(rs.getString(8));
                user.setRole_id(rs.getInt(9));
                usersList.add(user);
            }            
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingAll(EntityNames.USERS_PLURAL_ENTITY_NAME) + ex);
        } catch (Exception ex) {
            System.out.println(CustomErrorMessages.generateUnrelatedToSQLExceptionErrorMessageWhenGettingAll(EntityNames.USERS_PLURAL_ENTITY_NAME) + ex);
        }
        return usersList;
    }

    @Override
    public boolean update(UsersDTO dto) throws Exception {
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
