package com.captain.fresh.rideshareapp.utils;

import com.captain.fresh.rideshareapp.enums.CarModel;
import com.captain.fresh.rideshareapp.models.Trip;

import java.util.Scanner;

/**
 * A util class which acts as formatter for service classes.
 */
public class ServiceUtil {

    /**
     * Set the carModel for a given String.
     *
     * @param carModel carModel.
     * @return CarModel Enum carModel.
     */
    public CarModel setCarModel(String carModel) {
        CarModel result;
        switch (carModel) {
            case "Swift":
                result = CarModel.Swift;
                break;
            case "Baleno":
                result = CarModel.Baleno;
                break;
            case "Polo":
                result = CarModel.Polo;
                break;
            case "XUV":
                result = CarModel.XUV;
                break;
            case "Activa":
                result = CarModel.Activa;
                break;
            default:
                System.out.println("Default CardModel set.");
                result = CarModel.Activa;
        }
        return result;
    }

    /**
     * Selection Strategy for User.
     *
     * @param sc   Inuput from CLI.
     * @param trip add respective details for a Trip.
     */
    public void selectStategy(Scanner sc, Trip trip) {
        System.out.print("Choose 1 for Most Vacant, 2 for Car Model :");
        int selection = sc.nextInt();
        if (selection == 1) {
            trip.setCarModel(null);
            System.out.println("Most Vacant Trip will be fetched.");
        } else { // Default Selection.
            System.out.println("Enter your Proffered Car Model: ");
            trip.setCarModel(setCarModel(sc.nextLine()));
        }
    }
}
