package com.ll.wisesayingappjava.service;

import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.repository.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> getAllQuotes(Pageable pageable){
        return quoteRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Page<Quote> searchQuotes(String keyword, Pageable pageable){
        return quoteRepository.findByAuthorContainingOrContentContainingOrderByCreatedAtDesc(keyword, keyword, pageable);
    }

}
