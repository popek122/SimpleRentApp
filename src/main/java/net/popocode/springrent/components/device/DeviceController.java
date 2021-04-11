package net.popocode.springrent.components.device;

import net.popocode.springrent.app.CategoryNotFoundExeption;
import net.popocode.springrent.components.category.Category;
import net.popocode.springrent.components.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DeviceController(Scanner scanner,DeviceRepository deviceRepository,CategoryRepository categoryRepository){
        this.scanner=scanner;
        this.deviceRepository= deviceRepository;
        this.categoryRepository=categoryRepository;
    }
    @Transactional
    public void createDevice(){
        try {
            Device device= readDevice();
            deviceRepository.save(device);
            System.out.println("Dodano urzadzenie");
            System.out.println(device);
        }catch (CategoryNotFoundExeption e){
            System.err.println("Urzedzenia nie dodano"+e.getMessage());
        }
    }

    private Device readDevice() {
        Device device=new Device();
        System.out.println("Nazwa urzadzenia:");
        scanner.nextLine();
        device.setName(scanner.nextLine());
        System.out.println("Opis urzadzenia:");
        device.setDescription(scanner.nextLine());
        System.out.println("Cena urzedzenia:");
        device.setPrice(scanner.nextDouble());
        System.out.println("Ilosc (szt) urzadzenia:");
        device.setQuantity(scanner.nextInt());
        System.out.println("Kategoria urzadzenia");
        String categoryName=scanner.nextLine();
        Optional<Category> category=categoryRepository.findByNameIgnoreCase(categoryName);
        category.ifPresentOrElse(device::setCategory,
                ()->{
            throw new CategoryNotFoundExeption("Kategori o takiej nazwie juz istnieje: ");
                });
        return device;
    }
    public void removeDevice(){
        System.out.println("Podaj id urzeniia ktore chcesz usunac:");
        long deviceId=scanner.nextLong();
        Optional<Device> device=deviceRepository.findById(deviceId);
        device.ifPresentOrElse(deviceRepository::delete,()->System.out.println("Urzadzenie o podanym id nie istenie"+deviceId));
    }
    public void searchDevice(){
        System.out.println("Podaj fragment nawy");
        String name= scanner.nextLine();
        List<Device> devices=deviceRepository.findAllByNameContainingIgnoreCase(name);
        if (devices.isEmpty())
            System.out.println("Brak urzadzen o wskazanej nazwie");
        else {
            System.out.println("Znaleziono urzadzenia:");
            devices.forEach(System.out::println);
        }
    }
    }
