package com.ll.wisesayingappjava.service;

import com.ll.wisesayingappjava.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
