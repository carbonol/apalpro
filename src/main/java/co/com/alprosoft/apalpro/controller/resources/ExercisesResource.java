/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.controller.resources;

import co.com.alprosoft.apalpro.model.dao.ExercisesDAO;
import co.com.alprosoft.apalpro.model.dto.ExercisesDTO;
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

/**
 * Servidor de recursos REST asociado a los ejercicios de programación.
 * @author leand
 */
@Path("exercises")
public class ExercisesResource {
    
    @Context
    private UriInfo context;
    
    // Crea una nueva instancia de ExercisesResource

    public ExercisesResource() {
        
    }
    
    /**
     * Registra una nueva instancia de ejercicio y varias instancias de casos de prueba asociados al ejercicio, como recursos en la base de datos.
     * @return una instancia de ExercisesDTO.
     */
    @POST
    @Path("create/withTestCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ExercisesDTO createWithTestCases(ExercisesDTO newExerciseWithTestCases) {
        ExercisesDTO exerciseWithTestCases = new ExercisesDTO();
        ExercisesDAO exercisesDAO = null;
        try {
            exercisesDAO = new ExercisesDAO();
            Integer idExerciseWithTestCases = exercisesDAO.createWithTestCases(newExerciseWithTestCases);          
            exerciseWithTestCases = exercisesDAO.getByIdWithTestCases(idExerciseWithTestCases);
        } catch (SQLException ex) {
            System.out.println("Hubo un error a nivel de base de datos al tratar de registrar un nuevo ejercicio con casos de prueba: " + ex);
        } catch (Exception ex) {
            System.out.println("Hubo un error NO relacionado con la base de datos al tratar de registrar un nuevo ejercicio con casos de prueba: " + ex);
        } finally {
            if (exercisesDAO != null){
                exercisesDAO.closeDBConnection();
            }            
        }
        return exerciseWithTestCases;
    }
    
    /**
     * Busca la representación completa de una instancia existente de un ejercicio con sus respectivos casos de prueba como recurso con base en el ID de ejercicio dado, 
     *  y la da al cliente.
     * @param id El id del ejercicio.
     * @return una instancia de ExercisesDTO.
     */
    @GET
    @Path("{id}/get/withTestCases")
    @Produces(MediaType.APPLICATION_JSON)
    public ExercisesDTO getByIdWithTestCases(@PathParam("id") int id) {
        ExercisesDTO exerciseWithTestCases = new ExercisesDTO(); // Objeto generado para TRANSFERIR DATOS desde la base de datos al usuario.
        ExercisesDAO exercisesDAO = null; // Objeto de ACCESO DE DATOS a la base de datos.
        try {
            exercisesDAO = new ExercisesDAO();
            exerciseWithTestCases = exercisesDAO.getByIdWithTestCases(id);
        } catch (SQLException ex) {
            System.out.println("Hubo un error a nivel de bases de datos al tratar de obtener un ejercicio con sus casos de prueba, según el ID del ejercicio: " + ex);
        } catch (Exception ex) {
            System.out.println("Hubo un error NO relacionado con la base de datos al tratar de obtener un ejercicio con sus casos de prueba, según el ID del ejercicio: " + ex);        
        } finally {
            if (exercisesDAO != null){
                exercisesDAO.closeDBConnection();
            }            
        }
        return exerciseWithTestCases;
    }
    
    /**
     * Busca la representación completa de una instancia existente de un ejercicio con sus respectivos casos de prueba que son visibles al estudiante 
     *  como recurso con base en el ID de ejercicio dado, y la da al cliente.
     * @param id El id del ejercicio.
     * @return una instancia de ExercisesDTO.
     */
    @GET
    @Path("{id}/get/withVisibleTestCases")
    @Produces(MediaType.APPLICATION_JSON)
    public ExercisesDTO getByIdWithVisibleTestCases(@PathParam("id") int id) {
        ExercisesDTO exerciseWithTestCases = new ExercisesDTO(); // Objeto generado para TRANSFERIR DATOS desde la base de datos al usuario.
        ExercisesDAO exercisesDAO = null; // Objeto de ACCESO DE DATOS a la base de datos.
        try {
            exercisesDAO = new ExercisesDAO();
            exerciseWithTestCases = exercisesDAO.getByIdWithVisibleTestCases(id);
        } catch (SQLException ex) {
            System.out.println("Hubo un error a nivel de bases de datos al tratar de obtener un ejercicio con sus casos de prueba visibles por estudiantes, según el ID del ejercicio: " + ex);
        } catch (Exception ex) {
            System.out.println("Hubo un error NO relacionado con la base de datos al tratar de obtener un ejercicio con sus casos de prueba visibles por estudiantes, según el ID del ejercicio: " + ex);        
        } finally {
            if (exercisesDAO != null){
                exercisesDAO.closeDBConnection();
            }            
        }
        return exerciseWithTestCases;
    }
    
    
}
