package com.mycompany.parkinglot;

import java.util.Scanner;

public class Admin extends Person {
    // Admin credentials
    private final String apassWord = "admin";
    private final String aname = "admin";

    // Instance of Spot to manage parking slots
    Spot spot = new Spot();

    Scanner in = new Scanner(System.in);

    // Constructors
    public Admin() {}

    public Admin(String name, int id, int mobileNumber) {
        super(name, id, mobileNumber);
    }

    // Add slots
    public int addSlotCar() {
        try {
            return Spot.carSlotNum++;
        } catch (Exception ex) {
            System.out.println("Error adding car slot. Try again.");
            return addSlotCar();
        }
    }

    public int addSlotBike() {
        try {
            return Spot.bikeSlotNum++;
        } catch (Exception ex) {
            System.out.println("Error adding bike slot. Try again.");
            return addSlotBike();
        }
    }

    public int addSlotLargeCar() {
        try {
            return Spot.largecarSlotNum++;
        } catch (Exception ex) {
            System.out.println("Error adding large car slot. Try again.");
            return addSlotLargeCar();
        }
    }

    // Display slot status
     private Reservation reservation = new Reservation();
   // private Spot spot = new Spot();

    // Admin method to display available spots for cars
    public void checkCarSpot() {
        System.out.println("Car Spot Status:");
        for (int i = 0; i < spot.normalSpot.length; i++) {
//            System.out.println(spot.normalSpot[0]);
           System.out.println("Spot " + (i + 1) + ": " + (spot.normalSpot[i] == 0 ? "Available" : "Reserved"));
        }
    }

     public void checkBikeSpot() {
        System.out.println("Bike Spot Status:");
        for (int i = 0; i < spot.bikeSpot.length; i++) {
            System.out.println("Spot " + (i + 1) + ": " + (spot.bikeSpot[i] == 0 ? "Available" : "Reserved"));
        }
    }
      public void checkLargeCarSpot() {
        System.out.println("Large Car Spot Status:");
        for (int i = 0; i < spot.largeSpot.length; i++) {
            System.out.println("Spot " + (i + 1) + ": " + (spot.largeSpot[i] == 0 ? "Available" : "Reserved"));
        }
    }

    // Delete slots
    public void deleteCarSpot() {
        checkCarSpot();
        System.out.println("Choose the car slot to delete:");
        int slot = in.nextInt();
        if (slot > 0 && slot <= Spot.carSlotNum) {
            Spot.normalSpot[slot - 1] = 1;
            System.out.println("Car slot " + slot + " deleted successfully.");
        } else {
            System.out.println("Invalid slot number.");
        }
    }

    public void deleteBikeSpot() {
        checkBikeSpot();
        System.out.println("Choose the bike slot to delete:");
        int slot = in.nextInt();
        if (slot > 0 && slot <= Spot.bikeSlotNum) {
            Spot.bikeSpot[slot - 1] = 1;
            System.out.println("Bike slot " + slot + " deleted successfully.");
        } else {
            System.out.println("Invalid slot number.");
        }
    }

    public void deleteLargeCarSpot() {
        checkLargeCarSpot();
        System.out.println("Choose the large car slot to delete:");
        int slot = in.nextInt();
        if (slot > 0 && slot <= Spot.largecarSlotNum) {
            Spot.largeSpot[slot - 1] = 1;
            System.out.println("Large car slot " + slot + " deleted successfully.");
        } else {
            System.out.println("Invalid slot number.");
        }
    }

    // Update slot status
    public void updateSlots() {
        try {
            System.out.println("Enter the slot type to update (1 - Car, 2 - Bike, 3 - Large Car):");
            int type = in.nextInt();
            System.out.println("Enter the slot number to update:");
            int slot = in.nextInt();
            System.out.println("Enter new status (1 for Empty, 0 for Occupied):");
            int newStatus = in.nextInt();

            switch (type) {
                case 1:
                    if (slot > 0 && slot <= Spot.carSlotNum) {
                        Spot.normalSpot[slot - 1] = newStatus;
                        System.out.println("Car slot updated successfully.");
                    } else {
                        System.out.println("Invalid car slot number.");
                    }
                    break;
                case 2:
                    if (slot > 0 && slot <= Spot.bikeSlotNum) {
                        Spot.bikeSpot[slot - 1] = newStatus;
                        System.out.println("Bike slot updated successfully.");
                    } else {
                        System.out.println("Invalid bike slot number.");
                    }
                    break;
                case 3:
                    if (slot > 0 && slot <= Spot.largecarSlotNum) {
                        Spot.largeSpot[slot - 1] = newStatus;
                        System.out.println("Large car slot updated successfully.");
                    } else {
                        System.out.println("Invalid large car slot number.");
                    }
                    break;
                default:
                    System.out.println("Invalid slot type.");
            }
        } catch (Exception e) {
            System.out.println("Error while updating slot: " + e.getMessage());
        }
    }

    // Admin login
    public void login() {
        System.out.println("Enter Username:");
        String username = in.next();
        System.out.println("Enter Password:");
        String password = in.next();

        if (aname.equals(username) && apassWord.equals(password)) {
            System.out.println("Login successful. Welcome, Admin!");
        } else {
            System.out.println("Invalid username or password. Try again.");
            login();
        }
    }
}
