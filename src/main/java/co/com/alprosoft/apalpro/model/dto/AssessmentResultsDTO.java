/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dto;

import java.io.Serializable;

/**
 *
 * @author leand
 */
public class AssessmentResultsDTO implements Serializable {
    // En los DTO sólo deben ir atributos, constructores y getters/setters.
    // Además, esta clase debe implementar java.io.serializable y sus atributos no deben tener caracteres en mayúscula, si recuerdo correctamente,
    //  para evitar inconvenientes con el funcionamiento de la aplicación web (backend).
    private int id;
    private String created_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private String updated_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private boolean status;
    private String name;
    private String description;

    public AssessmentResultsDTO() {
    }

    public AssessmentResultsDTO(int id, String created_at, String updated_at, boolean status, String name, String description) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
