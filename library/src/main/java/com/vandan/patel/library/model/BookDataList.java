package com.vandan.patel.library.model;
import com.vandan.patel.library.controller.BookDataController;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.List;

public class BookDataList extends ResourceSupport {
    private List<BookData> dataList;
    public List<BookData> getBooks(){
        return dataList;
    }

    public BookDataList(List<BookData> dataList){
        this.dataList=dataList;
        Link link = ControllerLinkBuilder.linkTo(BookDataController.class).withSelfRel();
        add(link);
    }
}
