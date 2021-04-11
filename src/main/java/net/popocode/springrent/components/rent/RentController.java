package net.popocode.springrent.components.rent;

import net.popocode.springrent.components.customer.Customer;
import net.popocode.springrent.components.customer.CustomerRepository;
import net.popocode.springrent.components.device.Device;
import net.popocode.springrent.components.device.DeviceRepository;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class RentController {
    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;

    public RentController(Scanner scanner, DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void rentDeviceToCustomer(){
        try {
            rent();
        }catch (RentExeption e){
            System.err.println(e.getMessage());
        }
    }
    private void rent(){
        System.out.println("Podaj ID Klienta");
        scanner.nextLine();
        long customerId=scanner.nextLong();
        System.out.println("Podaj id Urzarzenia");
        long deviceId=scanner.nextLong();
        Optional<Customer> customer=customerRepository.findById(customerId);
        Optional<Device> device=deviceRepository.findById(deviceId);
        if (customer.isPresent())
            device.ifPresentOrElse(dev->{
                if (dev.getQuantity()>dev.getCustomers().size())
                    dev.AddCustomer(customer.get());
                else
                    throw new RentExeption("Brak Wolnego urzadzenia o wskazanym id");
            },()->{
                throw new RentExeption("Brak urzadzenia o wskazanym id");
            });
        else
            throw new RentExeption("Brak klienta o wskazanym id");
    }
}
