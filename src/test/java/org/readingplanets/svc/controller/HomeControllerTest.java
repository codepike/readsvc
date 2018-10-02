package org.readingplanets.svc.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.readingplanets.svc.mapper.BookMapper;
import org.readingplanets.svc.mapper.UserMapper;
import org.readingplanets.svc.model.Book;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class HomeControllerTest {
    BookMapper bookMapper = Mockito.mock(BookMapper.class);

    private HomeController homeController = new HomeController(bookMapper);
    @Test
    public void home() throws Exception{
        Book book1 = new Book(1, "title", "author");
        List<Book> books = Arrays.asList(book1);
        when(bookMapper.getAll()).thenReturn(books);

        List<Book> found = homeController.book();

        System.out.println(found);
    }

}