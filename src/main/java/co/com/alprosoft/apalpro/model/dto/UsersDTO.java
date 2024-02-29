
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
public class UsersDTO implements Serializable {
    // En los DTO sólo deben ir atributos, constructores y getters/setters.
    // Además, esta clase debe implementar java.io.serializable y sus atributos no deben tener caracteres en mayúscula, si recuerdo correctamente,
    //  para evitar inconvenientes con el funcionamiento de la aplicación web (backend).
    private int id;
    private String created_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private String updated_at; // Este tipo de dato puede ser String o java.sql.Date. Si se requiere hacer una operación con fechas, java.sql.Date es ideal.
    private boolean status;
    private String uname;
    private String pwd;
    private String names;
    private String surnames;
    private int role_id;
    // Este no es un atributo que mapee directamente con un atributo de la tabla roles en la base de datos, pero sirve para obtener o almacenar la información detallada del
    //  rol del usuario, junto con la información del usuario, en una sola petición.
    private RolesDTO role;
    // Este no es un atributo que mapee directamente con un atributo de la tabla roles en la base de datos, pero sirve para transferir el mensaje de confirmación de inicio
    //  de sesión de usuario al cliente.
    private UserLoginDTO user_login;

    public UsersDTO() {
    }

    public UsersDTO(int id, String created_at, String updated_at, boolean status, String uname, String pwd, String names, String surnames, int role_id) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.uname = uname;
        this.pwd = pwd;
        this.names = names;
        this.surnames = surnames;
        this.role_id = role_id;
    }

    public UsersDTO(int id, String created_at, String updated_at, boolean status, String uname, String pwd, String names, String surnames, int role_id, RolesDTO role) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.uname = uname;
        this.pwd = pwd;
        this.names = names;
        this.surnames = surnames;
        this.role_id = role_id;
        this.role = role;
    }

    public UsersDTO(UserLoginDTO user_login) {
        this.user_login = user_login;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public RolesDTO getRole() {
        return role;
    }

    public void setRole(RolesDTO role) {
        this.role = role;
    }

    public UserLoginDTO getUser_login() {
        return user_login;
    }

    public void setUser_login(UserLoginDTO user_login) {
        this.user_login = user_login;
    }
    
    
    
}
