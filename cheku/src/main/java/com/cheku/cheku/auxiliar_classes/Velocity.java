package com.cheku.cheku.auxiliar_classes;

import java.sql.Date;

// public class Velocity {
    
//     private Double velocity;
//     private String date;
//     private int gear;

//     public Velocity() {

//     }

//     public Velocity(Double velocity, int gear, String date) {
//         this.velocity = velocity;
//         this.date = date;
//         this.gear = gear;
//     }
    
//     public Double getVelocity() {
//         return velocity;
//     }

//     public String getDate() {
//         return date;
//     }

//     public int getGear() {
//         return gear;
//     }
// }

public interface Velocity {
    Double getVelocity();
    String getDate();
    int getGear();
}
