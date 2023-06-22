import java.io.*;
import java.util.*;

public class BookInfo {
    private String category; // 분야
    private String bookId; // 도서 번호
    private String title; // 도서명
    private String author; // 작가명
    private String publisher; // 출판사

    public BookInfo() {
    }

    public BookInfo(String category, String bookId, String title, String author, String publisher) { // 매개변수 생성자
        this.category = category;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}