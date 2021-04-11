package net.popocode.springrent.app;

public class InvaildOptionExeption extends RuntimeException{
    InvaildOptionExeption(){
        super("Opcja nie istnieje");
    }
}
