package com.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This Talks to the Database
 */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
     CurrencyExchange findByFromAndTo(String from, String to);
}
