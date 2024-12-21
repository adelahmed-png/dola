package com.mycompany.parkinglot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    private String userName;
    private String passWord;

    public void login() throws FileNotFoundException {
        // File location corrected
        File file = new File("registration.txt");

        // Ensuring the Scanner is properly handled
        try (Scanner scan = new Scanner(file); Scanner in = new Scanner(System.in)) {
            System.out.print("Enter your username: ");
            userName = in.nextLine();
            System.out.print("Enter your password: ");
            passWord = in.nextLine();

            boolean credentialsValid = false;

            // Read the file line by line and validate credentials
            while (scan.hasNextLine()) {
                String[] credentials = scan.nextLine().split(",");
                if (credentials.length == 2) {
                    String fileUsername = credentials[0].trim();
                    String filePassword = credentials[1].trim();

                    if (userName.equalsIgnoreCase(fileUsername) && passWord.equals(filePassword)) {
                        credentialsValid = true;
                        break;
                    }
                }
            }

            // Output result
            if (credentialsValid) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        }
    }
}
