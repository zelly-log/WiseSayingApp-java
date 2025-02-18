package com.ll.wisesayingappjava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 명언 번호

    private String author; // 작가

    private String content; // 명언

    private LocalDateTime createdAt; // 작성 시간

}
