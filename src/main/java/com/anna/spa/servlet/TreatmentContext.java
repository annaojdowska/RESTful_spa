/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.spa.servlet;

import com.anna.spa.entities.Treatment;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anna
 */
public class TreatmentContext implements Serializable{
    private ArrayList<Treatment> treatments;
    
    
    public TreatmentContext(){
        treatments = new ArrayList<>();
        try {
            String filePath = "C:\\Users\\Anna\\Desktop\\AUI REST\\SPA\\src\\main\\webapp\\services.txt";
            FileReader fr = new FileReader(filePath);
            
            Scanner scanner = new Scanner(fr).useDelimiter(";");
            scanner.next();
            while(scanner.hasNext()){
                String category = scanner.next();
                String name = scanner.next();
                String price  = scanner.next();
                Treatment treatment = new Treatment(category,name,price);
                treatments.add(treatment);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TreatmentContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Treatment> getAllTreatments(){
        return treatments;
    }
    public ArrayList<Treatment> getTreatmentsByCategoryAndPrice(String catParam, int minPrice, int maxPrice){
        ArrayList<Treatment> result = new ArrayList<>();
            for(Treatment treatment:treatments){
                if((treatment.getCategory().contains(catParam) || catParam.equals("All")) 
                        && Integer.parseInt(treatment.getPrice())>=minPrice && Integer.parseInt(treatment.getPrice())<=maxPrice)
                    result.add(treatment);
            }
        return result;
    }
    
    public Treatment find(int id){
        for(Treatment tr : treatments){
            if(tr.getId() == id) return tr;
        }
        return null;
    }
}
