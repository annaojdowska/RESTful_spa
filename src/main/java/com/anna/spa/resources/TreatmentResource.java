/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.spa.resources;

import com.anna.spa.entities.Reservation;
import com.anna.spa.entities.Treatment;
import com.anna.spa.servlet.ReservationContext;
import com.anna.spa.servlet.TreatmentContext;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Anna
 */

@Path("/treatments")
public class TreatmentResource {
    public static final String TREATMENT_CONTEXT = "treatmentContext";
    public static final String RESERVATION_CONTEXT = "reservationContext";
    private static final String GOOGLE_API_KEY = "AIzaSyCiarLATYgrSpje_gaFyuMZJ5RFB4-B2Fs";
    private static final GeoApiContext geoApiContext = new GeoApiContext().setApiKey(GOOGLE_API_KEY);
    private static final String DESTINATIONS = "London Aldwych 71";
    public static final String RESERVATION_COMMAND = "reserve";
    @Context
    ServletContext context;

    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Treatment> listTreatments(){
        return getTreatmentContext().getAllTreatments();
    }
    @GET
    @Path("list/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Treatment> listFilteredTreatments(@QueryParam("category") String catFilter, @QueryParam("minPrice") int minPrice, @QueryParam("maxPrice") int maxPrice){
        return getTreatmentContext().getTreatmentsByCategoryAndPrice(catFilter, minPrice, maxPrice);
    }
    
    @GET
    @Path("reservations")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Reservation> listReservations(){
        return getReservationContext().getAllReservations();
    }
    
    @GET
    @Path("distance")
    @Produces(MediaType.APPLICATION_JSON)
    public DistanceMatrix getDistance(@QueryParam("cityOrigin") String cityOrigin, 
            @QueryParam("streetOrigin") String streetOrigin, 
            @QueryParam("noOrigin") String noOrigin,
            @QueryParam("travelMode") String travelMode, 
            @QueryParam("units") String units) throws ApiException, InterruptedException, IOException     
    {
        String origins = cityOrigin+" "+streetOrigin+" "+noOrigin;
        DistanceMatrixApiRequest apiRequest = DistanceMatrixApi.newRequest(geoApiContext);
            DistanceMatrix matrix = apiRequest.origins(origins).destinations(DESTINATIONS)
                    .mode(getTravelMode(travelMode)).units(getUnits(units)).language("en-En").await();
            return matrix;
    }
    
    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Treatment findTreatmentById(@QueryParam("treatment_id") int id){
        Treatment tr = getTreatmentContext().find(id);
        if(tr != null){
            return tr; 
        }
        return null;
    }
    
    @POST
    @Path("reserve")
    public void reserveTreatment(@FormParam("treatment_id") Integer id, 
            @FormParam("date") String date, 
            @FormParam("hour") Integer hour, 
            @FormParam("name") String name) throws IOException {
        LocalDate date1 = LocalDate.parse(date);
        Reservation reservation = new Reservation(date1,hour,id,name, getTreatmentContext().find(id).getName());
        
        if(getReservationContext().saveReservation(reservation) == true)
            response.sendRedirect("../../reservations.html");
        else
            response.sendRedirect("../../reservationFail.html");

    }
    private TreatmentContext getTreatmentContext(){
        TreatmentContext treatmentContext = (TreatmentContext)context.getAttribute(TREATMENT_CONTEXT);
        if(treatmentContext ==null){
            treatmentContext = new TreatmentContext();
            context.setAttribute(TREATMENT_CONTEXT, treatmentContext);
        }
        return treatmentContext;
    }
    
    private ReservationContext getReservationContext(){
        ReservationContext reservationContext = (ReservationContext)context.getAttribute(RESERVATION_CONTEXT);
        if(reservationContext ==null){
            reservationContext = new ReservationContext();
            context.setAttribute(RESERVATION_CONTEXT, reservationContext);
        }
        return reservationContext;
    }
    
        private TravelMode getTravelMode(String mode){
        switch(mode){
            case "driving":return TravelMode.DRIVING;
            case "bicycling":return TravelMode.BICYCLING;
            case "walking":return TravelMode.WALKING;
            default: return TravelMode.UNKNOWN;
        }
    }
    
    private Unit getUnits(String units){
        switch(units){
            case "metric":return Unit.METRIC;
            case "imperial":return Unit.IMPERIAL;
            default: return Unit.METRIC;
        }
    }
    
    
}
