/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author leand
 */
public class SolutionProposalsDTO implements Serializable {
    // En los DTO sólo deben ir atributos, constructores y getters/setters.
    // Además, esta clase debe implementar java.io.serializable y sus atributos no deben tener caracteres en mayúscula, si recuerdo correctamente,
    //  para evitar inconvenientes con el funcionamiento de la aplicación web (backend).
    private int id;
    private String created_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private String updated_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private boolean status;
    private int user_id;
    private String code;
    private int assessment_result_id;
    // Estos no son atributos que mapeen directamente con atributos de la tabla solution_proposals en la base de datos, pero sirve para obtener o almacenar la 
    //  información detallada (pero relevante) del usuario que hizo la propuesta de solución y del resultado de evaluación de la solución propuesta, junto con 
    //  la información de la solución propuesta, en una sola petición.
    private UsersDTO user;
    private AssessmentResultsDTO assessment_result;
    
    private int exercise_id;
    private ArrayList<SolutionProposalTestCaseAssessmentsDTO> solution_proposal_test_case_assessments;

    public SolutionProposalsDTO() {
    }

    public SolutionProposalsDTO(int id, String created_at, String updated_at, boolean status, int user_id, String code, int assessment_result_id) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.user_id = user_id;
        this.code = code;
        this.assessment_result_id = assessment_result_id;
    }

    public SolutionProposalsDTO(int id, String created_at, String updated_at, boolean status, int user_id, String code, int assessment_result_id, UsersDTO user, AssessmentResultsDTO assessment_result) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.user_id = user_id;
        this.code = code;
        this.assessment_result_id = assessment_result_id;
        this.user = user;
        this.assessment_result = assessment_result;
    }

    public SolutionProposalsDTO(int id, String created_at, String updated_at, boolean status, int user_id, String code, int assessment_result_id, UsersDTO user, AssessmentResultsDTO assessment_result, int exercise_id, ArrayList<SolutionProposalTestCaseAssessmentsDTO> solution_proposal_test_case_assessments) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.user_id = user_id;
        this.code = code;
        this.assessment_result_id = assessment_result_id;
        this.user = user;
        this.assessment_result = assessment_result;
        this.exercise_id = exercise_id;
        this.solution_proposal_test_case_assessments = solution_proposal_test_case_assessments;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAssessment_result_id() {
        return assessment_result_id;
    }

    public void setAssessment_result_id(int assessment_result_id) {
        this.assessment_result_id = assessment_result_id;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public AssessmentResultsDTO getAssessment_result() {
        return assessment_result;
    }

    public void setAssessment_result(AssessmentResultsDTO assessment_result) {
        this.assessment_result = assessment_result;
    }

    public ArrayList<SolutionProposalTestCaseAssessmentsDTO> getSolution_proposal_test_case_assessments() {
        return solution_proposal_test_case_assessments;
    }

    public void setSolution_proposal_test_case_assessments(ArrayList<SolutionProposalTestCaseAssessmentsDTO> solution_proposal_test_case_assessments) {
        this.solution_proposal_test_case_assessments = solution_proposal_test_case_assessments;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }
    
    
}
