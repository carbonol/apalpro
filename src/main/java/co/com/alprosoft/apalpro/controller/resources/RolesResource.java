/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.controller.resources;

import co.com.alprosoft.apalpro.model.dao.RolesDAO;
import co.com.alprosoft.apalpro.model.dto.RolesDTO;
import co.com.alprosoft.apalpro.model.utils.CustomErrorMessages;
import co.com.alprosoft.apalpro.model.utils.EntityNames;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servidor de recursos REST asociado a los roles de usuario.
 * @author leand 
 */
@Path("roles")
public class RolesResource {
    
    @Context
    private UriInfo context;

    // Crea una nueva instancia de RolesResource
    public RolesResource() {
        
    }
    
    /**
     * Busca la representación completa de una instancia existente de un rol de usuario como recurso con base en el ID de rol dado, y la da al cliente.
     * @param id El id del rol de usuario.
     * @return una instancia de RolesDTO.
     */
    @GET
    @Path("{id}/get")
    @Produces(MediaType.APPLICATION_JSON)
    public RolesDTO getById(@PathParam("id") int id) {
        RolesDTO role = new RolesDTO(); // Objeto generado para TRANSFERIR DATOS desde la base de datos al usuario.
        RolesDAO rolesDAO = null; // Objeto de ACCESO DE DATOS a la base de datos.
        try {
            rolesDAO = new RolesDAO();
            role = rolesDAO.getById(id);
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingById(EntityNames.ROLES_SINGULAR_ENTITY_NAME) + ex);
        } finally {
            if (rolesDAO != null){
                rolesDAO.closeDBConnection();
            }            
        }
        return role;
    }
    
    /**
     * Busca y da la representación completa de todas las instancias existentes de roles de usuario como recurso al cliente.
     * @return un ArrayList de instancias de RolesDTO.
     */
    @GET
    @Path("get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RolesDTO> getAll() {
        ArrayList<RolesDTO> rolesList = new ArrayList<>();
        RolesDAO rolesDAO = null;
        try {
            rolesDAO = new RolesDAO();     
            rolesList = rolesDAO.getAll();
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingAll(EntityNames.ROLES_PLURAL_ENTITY_NAME));
        } finally {
            if (rolesDAO != null){
                rolesDAO.closeDBConnection();
            }            
        }
        return rolesList;
    }
    
    
    
}
