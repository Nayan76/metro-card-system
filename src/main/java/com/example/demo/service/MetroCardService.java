package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.example.demo.entity.*;
import com.example.demo.repository.MetroCardRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class MetroCardService {
    
    @Autowired
    private MetroCardRepository metroCardRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private PassengerRepository passengerRepository;
    

    public Transaction processJourney(Long passengerId, String cardNumber, String journeyType){
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);
        Optional<MetroCard> metroCardOptional = metroCardRepository.findByCardNumber(cardNumber);

        if (passengerOptional.isEmpty() || metroCardOptional.isEmpty()) {
            throw new RuntimeException("Passenger or MetroCard not found");
        }

        Passenger passenger = passengerOptional.get();
        MetroCard metroCard = metroCardOptional.get();

        Double travelCharge = calculateTravelCharge(passenger.getType(), journeyType);
        Double discount = calculateDiscount(passenger.getType(), journeyType);
        Double serviceFee = calculateServiceFee(travelCharge, metroCard.getBalance());

        Transaction transaction = new Transaction();
        transaction.setPassenger(passenger);
        transaction.setMetroCard(metroCard);
        transaction.setJourneyType(journeyType);
        transaction.setTravelCharge(travelCharge);
        transaction.setDiscount(discount);
        transaction.setServiceFee(serviceFee);
        transaction.setTransactionTime(LocalDateTime.now());

        metroCardRepository.save(metroCard);
        return transactionRepository.save(transaction);
    }

    private Double calculateTravelCharge(Passenger.PassengerType type, String journeyType) {
        // Implement logic to calculate travel charge based on passenger type and journey type
        return 100.0; // Placeholder value
    }

    private Double calculateDiscount(Passenger.PassengerType type, String journeyType) {
        // Implement logic to calculate discount based on passenger type and journey type
        return journeyType.equals("RETURN") ? 50.0 : 0.0; // Placeholder value
    }

    private Double calculateServiceFee(Double travelCharge, Double balance) {
        // Implement logic to calculate service fee based on travel charge and card balance
        return balance < travelCharge ? travelCharge * 0.02 : 0.0; // Placeholder value
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Map<String, Double> getCollectionSummary() {
        // TODO Auto-generated method stub
        List<Transaction> transactions = transactionRepository.findAll();
        Double totalAmountCollected = transactions.stream()
                .mapToDouble(Transaction::getTravelCharge)
                .sum();
        Double totalDiscountGiven = transactions.stream()
                .mapToDouble(Transaction::getDiscount)
                .sum();

        return Map.of(
                "totalAmountCollected", totalAmountCollected,
                "totalDiscountGiven", totalDiscountGiven
        );
    }

    public Map<String, Long> getPassengerSummary() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getPassenger().getType().toString(),
                        Collectors.counting()
                ));
    }
}
