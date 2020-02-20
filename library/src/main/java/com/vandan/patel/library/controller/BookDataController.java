package com.vandan.patel.library.controller;

import com.vandan.patel.library.data.BookDataService;
import com.vandan.patel.library.errors.BookNotFoundException;
import com.vandan.patel.library.model.BookData;
import com.vandan.patel.library.model.BookDataList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
public class BookDataController {

    private final Logger logger= LoggerFactory.getLogger(BookDataController.class);

    private BookDataService bookDataService;

    @Autowired
    public BookDataController(BookDataService bookDataService){
        this.bookDataService=bookDataService;
    }

    @GetMapping
    HttpEntity<BookDataList> listBooks(){
        logger.trace("listBooks() is called");
        BookDataList bookDataList = bookDataService.getAllBookData();
        logger.trace("this book list is sent");
        return new ResponseEntity<>(bookDataList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<BookData> bookDetails(@PathVariable int id){
        logger.trace("bookDetails() is called");
        BookData data = bookDataService.getBookData(id);
        if(data != null){
            return new ResponseEntity<>(data,HttpStatus.OK);
        }else{
            logger.trace("the book data is not found");
            throw new BookNotFoundException(id);
        }
    }

    @PostMapping
    public HttpEntity<BookData> insertBook(@Validated @RequestBody BookData data){
        logger.trace("insertBook() is called");
        bookDataService.insertBookData(data);
        logger.trace("the book data is inserted");
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<BookData> updateBook(@Validated @RequestBody BookData data,@PathVariable int id){
        logger.trace("updateBook() is called");
        BookData saved = bookDataService.updateBookData(data,id);
        if(saved!= null){
            logger.trace("the book data is updated");
            return new ResponseEntity<>(data,HttpStatus.OK);
        }else {
            logger.trace("the book data is not found");
            throw new BookNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id){
        logger.trace("deleteBook() is called");
        BookData data = bookDataService.getBookData(id);
        if (data != null){
            bookDataService.deleteBookData(id);
            logger.trace("the book data is deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            logger.trace("the book data is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }











}
