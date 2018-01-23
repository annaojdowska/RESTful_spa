/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.spa.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Anna
 * entity class representing single service in SPA
 */

@lombok.Getter
@lombok.Setter
@XmlType(name = "treatment")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "treatment")
public class Treatment implements Serializable {
    static int idCounter = 0;
     @XmlAttribute(name = "id")
    private Integer id;
     
    @XmlAttribute(name = "category")
    private String category;
    
    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
    private String price;

    public Treatment() {
        id = idCounter;
        idCounter++;
    }
    
    public Treatment(String category, String name, String price) {
        this.id = idCounter;
        idCounter++;
        this.category = category;
        this.name = name;
        this.price = price;
    }
}
