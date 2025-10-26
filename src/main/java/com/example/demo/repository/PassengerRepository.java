package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    
}



