package com.ll.wisesayingappjava.repository;

import com.ll.wisesayingappjava.model.Quote;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    // 최신 순으로 명언 목록 조회(한 페이지에 최대 5개 페이징)
    Page<Quote> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 특정 검색어를 포함하는 명언 목록 조회(한 페이지에 최대 5개 페이징)
    @Query("SELECT q from Quote q where q.author like %:keyword% or q.content like %:keyword%")
    Page<Quote> searchBykeword(@Param("keyword") String keyword, Pageable pageable);

    Optional<Quote> findTopByOrderByIdDesc();
}
