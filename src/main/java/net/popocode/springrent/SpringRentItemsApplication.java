package net.popocode.springrent;

import net.popocode.springrent.app.ApplicationController;
import net.popocode.springrent.components.category.Category;
import net.popocode.springrent.components.customer.Customer;
import net.popocode.springrent.components.device.Device;
import net.popocode.springrent.components.device.DeviceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SpringRentItemsApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx=SpringApplication.run(SpringRentItemsApplication.class, args);
        ApplicationController controller=ctx.getBean(ApplicationController.class);
        controller.mainLoop();
    }
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}
