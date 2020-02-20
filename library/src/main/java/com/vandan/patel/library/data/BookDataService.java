package com.vandan.patel.library.data;

import com.vandan.patel.library.model.BookData;
import com.vandan.patel.library.model.BookDataList;

public interface BookDataService {
    BookData insertBookData(BookData data);

    BookDataList getAllBookData();

    void deleteAllBookData();

    void deleteBookData(int id);

    BookData getBookData(int id);

    BookData updateBookData(BookData data,int id);

}
