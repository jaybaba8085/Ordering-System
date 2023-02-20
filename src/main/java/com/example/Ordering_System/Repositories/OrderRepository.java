package com.example.Ordering_System.Repositories;

import com.example.Ordering_System.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
