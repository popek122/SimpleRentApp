package net.popocode.springrent.app;

import net.popocode.springrent.components.category.CategoryController;
import net.popocode.springrent.components.customer.CustomerController;
import net.popocode.springrent.components.device.DeviceController;
import net.popocode.springrent.components.rent.RentController;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

@Controller
public class ApplicationController {

    private Scanner scanner;
    private DeviceController deviceController;
    private CategoryController categoryController;
    private CustomerController customerController;
    private RentController rentController;

    public ApplicationController(Scanner scanner,DeviceController deviceController,CategoryController categoryController,
                                 CustomerController customerController, RentController rentController){
        this.scanner=scanner;
        this.deviceController=deviceController;
        this.categoryController=categoryController;
        this.customerController=customerController;
        this.rentController=rentController;
    }

    public void mainLoop(){
        Options option;
        do {
            printOptions();
            option =readOption();
            executeOption(option);
        }while (option!=Options.EXIT);
    }

    private void executeOption(Options option) {
        switch (option){
            case ADD_DEVICE:
                deviceController.createDevice();
                break;
            case ADD_CATEGORY:
                categoryController.createCategory();
                break;
            case ADD_CUSTOMER:
                customerController.createCustomer();
                break;
            case RENT:
                rentController.rentDeviceToCustomer();
                break;
            case REMOVE_DEVICE:
                deviceController.removeDevice();
                break;
            case REMOVE_CATEGORY:
                categoryController.removeCategory();
                break;
            case REMOVE_CUSTOMER:
                customerController.removeCustomer();
                break;
            case SEARCH_DEVICE:
                deviceController.searchDevice();
            case EXIT:
                closeApp();
        }
    }

    private void closeApp() {
        scanner.close();
        System.out.println("Bye Bye!");
    }

    private Options readOption() {
        boolean correctOptionSelected= false;
        Options option =null;
        while (!correctOptionSelected){
            System.out.println("Podaj ID opcji");
            int optionID= scanner.nextInt();
        try{
            option=Options.numberToCateogry(optionID);
            correctOptionSelected=true;
        }catch (InvaildOptionExeption e){
            System.err.println(e.getMessage());
        }
        }
        return option;
    }

    private void printOptions() {
        System.out.println("Opcje");
        Arrays.stream(Options.values()).forEach(System.out::println);
    }
}
