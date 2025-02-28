package com.company.services;

import com.company.enums.Gender;
import com.company.exceptions.CreateException;
import com.company.models.*;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static UserService userService = null;

    private UserService() {
    }

    private Map<Integer, Rider> riderMap = new HashMap<>();
    private Map<Integer, Driver> driverMap = new HashMap<>();
    private Map<String, Vehicle> vehicleMap = new HashMap<>();

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public Driver registerDriver(String name, int id, Gender gender, Vehicle vehicle, boolean available) throws CreateException {
        if (driverMap.containsKey(id)) {
            throw new CreateException("Driver with ID " + id + " already exists!");
        }
        Driver driver = new Driver(name, id, gender, vehicle, available);
        driverMap.put(id, driver);
        vehicleMap.put(vehicle.getModel(), vehicle);
        return driver;
    }

    public Rider registerRider(String name, int id, Gender gender, Location fromLocation, Location toLocation) throws CreateException {
        if (riderMap.containsKey(id)) {
            throw new CreateException("Rider with ID " + id + " already exists!");
        }
        Rider rider = new Rider(name, id, gender, fromLocation, toLocation);
        riderMap.put(id, rider);
        return rider;
    }

    public Map<Integer, Driver> getDriverMap() {
        return driverMap;
    }

    public Map<Integer, Rider> getRiderMap() {
        return riderMap;
    }

    public Map<String, Vehicle> getVehicleMap() {
        return vehicleMap;
    }
}