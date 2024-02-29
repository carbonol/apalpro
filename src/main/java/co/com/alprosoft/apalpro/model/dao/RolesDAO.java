/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dao;

import co.com.alprosoft.apalpro.model.connection.DBConnection;
import co.com.alprosoft.apalpro.model.dto.RolesDTO;
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
public class RolesDAO implements GenericDAO<RolesDTO, Integer> {
    
    DBConnection cn;
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;

    public RolesDAO() throws SQLException {
        this.cn = new DBConnection();
    }
    
    public void closeDBConnection() {
        this.cn.closeConnection();
    }

    @Override
    public Integer create(RolesDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RolesDTO getById(Integer id) {
        con = cn.getConnection();
        RolesDTO role = new RolesDTO(); // El objeto usado para transferir datos de la base de datos al usuario.
        try {
            ps = con.prepareStatement(SQLQueries.GET_ROLE_BY_ID_SQL_QUERY_STRING);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                role.setId(rs.getInt(1));
                role.setCreated_at(rs.getString(2));
                role.setUpdated_at(rs.getString(3));
                role.setStatus(rs.getBoolean(4));
                role.setName(rs.getString(5));
                role.setDescription(rs.getString(6));
            }            
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.
                    generateSQLExceptionErrorMessageWhenGettingById(EntityNames.ROLES_SINGULAR_ENTITY_NAME) + ex);
        } catch (Exception ex) {
            System.out.println(CustomErrorMessages.
                    generateUnrelatedToSQLExceptionErrorMessageWhenGettingById(EntityNames.ROLES_SINGULAR_ENTITY_NAME) + ex);
        }
        return role;
    }

    @Override
    public ArrayList<RolesDTO> getAll() {
        con = cn.getConnection();
        ArrayList<RolesDTO> rolesList = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQLQueries.GET_ALL_ROLES_SQL_QUERY_STRING);
            rs = ps.executeQuery();
            while (rs.next()) {
                RolesDTO role = new RolesDTO();
                role.setId(rs.getInt(1));
                role.setCreated_at(rs.getString(2));
                role.setUpdated_at(rs.getString(3));
                role.setStatus(rs.getBoolean(4));
                role.setName(rs.getString(5));
                role.setDescription(rs.getString(6));
                rolesList.add(role);
            }            
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingAll(EntityNames.ROLES_PLURAL_ENTITY_NAME) + ex);
        } catch (Exception ex) {
            System.out.println(CustomErrorMessages.generateUnrelatedToSQLExceptionErrorMessageWhenGettingAll(EntityNames.ROLES_PLURAL_ENTITY_NAME) + ex);
        }
        return rolesList;
    }

    @Override
    public boolean update(RolesDTO dto) throws Exception {
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
