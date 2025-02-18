package com.ll.wisesayingappjava.repository;

import com.ll.wisesayingappjava.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    // 최신 순으로 명언 목록 조회(한 페이지에 최대 5개 페이징)
    Page<Quote> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 특정 검색어를 포함하는 명언 목록 조회(한 페이지에 최대 5개 페이징)
    Page<Quote> findByAuthorContainingOrContentContainingOrderByCreatedAtDesc(String author, String content, Pageable pageable);

}
