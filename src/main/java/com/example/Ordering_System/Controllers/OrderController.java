package com.example.Ordering_System.Controllers;
import java.util.ArrayList;
import java.util.List;

import com.example.Ordering_System.Models.Customer;
import com.example.Ordering_System.Models.Order;
import com.example.Ordering_System.Models.OrderItem;
import com.example.Ordering_System.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Ordering_System")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = orderService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long customerId,  @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItem> createOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderService.createOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = orderService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        List<OrderItem> orderItems = orderService.getOrderItems(orderId);
        return ResponseEntity.ok(orderItems);
    }
}


