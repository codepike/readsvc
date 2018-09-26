package org.readingplanets.readsvc2;

import org.readingplanets.readsvc2.mapper.BookMapper;
import org.readingplanets.readsvc2.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://www.readingplanets.org", maxAge = 3600)
public class HomeController {
    @Autowired
    BookMapper bookMapper;

    public HomeController(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @RequestMapping("/home")
    public String home() {
        return "Home run";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Book> book() {
        List<Book> books = bookMapper.getAll();

        System.out.println("num: " + books.size());
        return books;
    }
}
