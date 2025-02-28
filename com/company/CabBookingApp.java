package com.company;

import com.company.enums.Gender;
import com.company.exceptions.CreateException;
import com.company.models.Location;
import com.company.models.Rider;
import com.company.models.Driver;
import com.company.models.Vehicle;
import com.company.services.CabBookingService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CabBookingApp extends Application {

    private CabBookingService cabBookingService = CabBookingService.getInstance();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cab Booking System");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Rider Registration
        Label riderNameLabel = new Label("Rider Name:");
        GridPane.setConstraints(riderNameLabel, 0, 0);
        TextField riderNameField = new TextField();
        riderNameField.setPromptText("Enter Rider Name");
        GridPane.setConstraints(riderNameField, 1, 0);
        Button registerRiderButton = new Button("Register Rider");
        GridPane.setConstraints(registerRiderButton, 1, 1);
        Label riderStatusLabel = new Label();
        GridPane.setConstraints(riderStatusLabel, 1, 2);

        registerRiderButton.setOnAction(e -> {
            String riderName = riderNameField.getText();
            Location l1 = new Location(0, 0);
            Location l2 = new Location(8, 8);
            try {
                Rider r1 = cabBookingService.registerRider(riderName, 1, Gender.MALE, l1, l2);
                riderStatusLabel.setText("Rider " + r1.getId() + " successfully registered.");
            } catch (CreateException ex) {
                riderStatusLabel.setText(ex.getMessage());
            }
        });

        // Driver Registration
        Label driverNameLabel = new Label("Driver Name:");
        GridPane.setConstraints(driverNameLabel, 0, 3);
        TextField driverNameField = new TextField();
        driverNameField.setPromptText("Enter Driver Name");
        GridPane.setConstraints(driverNameField, 1, 3);
        Label vehicleModelLabel = new Label("Vehicle Model:");
        GridPane.setConstraints(vehicleModelLabel, 0, 4);
        TextField vehicleModelField = new TextField();
        vehicleModelField.setPromptText("Enter Vehicle Model");
        GridPane.setConstraints(vehicleModelField, 1, 4);
        Button registerDriverButton = new Button("Register Driver");
        GridPane.setConstraints(registerDriverButton, 1, 5);
        Label driverStatusLabel = new Label();
        GridPane.setConstraints(driverStatusLabel, 1, 6);

        registerDriverButton.setOnAction(e -> {
            String driverName = driverNameField.getText();
            String vehicleModel = vehicleModelField.getText();
            Location l3 = new Location(2, 2);
            Vehicle v1 = new Vehicle(vehicleModel, 1000, l3);
            try {
                Driver d1 = cabBookingService.registerDriver(driverName, 11, Gender.MALE, v1, true);
                driverStatusLabel.setText("Driver " + d1.getId() + " successfully registered.");
            } catch (CreateException ex) {
                driverStatusLabel.setText(ex.getMessage());
            }
        });

        // Ride Booking
        Button bookRideButton = new Button("Book a Ride");
        GridPane.setConstraints(bookRideButton, 1, 7);
        Label rideStatusLabel = new Label();
        GridPane.setConstraints(rideStatusLabel, 1, 8);

        bookRideButton.setOnAction(e -> {
            Location l1 = new Location(0, 0);
            Location l2 = new Location(8, 8);
            try {
                Driver d11 = cabBookingService.bookRide(1, l1, l2);
                rideStatusLabel.setText("Ride successfully booked in " + d11.getVehicle().getModel() + " and your driver is " + d11.getName());
            } catch (Exception ex) {
                rideStatusLabel.setText(ex.getMessage());
            }
        });

        // Add components to the grid
        grid.getChildren().addAll(riderNameLabel, riderNameField, registerRiderButton, riderStatusLabel,
                                  driverNameLabel, driverNameField, vehicleModelLabel, vehicleModelField, registerDriverButton, driverStatusLabel,
                                  bookRideButton, rideStatusLabel);

        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}