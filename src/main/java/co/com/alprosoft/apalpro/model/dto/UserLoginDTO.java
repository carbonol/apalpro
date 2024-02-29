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
public class UserLoginDTO implements Serializable {
    // En los DTO sólo deben ir atributos, constructores y getters/setters.
    // Además, esta clase debe implementar java.io.serializable y sus atributos no deben tener caracteres en mayúscula, si recuerdo correctamente,
    //  para evitar inconvenientes con el funcionamiento de la aplicación web (backend).
    private boolean login;
    private String msg;

    public UserLoginDTO() {
    }

    public UserLoginDTO(boolean login, String msg) {
        this.login = login;
        this.msg = msg;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
