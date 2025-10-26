package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Transaction;
import com.example.demo.service.MetroCardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/metro")
public class MetroCardController {

    @Autowired
    private MetroCardService metroCardService;

    @PostMapping("/journey")
    public Transaction processJourney(@RequestParam Long passengerId,
                                      @RequestParam String cardNumber,
                                      @RequestParam String journeyType) {
        return metroCardService.processJourney(passengerId, cardNumber, journeyType);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return metroCardService.getAllTransactions();
    }
    
    @GetMapping("/collection-summary")
    public Map<String, Double> getCollectionSummary() {
        return metroCardService.getCollectionSummary();
    }

    @GetMapping("/passenger-summary")
    public Map<String, Long> getPassengerSummary() {
        return metroCardService.getPassengerSummary();
    }
}
