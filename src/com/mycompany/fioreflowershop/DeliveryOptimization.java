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
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Order;
import com.mycompany.fioreflowershop.modal.User;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Nicholas
 */
public class DeliveryOptimization {

    public static TSPSolver distanceMatrix(String shopAddress, ListInterface<Order> destinations) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBXYBscU08iCFkkeKsamT9nmP1tjtO64-w")
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);

        String[] origin = new String[destinations.getTotalEntries() + 1];
        String[] dest = new String[destinations.getTotalEntries() + 1];
        String add = "";

        // Add origin address to first row & column of matrix
        origin[0] = shopAddress;
        dest[0] = shopAddress;

        // Loop address list to origin & destination string array
        for (int j = 2; j <= destinations.getTotalEntries() + 1; j++) {

            User user = destinations.getItem(j - 1).getUser();

            if (user instanceof Consumer) {
                dest[j - 1] = destinations.getItem(j - 1).getUser().getAddress();
                origin[j - 1] = destinations.getItem(j - 1).getUser().getAddress();
            } else {
                CorporateCustomer corp;
                corp = (CorporateCustomer) destinations.getItem(j - 1).getUser();
                dest[j - 1] = corp.getAddress();
                origin[j - 1] = corp.getAddress();
            }
        }

        DistanceMatrix t = req.origins(origin).destinations(dest).mode(TravelMode.DRIVING).await();

        double[][] array = new double[origin.length][dest.length];
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < dest.length; j++) {
                array[i][j] = t.rows[i].elements[j].distance.inMeters;
                //System.out.println(array[i][j]);
            }
        }

        int startNode = 0;
        TSPSolver solver = new TSPSolver(startNode, array);

        // Prints: [0, 3, 2, 4, 1, 5, 0]
        //System.out.println("Tour: " + solver.getTour());

        // Print: 42.0
        //System.out.println("Tour cost: " + solver.getTourCost());

        return solver;
    }
    
        public static TSPSolver distanceMatrix(String[] origin, String[] dest) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBXYBscU08iCFkkeKsamT9nmP1tjtO64-w")
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);

        DistanceMatrix t = req.origins(origin).destinations(dest).mode(TravelMode.DRIVING).await();

        double[][] array = new double[origin.length][dest.length];
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < dest.length; j++) {
                array[i][j] = t.rows[i].elements[j].distance.inMeters;
                System.out.println(array[i][j]);
            }
        }

        int startNode = 0;
        TSPSolver solver = new TSPSolver(startNode, array);

        // Prints: [0, 3, 2, 4, 1, 5, 0]
        System.out.println("Tour: " + solver.getTour());

        // Print: 42.0
        System.out.println("Tour cost: " + solver.getTourCost());

        return solver;
    }
}
