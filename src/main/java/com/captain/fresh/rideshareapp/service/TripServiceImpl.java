package com.captain.fresh.rideshareapp.service;

import com.captain.fresh.rideshareapp.enums.CarModel;
import com.captain.fresh.rideshareapp.models.Trip;
import com.captain.fresh.rideshareapp.models.TripStatus;
import com.captain.fresh.rideshareapp.utils.ServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Dhareppa.
 * This class is responsible for Trip Related Operations.
 */
@Log4j2
@Service
public class TripServiceImpl {

    private static Map<String, List<Trip>> TRIP_OFFERED = new HashMap<>();

    private static Map<String, TripStatus> TRIP_USERS = new HashMap<>();

    private Scanner sc;

    private ServiceUtil serviceUtil;

    public TripServiceImpl() {
        this.serviceUtil = new ServiceUtil();
        this.sc = new Scanner(System.in);
    }

    /**
     * Post Construct map to hold Pre defined Values.
     */
    @PostConstruct
    public void init() {
        TRIP_OFFERED.put("Rohan", List.of(new Trip("Rohan", "Hyderabad", "Bangalore", 1, CarModel.Swift, "KA-01-12345", new TripStatus()),
                new Trip("Rohan", "Bangalore", "Pune", 1, CarModel.Swift, "KA-01-12345", new TripStatus())));
        TRIP_OFFERED.put("Shipra", List.of(new Trip("Shipra", "Bangalore", "Mysore", 1, CarModel.Activa, "KA-12-12332", new TripStatus()),
                new Trip("Shipra", "Bangalore", "Mysore", 2, CarModel.Polo, "KA-05-41491", new TripStatus())));
        TRIP_OFFERED.put("Shashank", List.of(new Trip("Shashank", "Hyderabad", "Bangalore", 2, CarModel.Baleno, "TS-05-62395", new TripStatus())));
        TRIP_OFFERED.put("Rahul", List.of(new Trip("Rahul", "Hyderabad", "Bangalore", 5, CarModel.XUV, "KA-05-1234", new TripStatus())));
        log.info("Current Users are : {}", TRIP_OFFERED);
    }

    /**
     * Display all Users from the map.
     */
    public void displayOfferedTrips() {
        for (Map.Entry s : TRIP_OFFERED.entrySet()) {
            log.info("Trip =>{}:{}", s.getKey(), s.getValue().toString());
        }
    }

    /**
     * By calling this method you will be able to Add trip details using CLI.
     *
     * @return response whether Trip is added successfully or not.
     */
    public String addTripFromCLI() {
        System.out.println("Adding Trip..");
        System.out.print("please provide the UserName: ");
        String userName = sc.nextLine();
        boolean isValidUser = false;

        for (Map.Entry s : TRIP_OFFERED.entrySet()) {
            if (userName.equals(s.getKey())) {
                System.out.println("User Found to Offer a Trip.");
                isValidUser = true;
            }
        }
        if (!isValidUser) {
            return "Cannot Register a Trip since User is not Registered.";
        }
        Trip trip = new Trip();
        trip.setUserName(userName);

        System.out.print("please provide the Source Address: ");
        trip.setSource(sc.nextLine());

        System.out.print("please provide the Destination Address: ");
        trip.setDestination(sc.nextLine());

        System.out.print("please provide the Car Model: ");
        String carModel = sc.nextLine();
        trip.setCarModel(serviceUtil.setCarModel(carModel));

        System.out.print("please provide the Number Plate: ");
        trip.setNumberPlate(sc.nextLine());

        System.out.print("please provide the No of Seats to offer: ");
        trip.setAvailableSeats(sc.nextInt());

        List<Trip> vehicleList = new ArrayList<>(TRIP_OFFERED.get(userName));
        vehicleList.add(trip);

        TRIP_OFFERED.put(userName, vehicleList);

        displayOfferedTrips();
        sc = new Scanner(System.in);
        return "Trip Added Successfully to for User : " + userName;
    }

    /**
     * This method will allow {@code User} to request a Trip.
     *
     * @return Response whether requested Trip is provided with cond. or not.
     */
    public String requestTrip() {
        System.out.println("Requesting Trip..");
        System.out.println("please provide the UserName: ");
        String userName = sc.nextLine();

        Trip trip = new Trip();
        trip.setUserName(userName);

        System.out.print("please provide the Source Address: ");
        trip.setSource(sc.nextLine());

        System.out.print("please provide the Destination Address: ");
        trip.setDestination(sc.nextLine());

        serviceUtil.selectStategy(sc, trip);

        System.out.print("please provide the No of Seats to Allocate: ");
        trip.setAvailableSeats(sc.nextInt());

        Trip ridesForUser = findRidesForUser(trip);
        if (Optional.ofNullable(ridesForUser).isEmpty()) {
            System.out.println("Trip Not Found as per the Requirement for User : " + userName);
        }
        System.out.println("Trip Added Successfully to for User : " + userName);

        updateDriverTrips(ridesForUser);

        sc = new Scanner(System.in);
        return "Trip Request Ended.";
    }

    /**
     * Update the driver Details once Trip found for the Requested User.
     */
    private void updateDriverTrips(Trip trip) {
        if (Optional.ofNullable(trip).isPresent()) {
            int ridesTaken = trip.getTripStatus().getRidesTaken();
            trip.getTripStatus().setRidesOffered(++ridesTaken);
            System.out.println("Driver " + trip.getUserName() + " and the Trip is Scheduled as Follow.");
            System.out.println(trip.toString());
        }
    }

    /**
     * (In-Progress) //Comments: Dhareppa needs to resolve this ASAP.
     *
     * @return response with whether trip is ended or not
     */
    public String endTrip() {
        System.out.println("In end trip..");//TODO: need to add the endTrip logic here.
        return null;
    }

    /**
     * This Method will allow us to Find the User for given details and criteria.
     *
     * @param requestedTrip To find the suitable Trip we need requestTrip Details.
     * @return Trip     response trip data.
     */
    private Trip findRidesForUser(Trip requestedTrip) {
        System.out.println("Fetching Trips for User..");
        Trip resultTrip = new Trip();
        if (requestedTrip.getCarModel() == null) { //Most Vacant
            for (Map.Entry map : TRIP_OFFERED.entrySet()) {
                List<Trip> trips = new ArrayList<>(TRIP_OFFERED.get(map.getKey()));
                for (Trip trip : trips) {
                    if ((requestedTrip.getSource().equals(trip.getSource())) && (requestedTrip.getDestination().equals(trip.getDestination()))) {
                        resultTrip = trip;
                    }
                }
            }
        } else {
            int noOfAvailableSeats = 0;
            for (Map.Entry map : TRIP_OFFERED.entrySet()) {
                List<Trip> trips = new ArrayList<>(TRIP_OFFERED.get(map.getKey()));
                for (Trip trip : trips) {
                    if ((Optional.ofNullable(requestedTrip.getCarModel()).isEmpty()) && (requestedTrip.getDestination().equals(trip.getDestination()))
                            && (noOfAvailableSeats < trip.getAvailableSeats())) {
                        noOfAvailableSeats = trip.getAvailableSeats();
                        resultTrip = trip;
                    }
                }
            }
        }
        if (Optional.ofNullable(resultTrip).isEmpty()) {
            return null;
        }
        TripStatus tripStatus = new TripStatus();
        int alreadyRideTaken = resultTrip.getTripStatus().getRidesTaken();
        tripStatus.setRidesTaken(++alreadyRideTaken);
        TRIP_USERS.put(requestedTrip.getUserName(), tripStatus);
        updateTripFromMap(resultTrip);

        return resultTrip;
    }

    /**
     * Since the Offered User needs to be updated in Map/DB we pass
     * result trip and try to update/add the details in th map.
     *
     * @param resultTrip trip to be updated from the map.
     */
    private void updateTripFromMap(Trip resultTrip) {
        Map<String, List<Trip>> resultTrips = new HashMap<>();
        for (Map.Entry map : TRIP_OFFERED.entrySet()) {
            List<Trip> trips = new ArrayList<>(TRIP_OFFERED.get(map.getKey()));
            List<Trip> newTrips = new ArrayList<>();
            String userName = "";
            for (Trip trip : trips) {
                userName = trip.getUserName();
                if ((resultTrip.getSource().equals(trip.getSource())) &&
                        (resultTrip.getDestination().equals(trip.getDestination()) &&
                                (resultTrip.getCarModel().equals(trip.getCarModel())))) {
                    if (trip.getAvailableSeats() == resultTrip.getAvailableSeats()) {
                        continue;
                    }
                    trip.setAvailableSeats(trip.getAvailableSeats() - resultTrip.getAvailableSeats());
                }
                newTrips.add(trip);
            }
            resultTrips.put(userName, newTrips);
        }
        TRIP_OFFERED = resultTrips;
    }

    /**
     * Display all Users from the map
     */
    public void displayTripsStats() {
        for (Map.Entry s : TRIP_USERS.entrySet()) {
            log.info("Trip =>{}:{}", s.getKey(), s.getValue().toString());
        }
    }
}
