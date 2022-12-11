package com.cheku.cheku.model;

import javax.persistence.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
public class Notification  {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "priority", nullable = false)
   private int priority;

   @Column(name = "subject", nullable = false)
   private String subject;

   @Column(name = "message", nullable = false)
   private String message;

   // @Column(name = "date", nullable = false)
   // private Date date;

   @ManyToOne(optional = true)
   @JoinColumn(name = "car_id", nullable = true)
   private Car car;
}
