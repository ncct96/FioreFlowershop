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
import com.mycompany.fioreflowershop.modal.Order;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Nicholas
 */
public class DeliveryOptimization {

    public static TSPSolver distanceMatrix(ListInterface<Order> origins, ListInterface<Order> destinations) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBXYBscU08iCFkkeKsamT9nmP1tjtO64-w")
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);

        String[] origin = new String[origins.getTotalEntries()];
        String[] dest = new String[destinations.getTotalEntries()];

        // Loop list to string array
        for (int i = 0; i < origins.getTotalEntries(); i++) {
            if (origins.getItem(i + 1).getCon().getAddress() != null) {
                origin[i] = origins.getItem(i + 1).getCon().getAddress();
            } else {
                origin[i] = origins.getItem(i + 1).getCorp().getAddress();
            }
        }

        for (int j = 0; j < destinations.getTotalEntries(); j++) {
            if (destinations.getItem(j + 1).getCon() != null) {
                dest[j] = destinations.getItem(j + 1).getCon().getAddress();
            } else {
                dest[j] = destinations.getItem(j + 1).getCorp().getAddress();
            }
        }

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
