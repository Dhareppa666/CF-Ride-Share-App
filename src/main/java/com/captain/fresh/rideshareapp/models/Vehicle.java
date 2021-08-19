package com.captain.fresh.rideshareapp.models;

import com.captain.fresh.rideshareapp.enums.CarModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dhareppa
 * User Address model class.
 */
@NoArgsConstructor
@Getter
@Setter
public class Vehicle{

    private static final long serialVersionUID = 2388220076246087232L;

    private String userName;

    private CarModel carModel;

    private String numberPlate;

    public Vehicle(String userName, CarModel carModel, String numberPlate) {
        this.userName = userName;
        this.carModel = carModel;
        this.numberPlate = numberPlate;
    }

    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                ", carModel=" + carModel +
                ", numberPlate='" + numberPlate + '\'' +
                '}';
    }
}