package com.ll.wisesayingappjava.repository;

import com.ll.wisesayingappjava.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
