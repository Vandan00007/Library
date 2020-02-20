package com.vandan.patel.library.errors;

public class BookNotFoundException extends RuntimeException{
    private int id;

    public BookNotFoundException(int id){
        super("Book data is not found for id="+ id);
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
