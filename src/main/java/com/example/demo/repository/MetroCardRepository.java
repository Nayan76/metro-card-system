package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MetroCard;

public interface MetroCardRepository extends JpaRepository<MetroCard, Long> {

    Optional<MetroCard> findByCardNumber(String cardNumber);

}
