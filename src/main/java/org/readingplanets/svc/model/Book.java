package org.readingplanets.svc.model;

public class Book {

    int id;

    String title;

    String author;

    public Book(int id, String title, String  author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
