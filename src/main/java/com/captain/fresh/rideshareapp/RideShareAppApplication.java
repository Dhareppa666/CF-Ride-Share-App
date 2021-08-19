package com.captain.fresh.rideshareapp;

import com.captain.fresh.rideshareapp.service.TripServiceImpl;
import com.captain.fresh.rideshareapp.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@Log4j2
@SpringBootApplication
public class RideShareAppApplication {

    /**
     * Main Application to rin Ride Share Application.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RideShareAppApplication.class, args);

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        TripServiceImpl tripOperations = new TripServiceImpl();
        Scanner sc = new Scanner(System.in);
        printOptions();
        int param = sc.nextInt();
        while (param != (10)) {
            switch (param) {
                case 1: //add_user
                    System.out.println(userServiceImpl.addUserFromCLI());
                    break;
                case 2: //view_users
                    userServiceImpl.displayUsers();
                    break;
                case 3://add_vehicle
                    System.out.println(userServiceImpl.addVehicleFromCLI());
                    break;
                case 4://view_vehicles
                    userServiceImpl.displayVehicles();
                    break;
                case 5://offer_ride
                    System.out.println(tripOperations.addTripFromCLI());
                    break;
                case 6://view_rides
                    tripOperations.displayOfferedTrips();
                    break;
                case 7://request_ride
                    System.out.println(tripOperations.requestTrip());
                    break;
                case 8://end_ride
                    tripOperations.endTrip();
                    break;
                case 9://display_stats
                    tripOperations.displayTripsStats();
                    break;
                default:
                    System.out.println("Invalid Option.");
            }
            printOptions();
            param = sc.nextInt();
        }
        System.out.println("All Operation End.");
    }

    /**
     * Users can choose option to do their Tasks.
     */
    private static void printOptions() {
        System.out.println("------------------------------");
        System.out.println("Press 1 to add_user");
        System.out.println("Press 2 to view_users");
        System.out.println("Press 3 to add_vehicle");
        System.out.println("Press 4 to view_vehicles");
        System.out.println("Press 5 to Add/offer_ride");
        System.out.println("Press 6 to offered_rides");
        System.out.println("Press 7 to request_ride");
        System.out.println("Press 8 to end_ride");
        System.out.println("Press 9 to print_ride_stats");
        System.out.println("Press 10 to exit");
        System.out.println("------------------------------");
    }
}
