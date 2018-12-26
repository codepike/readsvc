package org.readingplanets.svc.model;

/**
 * Question presents a question and an answer about a book.
 */
public class Question {

    int id;

    int bookId;

    int chapterId;

    int priority;

    String question;

    String answer;

    public Question() {
    }

    public Question(int id, int bookId, String question, String answer){
        this.id = id;
        this.bookId = bookId;
        this.question = question;
        this.answer = answer;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public int getChapterId(){
        return chapterId;
    }

    public void setChapterId(int chapterId){
        this.chapterId = chapterId;
    }

    public int getPriority(){
        return priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
}
