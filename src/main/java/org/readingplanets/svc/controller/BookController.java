package org.readingplanets.svc.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.readingplanets.svc.mapper.BookMapper;
import org.readingplanets.svc.model.ApiResponse;
import org.readingplanets.svc.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://www.readingplanets.org","http://localhost:3000"}, maxAge = 3600)
public class BookController {

    private static Logger logger =  LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookMapper bookMapper;

    private final AmazonS3 s3Client;

    public BookController(BookMapper bookMapper) {
        this.bookMapper = bookMapper;

        s3Client = AmazonS3ClientBuilder.standard().build();
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "Home run";
    }


    @RequestMapping(value = "/book", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Book> book() {

        List<Book> books = bookMapper.getAll();

        return books;
    }


    /**
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ApiResponse saveBook(@RequestBody Book book) {
        if (!bookMapper.exists(book.getId())) {
            bookMapper.insert(book);
        } else {
            bookMapper.update(book);
        }

        return new ApiResponse(true, "save book successfully");
    }


    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Book getBook(@PathVariable("id") int id) {

        Book book = bookMapper.getBook(id);

        return book;
    }


    /**
     * Add a cover image to a book by bookId. The bookId must exist in the system.
     *
     * @param bookId
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/cover")
    @ResponseBody
    public ApiResponse addCover(@RequestParam int bookId,
            @RequestParam  MultipartFile file) throws IOException {
        if (!bookMapper.exists(bookId)) {
            return new ApiResponse(false, "Book does not exist");
        }

        final String url = "https://s3.us-east-2.amazonaws.com/readapp-images/"; //14eb1bf9e920d1c3ff68cdda185cc041.png
        logger.info("bookId {}", bookId);
        logger.info("filename {} {} ", file.getName(), file.getOriginalFilename());

        String originalFileName = file.getOriginalFilename();
        int i = originalFileName.lastIndexOf('.');
        if (i < 0 ){
            return new ApiResponse(false, "Invalid file");
        }

        String suffix = originalFileName.substring(i);


        InputStream inputStream = file.getInputStream();
        logger.info("md5: {}", org.apache.commons.codec.digest.DigestUtils.md5Hex(file.getInputStream()));

        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(file.getInputStream());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());


        PutObjectRequest putObjectRequest = new PutObjectRequest("readapp-images", md5+ suffix, inputStream, metadata);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult result = s3Client.putObject(putObjectRequest);

        Book book = bookMapper.getBook(bookId);
        book.setCover(url + md5 + suffix);

        bookMapper.update(book);

        return new ApiResponse(true, "add book successfully");
    }
}
