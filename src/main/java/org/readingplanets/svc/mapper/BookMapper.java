package org.readingplanets.svc.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.readingplanets.svc.model.Book;

import java.util.List;

public interface BookMapper {

    @Select("SELECT * FROM book")
    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "author", column = "AUTHOR")
    })
    List<Book> getAll();
}
