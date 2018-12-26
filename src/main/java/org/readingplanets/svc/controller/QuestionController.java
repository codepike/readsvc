package org.readingplanets.svc.controller;

import org.readingplanets.svc.mapper.QuestionMapper;
import org.readingplanets.svc.model.ApiResponse;
import org.readingplanets.svc.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@CrossOrigin(origins = {"http://www.readingplanets.org","http://localhost:3000"}, maxAge = 3600)
public class QuestionController {
    private static Logger logger =  LoggerFactory.getLogger(BookController.class);

    @Autowired
    final QuestionMapper questionMapper;

    public QuestionController(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    /**
     * Return question of a chapter of a book.
     *
     * @param bookId id of the book
     * @param chapterId id of the chapter
     * @return question of a chapter of a book
     */
    @RequestMapping(value = "/question", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Question> getQuestions(@RequestParam int bookId,
            @RequestParam(value = "chapterId", required = false, defaultValue = "0") int chapterId) {
        return questionMapper.getQuestions(bookId, chapterId);
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ApiResponse addQuiz(@RequestBody Question question) {
        questionMapper.insert(question);

        System.out.println(question);

        return new ApiResponse(true, "add question successfully");
    }

}
