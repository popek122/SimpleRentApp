package net.popocode.springrent.components.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CustomerController {
    private Scanner scanner;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(Scanner scanner,CustomerRepository customerRepository){
        this.scanner=scanner;
        this.customerRepository=customerRepository;
    }
    public void createCustomer(){
        Customer customer=readCustomer();
        customerRepository.save(customer);
        System.out.println("Dodano Klienta");
        System.out.println(customer);
    }

    private Customer readCustomer() {
        Customer customer=new Customer();
        System.out.println("Podaj Imie Klienta: ");
        scanner.nextLine();
        customer.setFirsName(scanner.nextLine());
        System.out.println("Podaj Nazwisko Klienta: ");
        customer.setLastName(scanner.nextLine());
        System.out.println("Podaj Pesel:");
        customer.setPesel(scanner.nextLine());
        System.out.println("Podaj numer dowodu");
        customer.setIdNumber(scanner.nextLine());
        return customer;
    }
    public void removeCustomer(){
        System.out.println("Podaj id klienta,ktorego chcesz usunac");
        long customerid=scanner.nextLong();
        Optional<Customer> customer=customerRepository.findById(customerid);
        customer.ifPresentOrElse(customerRepository::delete,()->System.out.println("Nie znaleziono klienta o takim id: "+customerid));
    }
}
