package com.ll.wisesayingappjava.controller;

import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.service.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    final private QuoteService quoteService;

    public QuoteController(QuoteService quoteService){
        this.quoteService = quoteService;
    }


    @GetMapping
    public Page<Quote> getQuotes(@RequestParam(defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page-1,5);
        return quoteService.getAllQuotes(pageable);
    }
    @GetMapping("/search")
    public Page<Quote> searchQuotes(@RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page-1, 5);

        if (keyword != null && !keyword.trim().isEmpty()) {
            return quoteService.searchQuotes(keyword, pageable);
        }

        return quoteService.getAllQuotes(pageable);
    }
}
