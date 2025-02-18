package com.ll.wisesayingappjava.service;

import com.ll.wisesayingappjava.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface QuoteService {

    // 명언 목록 조회(페이징 처리)
    Page<Quote> getAllQuotes(Pageable pageable);

    // 검색 기능 추가(페이징 처리)
    Page<Quote> searchQuotes(String keyword, Pageable pageable);
}
