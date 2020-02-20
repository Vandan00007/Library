package com.vandan.patel.library.data;

import com.vandan.patel.library.controller.BookDataController;
import com.vandan.patel.library.model.Book;
import com.vandan.patel.library.model.BookData;
import com.vandan.patel.library.model.BookDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookDataServiceImpl implements BookDataService {

    private BookRepository bookRepository;

    @Autowired
    BookDataServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }


    private static void copyDataToBook(BookData data, int id, Book book){
        book.setId(id);
        book.setBookTitle(data.getBookTitle());
        book.setAuthorName(data.getAuthorName());
        book.setPublicationDate(data.getPublicationDate());
        book.setLanguage(data.getLanguage());
        book.setFormat(data.getFormat());
        book.setUsedOrNew(data.getUsedOrNew());
    }

    private static void copyBookToData(Book book,BookData data){
        Link link = ControllerLinkBuilder.linkTo(BookDataController.class)
                .slash(book.getId()).withSelfRel();
        data.add(link);
        data.setBookTitle(book.getBookTitle());
        data.setAuthorName(book.getAuthorName());
        data.setPublicationDate(book.getPublicationDate());
        data.setLanguage(book.getLanguage());
        data.setFormat(book.getFormat());
        data.setUsedOrNew(book.getUsedOrNew());
    }

    @Override
    public BookData insertBookData(BookData data){
        Book book = new Book();
        copyDataToBook(data,0,book);
        book=bookRepository.save(book);
        bookRepository.flush();
        Link link = ControllerLinkBuilder
                .linkTo(BookDataController.class)
                .slash(book.getId()).withSelfRel();
        data.add(link);
        return data;
    }

    @Override
    public BookDataList getAllBookData(){
        List<BookData> dataList = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        for(Book book:bookList){
            BookData data = new BookData();
            copyBookToData(book,data);
            dataList.add(data);
        }
        return new BookDataList(dataList);
    }

    @Override
    public void deleteAllBookData(){
        bookRepository.deleteAll();
    }

    @Override
    public void deleteBookData(int id){
        bookRepository.deleteById(id);
    }

    @Override
    public BookData getBookData(int id){
        Optional<Book> result = bookRepository.findById(id);
        if(result.isPresent()){
            BookData data = new BookData();
            Book book = result.get();
            copyBookToData(book,data);
            return data;
        }
        return null;
    }

    @Override
    public BookData updateBookData(BookData data,int id){
        Optional<Book> result = bookRepository.findById(id);
        if(result.isPresent()){
            Book book = result.get();
            copyDataToBook(data,id,book);
            bookRepository.save(book);
            bookRepository.flush();
            Link link =ControllerLinkBuilder.linkTo(BookDataController.class).slash(book.getId()).withSelfRel();
            data.add(link);
            return data;
        }else {
            return null;
        }
    }



}
