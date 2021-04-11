package net.popocode.springrent.components.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(Scanner scanner,CategoryRepository categoryRepository){
        this.scanner=scanner;
        this.categoryRepository=categoryRepository;
    }
    public void createCategory(){
        Category category= readCategory();
        try{
        categoryRepository.save(category);
        System.out.println("Dodano Kategorie");
        System.out.println(category);
        } catch (DataIntegrityViolationException e){
            System.err.println("Nie dodano kategori mozliwe ze istnije juz kategoria o tej nazwie");
        }
    }

    private Category readCategory() {
        Category category=new Category();
        System.out.println("Podaj nazwe Kategori");
        scanner.nextLine();
        category.setName(scanner.nextLine());
        System.out.println("Podaj Opis Kategori");
        category.setDescription(scanner.nextLine());
        return category;
    }
    public void removeCategory(){
        System.out.println("Podaj id Kategori ktora chcesz usunac");
        long categoryid=scanner.nextLong();
        Optional<Category> category=categoryRepository.findById(categoryid);
        category.ifPresentOrElse(categoryRepository::delete,()->System.out.println("Brak kategori o wskazanym id"));
    }
}
