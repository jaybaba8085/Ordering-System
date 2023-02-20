package com.example.Ordering_System.Repositories;

import com.example.Ordering_System.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}