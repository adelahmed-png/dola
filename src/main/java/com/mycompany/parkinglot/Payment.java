/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parkinglot;

/**
 *
 * @author MBR
 */
public class Payment {

//public class Payments {
//public static String VehicleType; //type of vehicle
public static int Slot_No ; //number of slot or spot
public static int Time; //reservaton time in hours
public static int price =1;
TypeOfVehicle vehicle = new TypeOfVehicle();
Reservation reserv = new Reservation();

public void calcPayment (String typeOfVehicle , String countTime ,int numTime,int numOfVehicleSpot ){
if (typeOfVehicle.equalsIgnoreCase(vehicle.getBike())) {
    price = 10;
} else if (typeOfVehicle.equalsIgnoreCase(vehicle.getCar())) {    // bike =10$ , car =20$ , large car =30$
    price = 20;
} else if (typeOfVehicle.equalsIgnoreCase(vehicle.getLargeCar())) {
    price = 30;
} else {
    price = 0;
}


if(numTime >=1){

if (countTime.equalsIgnoreCase(reserv.time[0])){  
    price *= numTime; 
}else if (countTime.equalsIgnoreCase(reserv.time[1])){  
    if (numTime <=3){
    price *= numTime*12;
    } else{
        System.out.println("Sorry, max days you can make reservation is 3");
        System. exit(0);
    }
}
    System.out.println("you reserve slot number "+numOfVehicleSpot+" of vehicle type "+typeOfVehicle+" successfully");
    System.out.println("Your cost is "+price+"$ only");

    }else
        System.out.println("wrong choice");
}

    @Override
    public String toString() {
        return "Payment{" + "vehicle=" + vehicle + ", reserv=" + reserv + '}';
    }
        



}
