package org.readingplanets.svc.model;

public class Book {

    int id;

    String title;

    String series;

    String author;

    String isbn;

    int year;

    String cover;

    public Book() {

    }

    public Book(int id, String title, String series, String  author, String isbn, int year, String cover) {
        this.id = id;
        this.title = title;
        this.series = series;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.cover = cover;
    }

    public Book(String title, String series, String  author, String isbn, int year, String cover) {
        this(0, title, series, author, isbn, year, cover);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getSeries(){
        return series;
    }

    public void setSeries(String series){
        this.series = series;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String getCover(){
        return cover;
    }

    public void setCover(String cover){
        this.cover = cover;
    }

    @Override
    public String toString(){
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", series='" + series + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", cover='" + cover + '\'' +
                '}';
    }
}
