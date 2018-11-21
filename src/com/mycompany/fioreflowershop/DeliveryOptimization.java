/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Nicholas
 */
public class DeliveryOptimization {
    
    public static void distanceMatrix(String[] origins, String[] destinations) throws ApiException, InterruptedException, IOException{
	       GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyBXYBscU08iCFkkeKsamT9nmP1tjtO64-w")
		    .build();
		
		      DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		      DistanceMatrix t=req.origins(origins).destinations(destinations).mode(TravelMode.DRIVING.DRIVING).await();
		//long[][] array=new long[origins.length][destinations.length];
		for(int i=0;i<origins.length;i++){
			for(int j=0;j<destinations.length;j++){
				System.out.println(t.rows[i].elements[j].distance.inMeters);
			}
		}
}
    
}
