/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.pojo;

import jakarta.validation.constraints.NotBlank;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author naren
 */
@Entity(name="User")
@Table(name="User")
public class User {
    
    @Id
    @Column(name="UserName")
    private String username;
    
    @Basic
    @Column(name="Password")
    private String password;
    
    @Basic
    @Column(name="Role")
    private String userRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

       
}
