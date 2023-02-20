package com.example.Ordering_System.Repositories;

import com.example.Ordering_System.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
