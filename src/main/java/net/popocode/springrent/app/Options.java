package net.popocode.springrent.app;

public enum Options {
    ADD_DEVICE(1,"Dodaj urzadzenie"),
    ADD_CATEGORY(2,"Dodaj Kategorie"),
    ADD_CUSTOMER(3,"Dodaj klienta"),
    RENT(4,"Wypozycz urzarzenie"),
    REMOVE_DEVICE(5,"Usun urzadzenie"),
    REMOVE_CATEGORY(6,"Usun Kategorie"),
    REMOVE_CUSTOMER(7,"Usun Klienta"),
    SEARCH_DEVICE(8,"Szukaj urzadzenia po nazwie"),
    EXIT(9,"KONIEC");

    private int number;
    private String name;

    Options(int number,String name){
        this.name=name;
        this.number=number;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }

    static Options numberToCateogry(int number){
        if (number<1||number> values().length)
            throw new InvaildOptionExeption();
        return values()[number-1];
    }



}
