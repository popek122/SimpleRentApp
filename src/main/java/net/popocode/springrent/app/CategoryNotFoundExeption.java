package net.popocode.springrent.app;

public class CategoryNotFoundExeption extends RuntimeException {
    public CategoryNotFoundExeption(String message){
        super(message);
    }
}
