package com.ll.wisesayingappjava.service;

import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.repository.QuoteRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;

    // 명언 수정 기능
    public Quote updateQuote(Long id, String updateAuthor, String cupdateContent){
        Optional<Quote> optionalQuote = quoteRepository.findById(id);

        if (optionalQuote.isEmpty()){
            return null;
        }

        Quote quote = optionalQuote.get();
        quote.setAuthor(updateAuthor);
        quote.setContent(cupdateContent);

        return quoteRepository.save(quote);
    }

    // 명언 저장 기능
    public Quote savedQuote(String author, String content){
        Quote quote = new Quote(null, author, content, LocalDateTime.now());
        return quoteRepository.save(quote);
    }

    // 명언 목록 조회 (페이징 처리 x)
    public List<Quote> getAllQuotesList(){
        return quoteRepository.findAll();
    }

    // 명언 목록 조회(페이징 처리)
    public Page<Quote> getAllQuotes(Pageable pageable){
        return quoteRepository.findAllByOrderByCreatedAtDesc(pageable);
    };

    // 검색 기능 추가(페이징 처리)
    public Page<Quote> searchQuotes(String keyword, Pageable pageable){
        return quoteRepository.searchBykeword(keyword,pageable);
    };

}
