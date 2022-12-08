//package com.cheku.cheku.model;
//
//import javax.persistence.*;
//import java.util.*;
//import java.io.Serializable;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Entity
//@Data
//@Table(name = "notifications")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Notification  {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "tipo", nullable = false)
//    private String type;
//
//    @Column(name = "message", nullable = false)
//    private String message;
//
//    @Column(name = "date", nullable = false)
//    private Date date;
//
//    @Column(name = "read", nullable = false)
//    private boolean read;
//
////    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
////    @ManyToOne(optional = false)
////    private User user;
//
//}
