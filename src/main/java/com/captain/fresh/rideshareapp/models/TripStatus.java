package com.captain.fresh.rideshareapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Dhareppa
 * This class for Specifying the Address model class.
 */
@NoArgsConstructor
@Getter
@Setter
public class TripStatus implements Serializable {

    private static final long serialVersionUID = -4736936444848317674L;

    private int ridesTaken;

    private int ridesOffered;

    @Override
    public String toString() {
        return "{" +
                "ridesTaken=" + ridesTaken +
                ", ridesOffered=" + ridesOffered +
                '}';
    }
}
