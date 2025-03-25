package com.company;

import com.company.enums.Gender;
import com.company.exceptions.CreateException;
import com.company.exceptions.DriverNotAvailableException;
import com.company.models.Driver;
import com.company.models.Location;
import com.company.models.Rider;
import com.company.models.Vehicle;
import com.company.services.CabBookingService;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Database.initialize();
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }

        CabBookingService cabBookingService = CabBookingService.getInstance();
        Location l1 = new Location(0, 0);
        Location l2 = new Location(8, 8);

        Rider r1 = null;
        try {
            r1 = cabBookingService.registerRider("Srikant", 1, Gender.MALE, l1, l2);
            System.out.println("Rider " + r1.getId() + " successfully registered.");
        } catch (CreateException e) {
            System.out.println(e.getMessage());
        }

        Location l3 = new Location(2, 2);
        Vehicle v1 = new Vehicle("Swift", 1000, l3);
        Driver d1 = null;
        try {
            d1 = cabBookingService.registerDriver("Raju", 11, Gender.MALE, v1, true);
            System.out.println("Driver " + d1.getId() + " successfully registered.");
        } catch (CreateException e) {
            System.out.println(e.getMessage());
        }

        Location l4 = new Location(4, 4);
        Vehicle v2 = new Vehicle("Indica", 2000, l4);
        Driver d2 = null;
        try {
            d2 = cabBookingService.registerDriver("Ramu", 12, Gender.MALE, v2, true);
            System.out.println("Driver " + d2.getId() + " successfully registered.");
        } catch (CreateException e) {
            System.out.println(e.getMessage());
        }

        try {
            Driver d11 = cabBookingService.bookRide(1, l1, l2);
            System.out.println("Ride successfully booked in " + d11.getVehicle().getModel() + " and your driver is " + d11.getName());
        } catch (DriverNotAvailableException e) {
            System.out.println(e.getMessage());
        }
    }
}