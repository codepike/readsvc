package org.readingplanets.svc.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.readingplanets.svc.mapper.BookMapper;
import org.readingplanets.svc.model.Book;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookControllerTest {
    BookMapper bookMapper = Mockito.mock(BookMapper.class);

    private BookController bookController = new BookController(bookMapper);
    @Test
    public void home() throws Exception{
        Book book1 = new Book(1, "title", "author", "isbn", 2001, "");
        List<Book> books = Arrays.asList(book1);
        when(bookMapper.getAll()).thenReturn(books);

        List<Book> found = bookController.book();

        System.out.println(found);
    }

}