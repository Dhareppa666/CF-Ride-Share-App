package com.captain.fresh.rideshareapp.models;

import com.captain.fresh.rideshareapp.enums.CarModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Dhareppa
 * class for Specifying the Trip model class.
 */
@NoArgsConstructor
@Getter
@Setter
public class Trip implements Serializable {

    private static final long serialVersionUID = 2388220076246087232L;

//    private int tripId;

    private String userName;

    private String source;

    private String destination;

    private int availableSeats;

    private CarModel carModel;

    private String numberPlate;

//    private RideSelectionStrategy rideSelectionStrategy;

    private TripStatus tripStatus;

    public Trip(String userName, String source, String destination, int availableSeats,
                CarModel carModel, String plateNo, TripStatus tripStatus) {
        this.userName = userName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.carModel = carModel;
        this.numberPlate = plateNo;
        this.tripStatus = tripStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", availableSeats=" + availableSeats +
                ", carModel=" + carModel +
                ", numberPlate='" + numberPlate + '\'' +
                ", tripStatus='" + tripStatus + '\'' +
                '}';
    }
}
