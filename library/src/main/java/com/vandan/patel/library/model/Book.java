package com.vandan.patel.library.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id =0;

    @Column(name = "book_title")
    private String bookTitle="";

    @Column(name = "author_name")
    private String authorName="";

    @Column(name = "publication_date")
    private String publicationDate="";

    @Column(name = "language_name")
    private String language="";

    @Column(name = "format_name")
    private String format="";

    @Column(name = "used_or_new")
    private Boolean usedOrNew=false;

    public Book(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getUsedOrNew() {
        return usedOrNew;
    }

    public void setUsedOrNew(Boolean usedOrNew) {
        this.usedOrNew = usedOrNew;
    }
}
