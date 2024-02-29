/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.controller.resources;

import co.com.alprosoft.apalpro.model.dao.UsersDAO;
import co.com.alprosoft.apalpro.model.dto.UsersDTO;
import co.com.alprosoft.apalpro.model.utils.CustomErrorMessages;
import co.com.alprosoft.apalpro.model.utils.EntityNames;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servidor de recursos REST asociado a los usuarios.
 * @author leand
 */
@Path("users")
public class UsersResource {
    
    @Context
    private UriInfo context;
    
    // Crea una nueva instancia de UsersResource
    public UsersResource() {
        
    }
    
    /**
     * Registra una nueva instancia de usuario como recurso en la base de datos.
     * @param newUser El objeto UsersDTO resultante del envío, por parte del cliente, de un String JSON con los datos necesarios para crear un nuevo usuario
     *  en la base de datos: uname, pwd, names, surnames y role_id.
     * @return una instancia de UsersDTO.
     */
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UsersDTO create(UsersDTO newUser) {
        UsersDTO user = new UsersDTO();
        UsersDAO usersDAO = null;
        try {
            usersDAO = new UsersDAO();
            Integer idUser = usersDAO.create(newUser);          
            user = usersDAO.getById(idUser);            
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.
                    generateSQLExceptionErrorMessageWhenInsertingIntoTable(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
        } finally {
            if (usersDAO != null){
                usersDAO.closeDBConnection();
            }            
        }
        return user;
    }
    
    /**
     * Busca la representación completa de una instancia existente de un usuario como recurso con base en el ID de usuario dado, y la da al cliente.
     * @param id El id del usuario.
     * @return una instancia de UsersDTO.
     */
    @GET
    @Path("{id}/get")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersDTO getById(@PathParam("id") int id) {
        UsersDTO user = new UsersDTO(); // Objeto generado para TRANSFERIR DATOS desde la base de datos al usuario.
        UsersDAO usersDAO = null; // Objeto de ACCESO DE DATOS a la base de datos.
        try {
            usersDAO = new UsersDAO();
            user = usersDAO.getById(id);
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingById(EntityNames.USERS_SINGULAR_ENTITY_NAME) + ex);
        } finally {
            if (usersDAO != null){
                usersDAO.closeDBConnection();
            }            
        }
        return user;
    }
    
    /**
     * Busca y da la representación completa de todas las instancias existentes de usuario como recurso al cliente.
     * @return un ArrayList de instancias de UsersDTO.
     */
    @GET
    @Path("get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UsersDTO> getAll() {
        ArrayList<UsersDTO> usersList = new ArrayList<>();
        UsersDAO usersDAO = null;
        try {
            usersDAO = new UsersDAO();     
            usersList = usersDAO.getAll();
        } catch (SQLException ex) {
            System.out.println(CustomErrorMessages.generateSQLExceptionErrorMessageWhenGettingAll(EntityNames.USERS_PLURAL_ENTITY_NAME));
        } finally {
            if (usersDAO != null){
                usersDAO.closeDBConnection();
            }
        }
        return usersList;
    }
    
    
}
