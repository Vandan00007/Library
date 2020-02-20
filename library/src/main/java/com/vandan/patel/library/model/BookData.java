package com.vandan.patel.library.model;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.*;

public class BookData extends ResourceSupport {
    @NotBlank(message = "bookTitle is blank")
    @Size(max = 30,message = "bookTitle is too long")
    @Pattern(regexp = "[A-Za-z]*",message = "bookTitle is illegal")
    private String bookTitle="";

    @NotBlank(message = "authorName is blank")
    @Size(max = 30, message = "authorName is too long")
    @Pattern(regexp = "[A-Za-z]*", message="authorName is illegal")
    private String authorName = "";

    @NotBlank(message = "publicationDate is blank")
    @Size(max = 30, message = "publicationDate is too long")
    @Pattern(regexp = "[A-Za-z]*", message="publicationDate is illegal")
    private String publicationDate = "";

    @NotBlank(message = "language is blank")
    @Pattern(regexp = "(English|French)?",
            message = "language is illegal")
    private String language = "";

    @NotNull(message = "format is null")
    @Size(max=30,message = "format is too long")
    @Pattern(regexp = "[A-Za-z]*",message = "format is illegal")
    private String format="";

    @NotNull(message = "used or new is null")
    private Boolean usedOrNew;

    public BookData(){}

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

    public Boolean getUsedOrNew() {
        return usedOrNew;
    }

    public void setUsedOrNew(Boolean usedOrNew) {
        this.usedOrNew = usedOrNew;
    }
}
