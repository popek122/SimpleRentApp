package net.popocode.springrent.components.customer;

import net.popocode.springrent.components.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
