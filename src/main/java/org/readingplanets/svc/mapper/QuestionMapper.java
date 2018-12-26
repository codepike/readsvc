package org.readingplanets.svc.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.readingplanets.svc.model.Question;

import java.util.List;

public interface QuestionMapper {
    /**
     * Return questions of a chapter of a book. The returned questions are sorted
     * by the order. Questions with smaller order appear at the beginning of the
     * list. Questions with the same order are sorted by the question id. Questions
     * with smaller id appear before those with larger ID.
     *
     * @param bookId id of the book
     * @param chapterId id of the chapter
     * @return questions of a chapter of a book ordered by order then by id
     */
    @Select("SELECT id, book_id, chapter_id, priority, question, answer from question WHERE book_id = #{bookId} and chapter_id = #{chapterId}")
    @Results(value = {
            @Result(property = "id", column = "ID", jdbcType= JdbcType.INTEGER),
            @Result(property = "bookId", column = "BOOK_ID", jdbcType= JdbcType.INTEGER),
            @Result(property = "chapterId", column = "CHAPTER_ID", jdbcType= JdbcType.INTEGER),
            @Result(property = "priority", column = "PRIORITY", jdbcType= JdbcType.INTEGER),
            @Result(property = "question", column = "QUESTION", jdbcType=JdbcType.VARCHAR),
            @Result(property = "answer", column = "ANSWER", jdbcType=JdbcType.VARCHAR)
    })
    List<Question> getQuestions(@Param("bookId") int bookId, @Param("chapterId") int chapterId);

    /**
     * Persist a question
     *
     * @param question question to persist
     */
    @Insert("INSERT INTO question(book_id, chapter_id, priority, question, answer)" +
            "VALUES(#{bookId}, #{chapterId}, #{priority}, #{question}, #{answer})")
    void insert(Question question);
}
