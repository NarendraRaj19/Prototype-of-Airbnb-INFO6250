/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.pojo;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author naren
 */


@Entity(name="Property")
@Table(name="Property")
public class Property {
    
    @Id
    @Column(name="Property_ID")
    private int propertyID;
    
    @Basic
    @Column(name="Property_Address")
    private String propertyAddress;
    
    @Basic
    @Column(name="Property_City")
    private String propertyCity;
    
    @Basic
    @Column(name="Property_State")
    private String propertyState;
    
    @Basic
    @Column(name="Property_Type")
    private String propertyType;
    
    @Basic
    @Column(name="Property_Cost")
    private String propertyCost;
    
    @Basic
    @Column(name="Owner")
    private String propertyOwner;

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    
    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPropertyState() {
        return propertyState;
    }

    public void setPropertyState(String propertyState) {
        this.propertyState = propertyState;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyCost() {
        return propertyCost;
    }

    public void setPropertyCost(String propertyCost) {
        this.propertyCost = propertyCost;
    }

    public String getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(String propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

 
}
