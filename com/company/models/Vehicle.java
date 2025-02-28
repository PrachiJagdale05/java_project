package com.company.models;

public class Vehicle {
    private String model;
    private int capacity;
    private Location location;

    public Vehicle(String model, int capacity, Location location) {
        this.model = model;
        this.capacity = capacity;
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}