package com.ll.wisesayingappjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.service.QuoteService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class App {

    private final QuoteService quoteService;

    public App(QuoteService quoteService){
        this.quoteService = quoteService;
    }

    public void run(){

        // 프로그램 실행시 scanner로 명령 받기
        Scanner sc = new Scanner(System.in); // System.in 사용자로부터 입력 받음

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.println("명령을 입력하세요: ");
            String input = sc.nextLine().trim(); // trim() 공백 제거

            if (input.equals("목록")) {
                List<Quote> quotes = quoteService.getAllQuotesList();
                for (int i = quotes.size()-1; i>=0 ; i--) {
                    Quote quote = quotes.get(i);
                    System.out.printf("%d / %s / %s%n", quote.getId(), quote.getAuthor(),
                            quote.getContent());
                }
                System.out.println("------------------------------");

            } else if (input.equals("등록")) {

                System.out.println("작가");
                String author = sc.nextLine();
                System.out.println("명언");
                String content = sc.nextLine();

                Quote saveQuote = quoteService.saveQuote(author, content);
                System.out.println(saveQuote.getId()+"번 명언이 등록되었습니다.");

            } else if (input.equals("수정")) {
                System.out.println("수정할 명언 번호를 입력하세요.");
                Long updateId = Long.parseLong(sc.nextLine().trim());

                Quote existingQuote = quoteService.updateQuote(updateId, "","");
                if (existingQuote == null){
                    System.out.println(updateId+"번 명언은 존재하지 않습니다.");
                    continue;
                }

                System.out.println("수정할 명언 작가를 입력하세요.");
                String updateAuthor = sc.nextLine();
                System.out.println("수정할 명언 내용을 입력하세요.");
                String updateContent = sc.nextLine();

                Quote updateQuote = quoteService.updateQuote(updateId, updateAuthor, updateContent);
                System.out.println(updateQuote.getId()+"번 명언이 수정되었습니다.");

            } else if (input.equals("삭제")) {
                System.out.println("삭제할 명언 번호를 입력하세요.");
                Long deleteId = Long.parseLong(sc.nextLine().trim());

                boolean isDeleted = quoteService.deleteQuote(deleteId);

                if(!isDeleted)
                {
                    System.out.println(deleteId + "번 명언이 존재하지 않습니다.");
                } else{
                    System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                }

            } else if (input.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;

            } else {
                System.out.println("목록 / 등록 / 수정 / 삭제 / 종료 중 해당하는 명령어를 입력해 주세요.");
            }
        }
        sc.close();
    }
}
