package com.cheku.cheku.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for storing notification data.
 */
@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

   /** The unique ID of the notification. */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   /** The priority of the notification. */
   @Column(name = "priority", nullable = false)
   private Integer priority;

   /** The subject of the notification. */
   @Column(name = "subject", nullable = false)
   private String subject;

   /** The message of the notification. */
   @Column(name = "message", nullable = false)
   private String message;

   /** The type of the notification. */
   @Column(name = "type", nullable = false)
   private String type;

   /** The car that the notification is related to. */
   @ManyToOne(optional = true)
   @JoinColumn(name = "car_id", nullable = true)
   private Car car;
}
