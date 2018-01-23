/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.spa.servlet;

import com.anna.spa.entities.Reservation;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Anna
 */
public class ReservationContext implements Serializable{
    private ArrayList<Reservation> reservations;
    
    
    public ReservationContext(){
        reservations = new ArrayList<>();
    }
    
    public ArrayList<Reservation> getAllReservations(){
        return reservations;
    }
    
    public boolean saveReservation(Reservation reservation){
        boolean taken = false;
        for(Reservation res:reservations){
            if(reservation.getDate().equals(res.getDate()) 
                    && reservation.getHour().equals(res.getHour()))
                taken = true;
        }
        if(taken == false){
            reservations.add(reservation);
            return true;
        }
        else{
            return false;
        }
    }
}
