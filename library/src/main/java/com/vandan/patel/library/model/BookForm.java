package com.vandan.patel.library.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BookForm implements Serializable {
    private int id =0;

    @NotBlank
    @Size(max = 30)

    @Pattern(regexp = "[A-Za-z]*")
    private String bookTitle="";

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String authorName="";

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String publicationDate="";

    @NotBlank
    @Pattern(regexp = "(English|French)?")
    private String language = "";


    private String format= "";

    private String usedOrNew="";

    public BookForm(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUsedOrNew() {
        return usedOrNew;
    }

    public void setUsedOrNew(String usedOrNew) {
        this.usedOrNew = usedOrNew;
    }
}
