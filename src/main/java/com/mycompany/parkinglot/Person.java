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
public abstract class Person {
   private String name;
   private long id;
   private  long mobileNumber;

    public Person() {
    }

   
    public Person(String name, long id, long mobileNumber) {
        this.name = name;
        this.id = id;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
   
   public abstract void login();
   public  void registration(){
      
       
   };
   
   
   
}

