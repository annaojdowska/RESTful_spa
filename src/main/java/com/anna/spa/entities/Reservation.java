/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.spa.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Anna
 * entity class representing single resevation for service in SPA
 */

@lombok.Getter
@lombok.Setter
@XmlType(name = "reservation")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "reservation")
public class Reservation implements Serializable {
    static int idCounter = 0;
     @XmlAttribute(name = "id")
    private Integer id;
     
    @XmlAttribute(name = "date")
    private LocalDate date;
    
    @XmlAttribute(name = "hour")
    private Integer hour;
    
    @XmlAttribute(name = "treatmentId")
    private Integer treatmentId;
    
    @XmlAttribute(name = "treatmentName")
    private String treatmentName;

    @XmlAttribute(name = "clientName")
    private String clientName;

    public Reservation() {
        id = idCounter;
        idCounter++;
    }
    
    public Reservation(LocalDate date,Integer hour, 
            Integer treatmentId, String name, String treatmentName) {
        this.id = idCounter;
        idCounter++;
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.date = date;
        this.clientName = name;
        this.hour = hour;
    }
}
