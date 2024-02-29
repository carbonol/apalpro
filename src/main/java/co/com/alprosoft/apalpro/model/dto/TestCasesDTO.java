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
public class TestCasesDTO implements Serializable {
    // En los DTO sólo deben ir atributos, constructores y getters/setters.
    // Además, esta clase debe implementar java.io.serializable y sus atributos no deben tener caracteres en mayúscula, si recuerdo correctamente,
    //  para evitar inconvenientes con el funcionamiento de la aplicación web (backend).
    private int id;
    private String created_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private String updated_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private boolean status;
    private int exercise_id;
    private String input_data; // Datos de entrada (texto) del caso de prueba
    private String expected_output; // Datos de salida (texto) que se espera que la propuesta de solución (programa de computador) produzca cuando se
    //  ingresan los datos de entrada al programa propuesto.
    private boolean is_visible; // Si el caso de prueba es visible o no al estudiante.
    // Este no es un atributo que mapee directamente con un atributo de la tabla test_cases en la base de datos, pero sirve para obtener o almacenar la información 
    //  detallada del ejercicio al que está asociado este caso de prueba, junto con la información del caso de prueba, en una sola petición.
    private ExercisesDTO exercise;

    public TestCasesDTO() {
    }

    public TestCasesDTO(int id, String created_at, String updated_at, boolean status, int exercise_id, String input_data, String expected_output, boolean is_visible) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.exercise_id = exercise_id;
        this.input_data = input_data;
        this.expected_output = expected_output;
        this.is_visible = is_visible;
    }

    public TestCasesDTO(int id, String created_at, String updated_at, boolean status, int exercise_id, String input_data, String expected_output, boolean is_visible, ExercisesDTO exercise) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.exercise_id = exercise_id;
        this.input_data = input_data;
        this.expected_output = expected_output;
        this.is_visible = is_visible;
        this.exercise = exercise;
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

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getInput_data() {
        return input_data;
    }

    public void setInput_data(String input_data) {
        this.input_data = input_data;
    }

    public String getExpected_output() {
        return expected_output;
    }

    public void setExpected_output(String expected_output) {
        this.expected_output = expected_output;
    }

    public boolean isIs_visible() {
        return is_visible;
    }

    public void setIs_visible(boolean is_visible) {
        this.is_visible = is_visible;
    }

    public ExercisesDTO getExercise() {
        return exercise;
    }

    public void setExercise(ExercisesDTO exercise) {
        this.exercise = exercise;
    }
    
    
}
