package com.example.demo.entity;
    
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Passenger passenger;


    @ManyToOne
    private MetroCard metroCard;

    private String journeyType; // "SINGLE" or "RETURN"

    private Double travelCharge;

    private Double discount;

    private Double serviceFee;

    private LocalDateTime transactionTime;
    
}
