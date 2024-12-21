package com.mycompany.parkinglot;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
  public static void runParkingLot() throws IOException {
    File file = new File("registration.txt");

    BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true));
    BufferedReader bufferReader = new BufferedReader(new FileReader(file));

    File file1 = new File("reserv.txt");
    BufferedWriter f1 = new BufferedWriter(new FileWriter(file1, true));
    BufferedReader f2 = new BufferedReader(new FileReader(file1));

    Scanner in = new Scanner(System.in);
    TypeOfVehicle tov = new TypeOfVehicle();
    Spot spot1 = new Spot();  // Initialize the spot class to load the old reservation data
    Reservation reserv = new Reservation();
    Person owner = new Owner();
    Payment payment = new Payment();
    Admin admin1 = new Admin();

    try {
        System.out.println("Sign in/up (press 1) or access admin mode (press 2):");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Sign up (press 1) or Sign in (press 0):");
                int userChoice = in.nextInt();

                if (userChoice == 1) {
                    owner.registration();
                }

                owner.login();

                System.out.println("Do you want to reserve a spot? (press 1 for Yes, 0 for No):");
                int reserveChoice = in.nextInt();

                if (reserveChoice == 1) {
                    System.out.println("Enter your vehicle type (car, largeCar, bike):");
                    String typeOfVehicle = in.next();
                    bufferWriter.write("Vehicle Type: " + typeOfVehicle);
                    bufferWriter.newLine();

                    try {
    if (tov.isValidVehicleType(typeOfVehicle)) {
        System.out.println("Choose a spot number (1-10):");
        int numOfVehicleSpot;

        while (true) {
            try {
                numOfVehicleSpot = in.nextInt();

                if (numOfVehicleSpot < 1 || numOfVehicleSpot > 10) {
                    throw new IllegalArgumentException("Invalid spot number. Please enter a number between 1 and 10.");
                }

                spot1.numOfVehicleSpot = numOfVehicleSpot;
                boolean isReserved = false;

                if (typeOfVehicle.equalsIgnoreCase("car")) {
                    isReserved = spot1.normalSpot[numOfVehicleSpot - 1] == 1;
                } else if (typeOfVehicle.equalsIgnoreCase("bike")) {
                    isReserved = spot1.bikeSpot[numOfVehicleSpot - 1] == 1;
                } else if (typeOfVehicle.equalsIgnoreCase("largeCar")) {
                    isReserved = spot1.largeSpot[numOfVehicleSpot - 1] == 1;
                }

                if (isReserved) {
                    System.out.println("This spot is already reserved. Please choose a different spot:");
                } else {
                    if (typeOfVehicle.equalsIgnoreCase("car")) {
                        spot1.normalSpot[numOfVehicleSpot - 1] = 1;
                    } else if (typeOfVehicle.equalsIgnoreCase("bike")) {
                        spot1.bikeSpot[numOfVehicleSpot - 1] = 1;
                    } else if (typeOfVehicle.equalsIgnoreCase("largeCar")) {
                        spot1.largeSpot[numOfVehicleSpot - 1] = 1;
                    }
                    bufferWriter.write("Spot Number: " + numOfVehicleSpot);
                    bufferWriter.newLine();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for the spot number.");
                in.next(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        reserv.checkSpotEmpty(typeOfVehicle, numOfVehicleSpot);

        System.out.println("Reserve slot by hour or day?");
        String countTime = in.next();

        if (countTime.equalsIgnoreCase("hour")) {
            System.out.println("How many hours do you want to reserve?");
            try {
                int hours = in.nextInt();
                if (hours <= 0) throw new IllegalArgumentException("Reservation hours must be positive.");

                bufferWriter.write("Reservation Hours: " + hours);
                bufferWriter.newLine();
                reserv.setTimeHour(hours);
                payment.calcPayment(typeOfVehicle, countTime, hours, numOfVehicleSpot);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for hours.");
                in.next(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else if (countTime.equalsIgnoreCase("day")) {
            System.out.println("How many days do you want to reserve (max 3 days)?");
            try {
                int days = in.nextInt();
                if (days <= 0 || days > 3) throw new IllegalArgumentException("Reservation days must be between 1 and 3.");

                bufferWriter.write("Reservation Days: " + days);
                bufferWriter.newLine();
                reserv.setTimeDay(days);
                payment.calcPayment(typeOfVehicle, countTime, days, numOfVehicleSpot);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for days.");
                in.next(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid choice for time.");
        }

        // Update reservation data in file
        spot1.updateReservation();
    } else {
        System.out.println("Invalid vehicle type.");
    }
} catch (IOException e) {
    System.out.println("An error occurred while writing to the buffer.");
    e.printStackTrace();
} catch (Exception e) {
    System.out.println("An unexpected error occurred.");
    e.printStackTrace();
} 
                }
                break;

            case 2:
                admin1.login();
                System.out.println("Admin Options:");
                System.out.println("1 - Add slot");
                System.out.println("2 - Delete slot");
                System.out.println("3 - Display slots");
                System.out.println("4 - Calculate all money");
                System.out.println("5 - Update slot");
                System.out.println("6 - Show owner info");
                System.out.println("7 - Show all info");
                System.out.println("--------------------------");

                int adminChoice = in.nextInt();
                switch (adminChoice) {
                    case 1:
                        System.out.println("Enter slot type (1 - car, 2 - bike, 3 - large car):");
                        int slotType = in.nextInt();
                        if (slotType == 1) admin1.addSlotCar();
                        else if (slotType == 2) admin1.addSlotBike();
                        else if (slotType == 3) admin1.addSlotLargeCar();
                        else System.out.println("Invalid slot type.");
                        break;

                    case 2:
                        System.out.println("Choose the slot type to delete (1 - car, 2 - bike, 3 - large car):");
                        int deleteChoice = in.nextInt();
                        if (deleteChoice == 1) admin1.deleteCarSpot();
                        else if (deleteChoice == 2) admin1.deleteBikeSpot();
                        else if (deleteChoice == 3) admin1.deleteLargeCarSpot();
                        else System.out.println("Invalid choice.");
                        break;

                    case 3:
                        System.out.println("Choose slot type to display (1 - car, 2 - bike, 3 - large car):");
                        int displayChoice = in.nextInt();
                        if (displayChoice == 1) admin1.checkCarSpot();
                        else if (displayChoice == 2) admin1.checkBikeSpot();
                        else if (displayChoice == 3) admin1.checkLargeCarSpot();
                        else System.out.println("Invalid choice.");
                        break;

                    case 4:
                        System.out.println("Calculating total earnings...");
                        break;

                    case 5:
                        System.out.println("Updating slots...");
                        admin1.updateSlots();
                        break;

                    case 6:
                        System.out.println("Owner Info:");
                        System.out.println(owner.toString());
                        break;
                    case 7:
                        bufferReader = new BufferedReader(new FileReader(file));
                        System.out.println("File content:");
                        String line;
                        while ((line = bufferReader.readLine()) != null) {
                            System.out.println(line);
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }

        // Close and reopen buffer for reading
        bufferWriter.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

      public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            runParkingLot();
            System.out.println("Press 122 to exit or any other key to continue.");
            String input = scanner.nextLine();
            if ("122".equalsIgnoreCase(input)) {
                System.out.println("Exiting the application...");
                break;
            }
        }
    }

}
