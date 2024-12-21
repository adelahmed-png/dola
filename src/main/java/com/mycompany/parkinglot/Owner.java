/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MBR
 */
public class Owner extends Person {
   private int LicenseNumber;
   private String userName;
   private String passWord; 
   int numOfHaveVcl;
   private String []vehicles= new String[3];
    public Owner() {
//       LicenseNumber =12;
//       userName = "mw";
//       passWord = "a1";
//       numOfHaveVcl = 12;
    }
   
    public Owner(int LicenseNumber) {
        this.LicenseNumber = LicenseNumber;
    }

    public Owner(int LicenseNumber, String name, int id, int mobileNumber) {
        super(name, id, mobileNumber);
        this.LicenseNumber = LicenseNumber;
    }

    public int getLicenseNumber() {
        return LicenseNumber;
    }

    public void setLicenseNumber(int LicenseNumber) {
        this.LicenseNumber = LicenseNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
//// getter and setter
    
    public int getNumOfHaveVcl() {
        return numOfHaveVcl;
    }

    public void setNumOfHaveVcl(int numOfHaveVcl) {
        this.numOfHaveVcl = numOfHaveVcl;
    }
    
   @Override
    public String getName() {
        return super.getName();
    
    }
   @Override
    public void setName(String name){
       super.setName(name);
       }
       
   @Override
    public long getId() {
        return super.getId();
    }
       
   @Override
        public void setId(long id) {
        super.setId(id);
    }
        
   @Override
       public long getMobileNumber() {
       return super.getMobileNumber();
    }

   @Override
    public void setMobileNumber(long mobileNumber) {
        super.setMobileNumber(mobileNumber);
    }
       
 
    public void registration(){
        FileWriter fw = null;
       try{
           Scanner in = new Scanner(System.in);
           File file =new File("registeration,txt");
           fw = new FileWriter(file,true); 
           
           try{
               System.out.println("Registration now");
               
               
                System.out.println("Enter your username");
               String userName = in.next();
                fw.write(" , "+userName);
                
                System.out.println("Enter your password");
               String password = in.next();
                fw.write(" , "+password);
                
                
               System.out.println("Enter your name");
               String name = in.next();
                fw.write(" , "+name);
                
                
               System.out.println("Enter your ID");
               long id = in.nextInt();
               fw.write(" , "+id);
              
               System.out.println("Enter your mobile number");
               long mobileNumber = in.nextInt();
               fw.write(" , "+mobileNumber);
             
               System.out.println("Enter your license number");
               int licenseNumber = in.nextInt();
                fw.write(" , "+licenseNumber);
             
              
              
             
              
               System.out.println("Enter num of vehicles you have");
               numOfHaveVcl = in.nextInt();
                fw.write(" , "+numOfHaveVcl);
            
               System.out.println("Enter type of vehicles, car ,large car and bike");
               for(int i = 0; i<numOfHaveVcl ; i++){
                   System.out.println("Enter type number "+(i+1) );
                   vehicles[i]=in.next();
                    fw.write(" , "+ vehicles[i]);
                   
               }
                fw.write("\n");
                     fw.close();
               System.out.println("You are make account successfully");
               System.out.println("you will go to in login page");
               this.setName(name);
               this.setId(id);
               this.setMobileNumber(mobileNumber);
               this.setLicenseNumber(licenseNumber);
               this.setUserName(userName);
               this.setPassWord(password);
           }catch(java.util.InputMismatchException ime){
               System.out.println("wrong , you must input number only");
               registration();
//           System. exit(0);
           }  catch(Exception ex){
               System.out.println("Sorry, maybe wrong choice");
               System. exit(0);
           }
       }catch(IOException ex){
           Logger.getLogger(Owner.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               fw.close();
           } catch (IOException ex) {
               Logger.getLogger(Owner.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        }

    /**
     *
     */
    @Override
    // Original login method
    public void login() {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("If you are logging in for the first time, press 0. Otherwise, press 1:");
            int num = in.nextInt();
            
            if (num == 0) {
                registration(); 
            } else {
                // Proceed to login
                System.out.println("Login now");
                System.out.println("Enter username:");
                String username = in.next();
                System.out.println("Enter password:");
                String password = in.next();

                // Call the second login method which reads from file
                login(username, password);
            }
        } catch (Exception ex) {
            System.out.println("Sorry, an error occurred: " + ex.getMessage());
            login(); // Retry login on error
        }
    }

    // New login method that takes username and password as parameters
    public void login(String username, String password) {
        File file = new File("registeration,txt"); // Assuming this is your file for registration data
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean loginSuccess = false;

            while ((line = bufferReader.readLine()) != null) {
                // Split the line by commas and check credentials1
                String[] data = line.split(",");
                if (data.length >= 3) { // Ensure there are enough data fields
                    String storedUsername = data[1].trim(); // Username stored in file
                    String storedPassword = data[2].trim(); // Password stored in file
                    
                    // Compare the entered username and password with stored ones
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        System.out.println("You are logged in now.");
                        loginSuccess = true;
                        break; // Exit the loop once a match is found
                    }
                }
            }

            if (!loginSuccess) {
                System.out.println("Sorry, username or password might be wrong.");
                login(); // Retry login if not successful
            }
        } catch (IOException ex) {
            System.out.println("Error reading the file: " + ex.getMessage());
        }
    }


    @Override
    public String toString() {
        return "Owner{" + "LicenseNumber=" + LicenseNumber + ", userName=" + userName + ", passWord=" + passWord + ", numOfHaveVcl=" + numOfHaveVcl + ", vehicles=" + vehicles + '}';
    }
   
}
