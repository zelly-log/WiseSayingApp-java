package com.ll.wisesayingappjava.config;

import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.repository.QuoteRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final QuoteRepository quoteRepository;

    public DataInitializer(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void run(String... args){
        if(quoteRepository.count() == 0){ // 데이터 없는 경우에 초기화(데이터 있음 추가 x)
            List<Quote> quotes = List.of(
                new Quote(null, "작자미상", "명언 10", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 9", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 8", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 7", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 6", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 5", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 4", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 3", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 2", LocalDateTime.now()),
                new Quote(null, "작자미상", "명언 1", LocalDateTime.now())
            );
            quoteRepository.saveAll(quotes);
        }
    }
}
