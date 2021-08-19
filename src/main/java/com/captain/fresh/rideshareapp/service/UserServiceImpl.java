package com.captain.fresh.rideshareapp.service;

import com.captain.fresh.rideshareapp.enums.CarModel;
import com.captain.fresh.rideshareapp.enums.Gender;
import com.captain.fresh.rideshareapp.models.User;
import com.captain.fresh.rideshareapp.models.Vehicle;
import com.captain.fresh.rideshareapp.utils.ServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Dhareppa.
 * This class is responsible for User Related Operations.
 */
@Log4j2
@Service
public class UserServiceImpl {

    private static Map<String, User> USERS_MAP = new HashMap<>();

    private static Map<String, List<Vehicle>> VEHICLES_MAP = new HashMap<>();

    private static int userId;

    private Scanner sc;

    private ServiceUtil serviceUtil;

    public UserServiceImpl() {
        this.serviceUtil = new ServiceUtil();
        this.sc = new Scanner(System.in);
    }

    /**
     *  Post Construct map to hold Pre defined Values.
     */
    @PostConstruct
    public void ProcessUser() {
        USERS_MAP.put("Rohan", new User(++userId, "Rohan", Gender.M, 36));
        USERS_MAP.put("Shashank", new User(++userId, "Shashank", Gender.M, 29));
        USERS_MAP.put("Nandini", new User(++userId, "Nandini", Gender.F, 29));
        USERS_MAP.put("Shipra", new User(++userId, "Shipra", Gender.F, 27));
        USERS_MAP.put("Gaurav", new User(++userId, "Gaurav", Gender.M, 29));
        USERS_MAP.put("Rahul", new User(++userId, "Rahul", Gender.M, 35));
        log.info("There are {} users currently present.", userId);
        log.info("Current Users are : {}", USERS_MAP);
    }

    /**
     *  Post Construct map to hold Pre defined Values.
     */
    @PostConstruct
    public void ProcessVehicles() {
        VEHICLES_MAP.put("Rohan", List.of(new Vehicle("Rohan", CarModel.Activa, "KA-01-12345")));
        VEHICLES_MAP.put("Shashank", List.of(new Vehicle("Shashank", CarModel.Baleno, "TS-05-62395")));
        VEHICLES_MAP.put("Shipra", List.of(new Vehicle("Shipra", CarModel.Baleno, "KA-05-41491"),
                new Vehicle("Shipra", CarModel.Activa, "KA-12-12332")));
        VEHICLES_MAP.put("Rahul", List.of(new Vehicle("Rahul", CarModel.XUV, "KA-05-1234")));
        log.debug("Current Vehicles are : {}", VEHICLES_MAP.size());
    }

    /**
     * Add Vehicle - Future Use for Rest API's
     * @param vehicle vehicle to add in the map/DB.
     * @return Response return whether User added or not.
     */
    public String addVehice(Vehicle vehicle) {
        User user = USERS_MAP.get(vehicle.getUserName());
        if (Optional.ofNullable(user).isPresent()) {
            VEHICLES_MAP.put(vehicle.getUserName(), Collections.singletonList(vehicle));
            return vehicle.getUserName() + "'s Vehicle Added Successfully";
        }
        return "The Vehicle User is not Registered with Us.";
    }

    /**
     * Display all Users from the map
     */
    public String addVehicleFromCLI() {
        System.out.println("Adding Vehicle..");
        System.out.println("please provide the UserName: ");
        String userName = sc.nextLine();
        boolean isValidUser = false;

        for (Map.Entry s : USERS_MAP.entrySet()) {
            if (userName.equals(s.getKey())) {
                System.out.println("User Found to Register your Vehicle.");
                isValidUser = true;
            }
        }
        if (!isValidUser) {
            return "Cannot Register a Vehicle since no Registered User Found";
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setUserName(userName);

        System.out.print("please provide the Car Model: ");
        String carModel = sc.nextLine();
        vehicle.setCarModel(serviceUtil.setCarModel(carModel));

        System.out.print("please provide the Number Plate: ");
        vehicle.setNumberPlate(sc.nextLine());

        List<Vehicle> vehiclesList = new ArrayList<>(VEHICLES_MAP.get(userName));
        vehiclesList.add(vehicle);

        VEHICLES_MAP.put(userName, vehiclesList);

        displayVehicles();
        sc = new Scanner(System.in);
        return vehicle.getCarModel() + " Vehicle Added Successfully to " + userName + " User.";
    }

    /**
     * Display all Users from the map
     */
    public void displayVehicles() {
        for (Map.Entry s : VEHICLES_MAP.entrySet()) {
            log.info("Vehicle =>{}:{}", s.getKey(), s.getValue().toString());
        }
    }

    /**
     * Add User - Future Use for Rest API's
     * @param user user to add in the map/DB.
     * @return Response return whether User added or not.
     */
    public String addUser(User user) {
        user.setUserId(++userId);
        USERS_MAP.put(user.getUserName(), user);
        displayUsers();
        return user.getUserName() + " User Added Successfully";
    }

    /**
     *  This method will allow you to register an User using CLI
     * @return Response return whether User added or not.
     */
    public String addUserFromCLI() {
        System.out.println("Adding User..");
        User user = new User();
        user.setUserId(++userId);
        System.out.print("please provide the UserName: ");
        String userName = sc.nextLine();
        user.setUserName(userName);
        System.out.print("please provide the Gender: ");
        String gender = sc.nextLine();
        if ((gender.equals("M"))) {
            user.setGender(Gender.M);
        } else {
            user.setGender(Gender.F);
        }
        System.out.print("please provide the Age: ");
        Integer age = sc.nextInt();
        user.setAge(age);

        USERS_MAP.put(user.getUserName(), user);
        displayUsers();
        sc = new Scanner(System.in);
        return user.getUserName() + " User Added Successfully";
    }

    /**
     * Display all Users from the map.
     */
    public void displayUsers() {
        for (Map.Entry s : USERS_MAP.entrySet()) {
            log.info("User =>{}:{}", s.getKey(), s.getValue().toString());
        }
    }

}
