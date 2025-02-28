package com.company.services;

import com.company.exceptions.DriverNotAvailableException;
import com.company.models.Driver;
import com.company.models.Location;
import com.company.models.Ride;
import com.company.models.Rider;

import java.util.ArrayList;
import java.util.List;

public class RideService {

    private static RideService rideService = null;

    private UserService userService = UserService.getInstance();

    private static final Integer MAX_DISTANCE = 4;

    private RideService() {
    }

    public static RideService getInstance() {
        if (rideService == null) {
            rideService = new RideService();
        }
        return rideService;
    }

    public Driver bookRide(int riderId, Location fromLocation, Location toLocation) throws DriverNotAvailableException {
        Rider rider = userService.getRiderMap().get(riderId);
        List<Driver> driverList = getAllAvailableDrivers(fromLocation);
        if (driverList.isEmpty()) {
            throw new DriverNotAvailableException("No driver found");
        }
        Driver assignedDriver = driverList.get(0);
        assignedDriver.setAvailable(false);
        Ride ride = new Ride(assignedDriver, rider, fromLocation, toLocation);
        rider.getRideList().add(ride);
        return assignedDriver;
    }

    public List<Driver> getAllAvailableDrivers(Location location) {
        List<Driver> driverList = new ArrayList<>();
        for (Driver driver : userService.getDriverMap().values()) {
            if (driver.isAvailable() && distance(driver.getVehicle().getLocation(), location) <= MAX_DISTANCE) {
                driverList.add(driver);
            }
        }
        return driverList;
    }

    public Integer distance(Location l1, Location l2) {
        return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
    }
}