package com.cheku.cheku.model;

import javax.persistence.*;
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
   private Integer priority;

   @Column(name = "subject", nullable = false)
   private String subject;

   @Column(name = "message", nullable = false)
   private String message;

   @Column(name = "type", nullable = false)
   private String type;

   // @Column(name = "date", nullable = false)
   // private Date date;

   @ManyToOne(optional = true)
   @JoinColumn(name = "car_id", nullable = true)
   private Car car;
}
