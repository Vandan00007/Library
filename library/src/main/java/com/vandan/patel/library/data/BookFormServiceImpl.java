package com.vandan.patel.library.data;

import com.vandan.patel.library.model.Book;
import com.vandan.patel.library.model.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookFormServiceImpl implements BookFormService {

    private BookRepository bookRepository;

    @Autowired
    BookFormServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    private static void copyFormToBook(BookForm form, Book book){
        book.setId(form.getId());
        book.setBookTitle(form.getBookTitle());
        book.setAuthorName(form.getAuthorName());
        book.setPublicationDate(form.getPublicationDate());
        book.setLanguage(form.getLanguage());
        book.setFormat(form.getFormat());
        book.setUsedOrNew(form.getUsedOrNew().equals("yes"));
    }

    private static void copyBookToForm(Book book,BookForm form){
        form.setId(book.getId());
        form.setBookTitle(book.getBookTitle());
        form.setAuthorName(book.getAuthorName());
        form.setPublicationDate(book.getPublicationDate());
        form.setLanguage(book.getLanguage());
        form.setFormat(book.getFormat());
        form.setUsedOrNew(book.getUsedOrNew()?"yes":"no");
    }

    @Override
    public void insertBookForm(BookForm form){
        Book book = new Book();
        copyFormToBook(form,book);
        book = bookRepository.save(book);
        bookRepository.flush();
        form.setId(book.getId());
    }

    @Override
    public List<BookForm> getAllBookForm(){
        List<BookForm> formList = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        for(Book book: bookList){
            BookForm form = new BookForm();
            copyBookToForm(book,form);
            formList.add(form);
        }
        return formList;
    }

    @Override
    public void deleteAllBookForm(){
        bookRepository.deleteAll();
    }

    @Override
    public void deleteBookForm(int id){
        bookRepository.deleteById(id);
    }

    @Override
    public BookForm getBookForm(int id){
        Optional<Book> result = bookRepository.findById(id);
        if(result.isPresent()){
            BookForm form = new BookForm();
            Book book = result.get();
            copyBookToForm(book,form);
            return form;
        }
        return null;
    }

    @Override
    public void updateBookForm(BookForm form){
        Optional<Book> result = bookRepository.findById(form.getId());
        if(result.isPresent()){
            Book book = result.get();
            copyFormToBook(form,book);
            bookRepository.save(book);
            bookRepository.flush();
        }
    }


}
