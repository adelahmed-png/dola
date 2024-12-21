package com.mycompany.parkinglot;

import static com.mycompany.parkinglot.Spot.bikeSpot;
import static com.mycompany.parkinglot.Spot.largeSpot;
import static com.mycompany.parkinglot.Spot.normalSpot;

public class Reservation {
    private int timeHour;
    private int timeDay;
    final String[] time = {"hour", "day"};
    private final TypeOfVehicle vehicle = new TypeOfVehicle();

    // Getters and Setters
    public int getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(int timeHour) {
        this.timeHour = timeHour;
    }

    public int getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(int timeDay) {
        this.timeDay = timeDay;
    }

    // Method to check if a spot is available and reserve it
    public void checkSpotEmpty(String typeOfVehicle, int numOfVehicleSpot) {
        try {
            if (numOfVehicleSpot <= 0) {
                throw new IllegalArgumentException("Invalid spot number.");
            }

            switch (typeOfVehicle.toLowerCase()) {
                case "car":
                    handleReservation(normalSpot, numOfVehicleSpot, "car");
                    break;
                case "large car":
                    handleReservation(largeSpot, numOfVehicleSpot, "large car");
                    break;
                case "bike":
                    handleReservation(bikeSpot, numOfVehicleSpot, "bike");
                    break;
                default:
                    System.out.println("Invalid vehicle type. Please input valid data.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Spot number is out of bounds. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Helper method to handle reservation logic
    private void handleReservation(int[] spotArray, int spotNumber, String vehicleType) {
        if (spotNumber > spotArray.length) {
            throw new ArrayIndexOutOfBoundsException("Spot number exceeds the limit.");
        }
        if (spotArray[spotNumber - 1] == 1) {
            System.out.println("Sorry, this " + vehicleType + " spot is already reserved.");
        } else {
            spotArray[spotNumber - 1] = 1;
            System.out.println("You reserved " + vehicleType + " spot number " + spotNumber + ".");
        }
    }
}

