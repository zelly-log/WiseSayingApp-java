package com.ll.wisesayingappjava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.repository.QuoteRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String DB_PATH = "db/wiseSaying";
    private static final String LAST_ID_PATH = DB_PATH + "/lastId.txt";

    // 마지막 ID 저장
    private void updateLastId(Long id) {
        try {
            Files.writeString(Paths.get(LAST_ID_PATH), id.toString());
        } catch (IOException e) {
            throw new RuntimeException("마지막 ID 저장 중 오류 발생: " + e.getMessage());
        }
    }

    // 마지막 ID 조회
    private Long getLastId() {
        try {
            File lastIdFile = new File(LAST_ID_PATH);
            // lastId.txt가 존재하면 파일에서 읽기
            if (lastIdFile.exists()) {
                String lastIdStr = new String(Files.readAllBytes(Paths.get(LAST_ID_PATH))).trim();
                if (!lastIdStr.isEmpty()) {
                    Long fileLastId = Long.parseLong(lastIdStr);
                    Long dbLastId = quoteRepository.findTopByOrderByIdDesc().map(Quote::getId).orElse(0L);

                    // 📌 파일 ID와 DB ID를 비교하여 최신 ID 반환
                    return Math.max(fileLastId, dbLastId);
                }
            }

            // 파일이 없거나 비어있으면, DB에서 가장 큰 ID 찾기
            return quoteRepository.findTopByOrderByIdDesc()
                    .map(Quote::getId)
                    .orElse(0L);
        } catch (IOException | NumberFormatException e) {
            return 0L;
        }
    }

    // 명언을 JSON 파일 저장
    private void saveQuoteToFile(Quote quote) {
        try {
            Files.createDirectories(Paths.get(DB_PATH)); // 폴더 생성
            File file = new File(DB_PATH + "/" + quote.getId() + ".json");
            objectMapper.writeValue(file, quote); // JSON 파일 저장
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }


    // 명언 삭제 기능
    public boolean deleteQuote(Long deleteId) {
        Optional<Quote> optionalQuote = quoteRepository.findById(deleteId);

        if (optionalQuote.isEmpty()) {
            return false;
        }

        quoteRepository.deleteById(deleteId);
        return true;

    }

    // 명언 수정 기능
    public Quote updateQuote(Long updateid, String updateAuthor, String cupdateContent){
        Optional<Quote> optionalQuote = quoteRepository.findById(updateid);

        if (optionalQuote.isEmpty()){
            return null;
        }

        Quote quote = optionalQuote.get();
        quote.setAuthor(updateAuthor);
        quote.setContent(cupdateContent);

        return quoteRepository.save(quote);
    }

    // 명언 저장 기능
    public Quote saveQuote(String author, String content){
        Long newId = getLastId() + 1;
        Quote quote = new Quote(newId, author, content, LocalDateTime.now());

        quote.setId(null);

        Quote saveQuote = quoteRepository.save(quote);

        saveQuoteToFile(saveQuote);
        updateLastId(saveQuote.getId());

        return saveQuote;
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
