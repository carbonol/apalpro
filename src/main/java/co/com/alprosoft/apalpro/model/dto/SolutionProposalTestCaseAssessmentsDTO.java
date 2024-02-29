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
public class SolutionProposalTestCaseAssessmentsDTO implements Serializable {
    // En los DTO sólo deben ir atributos, constructores y getters/setters.
    // Además, esta clase debe implementar java.io.serializable y sus atributos no deben tener caracteres en mayúscula, si recuerdo correctamente,
    //  para evitar inconvenientes con el funcionamiento de la aplicación web (backend).
    private int id;
    private String created_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private String updated_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private boolean status;
    private int solution_proposal_id; // El ID de la solución propuesta por el estudiante
    private int test_case_id; // El ID del caso de prueba (de un problema) que fue evaluado automáticamente, el cual contiene la correspondencia exacta de entrada-salida
    //  que debe cumplir el programa propuesto.
    private String output_data; // Los datos de salida (texto) que fueron producidos por el programa cuando se introdujeron los datos de entrada descritos en el caso de prueba
    //  evaluado.
    private int assessment_result_id; // El ID del resultado de la evaluación automática del cumplimiento del caso de prueba (del problema) por parte de la propuesta de solución.
    // Estos no son atributos que mapeen directamente con atributos de la tabla solution_proposal_test_case_assessments de la base de datos, pero sirve para obtener o 
    //  almacenar la información detallada de la propuesta de solución evaludada, el caso de prueba (del problema) evaluado, y el resultado de la evaluación del caso de
    //  prueba, junto con los datos de salida (output_data) y demás datos generales asociados a la evaluación del caso de prueba, en una sola petición.
    private SolutionProposalsDTO solution_proposal;
    private TestCasesDTO test_case;
    private AssessmentResultsDTO assessment_result;

    public SolutionProposalTestCaseAssessmentsDTO() {
    }

    public SolutionProposalTestCaseAssessmentsDTO(int id, String created_at, String updated_at, boolean status, int solution_proposal_id, int test_case_id, String output_data, int assessment_result_id) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.solution_proposal_id = solution_proposal_id;
        this.test_case_id = test_case_id;
        this.output_data = output_data;
        this.assessment_result_id = assessment_result_id;
    }

    public SolutionProposalTestCaseAssessmentsDTO(int id, String created_at, String updated_at, boolean status, int solution_proposal_id, int test_case_id, String output_data, int assessment_result_id, SolutionProposalsDTO solution_proposal, TestCasesDTO test_case, AssessmentResultsDTO assessment_result) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.solution_proposal_id = solution_proposal_id;
        this.test_case_id = test_case_id;
        this.output_data = output_data;
        this.assessment_result_id = assessment_result_id;
        this.solution_proposal = solution_proposal;
        this.test_case = test_case;
        this.assessment_result = assessment_result;
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

    public int getSolution_proposal_id() {
        return solution_proposal_id;
    }

    public void setSolution_proposal_id(int solution_proposal_id) {
        this.solution_proposal_id = solution_proposal_id;
    }

    public int getTest_case_id() {
        return test_case_id;
    }

    public void setTest_case_id(int test_case_id) {
        this.test_case_id = test_case_id;
    }

    public String getOutput_data() {
        return output_data;
    }

    public void setOutput_data(String output_data) {
        this.output_data = output_data;
    }

    public int getAssessment_result_id() {
        return assessment_result_id;
    }

    public void setAssessment_result_id(int assessment_result_id) {
        this.assessment_result_id = assessment_result_id;
    }

    public SolutionProposalsDTO getSolution_proposal() {
        return solution_proposal;
    }

    public void setSolution_proposal(SolutionProposalsDTO solution_proposal) {
        this.solution_proposal = solution_proposal;
    }

    public TestCasesDTO getTest_case() {
        return test_case;
    }

    public void setTest_case(TestCasesDTO test_case) {
        this.test_case = test_case;
    }

    public AssessmentResultsDTO getAssessment_result() {
        return assessment_result;
    }

    public void setAssessment_result(AssessmentResultsDTO assessment_result) {
        this.assessment_result = assessment_result;
    }
    
    
}
