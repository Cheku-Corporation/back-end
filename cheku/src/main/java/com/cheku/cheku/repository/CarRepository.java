package com.cheku.cheku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class CarRepository extends JpaRepository<Car, Long>  {

}