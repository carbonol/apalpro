/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dao;

import java.util.ArrayList;

/**
 *
 * @author leand
 * @param <DTO> DTO (Data Transfer Object) asociado a la tabla de base de datos que se quiere usar para hacer una operación en la base de datos.
 * @param <ID> El tipo de dato asociado al identificador (id) de la tabla de base de datos que se quiere usar para hacer una operación en la base de datos.
 */
public interface GenericDAO<DTO, ID> {
    
    // C: Create
    public ID create(DTO dto) throws Exception;
    
    // R: Read
    public DTO getById(ID id) throws Exception;
    public ArrayList<DTO> getAll() throws Exception;
    
    // U: Update
    public boolean update(DTO dto) throws Exception;
    
    // Pseudo-D: Deshabilitar / habilitar mediante el atributo status de una tabla de base de datos
    public boolean disableById(ID id) throws Exception;
    public boolean enableById(ID id) throws Exception;
    
    // D: Delete
    public boolean deleteById(ID id) throws Exception;
    public boolean deleteAll() throws Exception;
}
