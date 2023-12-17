package diakonov.library.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private volatile int id;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min=2, max = 30, message = "Название должно содержать от 2 до 30 символов")
    private String bookName;
    @NotEmpty(message = "Название не должно быть пустым")
    private String author;
    @Min(value = 1900, message = "Введите корректный год")
    private int yearOfIssue;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Book() { }

    public Book( String bookName, String author, int yearOfIssue) {
        this.bookName = bookName;
        this.author = author;
        this.yearOfIssue = yearOfIssue;
    }
}

