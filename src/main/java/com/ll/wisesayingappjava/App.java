package com.ll.wisesayingappjava;

import com.ll.wisesayingappjava.model.Quote;
import com.ll.wisesayingappjava.service.QuoteService;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
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

                Quote savedQuote = quoteService.savedQuote(author, content);
                System.out.println(savedQuote.getId()+"번 명언이 등록되었습니다.");

            } else if (input.equals("수정")) {
                System.out.println("명언 수정 로직");

            } else if (input.equals("삭제")) {
                System.out.println("명언 삭제 로직");

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
