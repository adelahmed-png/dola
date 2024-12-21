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
public class TypeOfVehicle {
  private final static String []typeOfVehicle= {"Car","largeCar","bike"};
   
   // static int type;
    private  String car =TypeOfVehicle.typeOfVehicle[0];
    private  String largeCar =TypeOfVehicle.typeOfVehicle[1];
    private  String bike =TypeOfVehicle.typeOfVehicle[2];

//    public  int getType() {
//        return type;
//    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getLargeCar() {
        return largeCar;
    }

    public void setLargeCar(String largeCar) {
        this.largeCar = largeCar;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }

    @Override
    public String toString() {
        return "TypeOfVehicle{" + "car=" + car + ", largeCar=" + largeCar + ", bike=" + bike + '}';
    }
    
    public boolean isValidVehicleType(String type) {
    return type.equalsIgnoreCase(car) || type.equalsIgnoreCase(largeCar) || type.equalsIgnoreCase(bike);
}

     
    
    
     
}
