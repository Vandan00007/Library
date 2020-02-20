package com.vandan.patel.library.data;

import com.vandan.patel.library.model.BookForm;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface BookFormService {

    void insertBookForm(BookForm form);

    List<BookForm> getAllBookForm();

    void deleteAllBookForm();

    void deleteBookForm(int id);

    BookForm getBookForm(int id);

    void updateBookForm(BookForm form);
}
