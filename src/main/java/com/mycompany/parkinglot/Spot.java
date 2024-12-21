package com.mycompany.parkinglot;

import java.io.*;
import java.util.ArrayList;

public class Spot {
    
    public static int carSlotNum = 10;
    public static int[] normalSpot = new int[carSlotNum];

    public static int bikeSlotNum = 10;
    public static int[] bikeSpot = new int[bikeSlotNum];

    public static int largecarSlotNum = 10;
    public static int[] largeSpot = new int[largecarSlotNum];

    public int numOfVehicleSpot;

    public Spot() {
        // Initialize spots to 0 (available)
        initializeSpots();
    }
    private void initializeSpots() {
    try (BufferedReader reader = new BufferedReader(new FileReader("reserv.txt"))) {
        String line;
        int section = 0; // Tracks which section (car, bike, large car) we're processing

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            int length = values.length;

            switch (section) {
                case 0: // Normal car spots
                    if (length == carSlotNum) {
                        for (int i = 0; i < length; i++) {
                            normalSpot[i] = Integer.parseInt(values[i]);
                        }
                    } else {
                        System.out.println("Error: Invalid car spot data in file.");
                    }
                    break;
                case 1: // Bike spots
                    if (length == bikeSlotNum) {
                        for (int i = 0; i < length; i++) {
                            bikeSpot[i] = Integer.parseInt(values[i]);
                        }
                    } else {
                        System.out.println("Error: Invalid bike spot data in file.");
                    }
                    break;
                case 2: // Large car spots
                    if (length == largecarSlotNum) {
                        for (int i = 0; i < length; i++) {
                            largeSpot[i] = Integer.parseInt(values[i]);
                        }
                    } else {
                        System.out.println("Error: Invalid large car spot data in file.");
                    }
                    break;
                default:
                    System.out.println("Error: Unexpected data in file.");
            }
            section++;
        }
    } catch (IOException e) {
        System.out.println("Error loading reservation data: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("Error parsing spot data: " + e.getMessage());
    }
}
    // Update reservation in the file
    public void updateReservation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reserv.txt"))) {
            // Write updated spot availability for cars
            writeSpot(writer, normalSpot);
            // Write updated spot availability for bikes
            writeSpot(writer, bikeSpot);
            // Write updated spot availability for large cars
            writeSpot(writer, largeSpot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Utility method to write a spot's array to file
    private void writeSpot(BufferedWriter writer, int[] spots) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int spot : spots) {
            sb.append(spot).append(",");
        }
        sb.setLength(sb.length() - 1); // Remove the trailing comma
        writer.write(sb.toString());
        writer.newLine();
    }
}
