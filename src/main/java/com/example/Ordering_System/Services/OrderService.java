package com.example.Ordering_System.Services;
import java.util.ArrayList;
import java.util.List;

import com.example.Ordering_System.Models.Customer;
import com.example.Ordering_System.Models.Order;
import com.example.Ordering_System.Models.OrderItem;
import com.example.Ordering_System.Repositories.CustomerRepository;
import com.example.Ordering_System.Repositories.OrderItemRepository;
import com.example.Ordering_System.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + order.getCustomer().getId()));

        customer.setOrderCount(customer.getOrderCount() + 1);
        order.setDiscount(calculateDiscount(customer.getOrderCount()));
        order.setTotalAmount(calculateTotalAmount(order.getOrderItems()));
        customerRepository.save(customer);
        return orderRepository.save(order);
    }

    private Double calculateDiscount(Integer orderCount) {
        if (orderCount >= 10 && orderCount < 20) {
            return 0.1;
        } else if (orderCount >= 20) {
            return 0.2;
        } else {
            return 0.0;
        }
    }

    private Double calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getUnitPrice() * orderItem.getQuantity())
                .sum();
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        return order.getOrderItems();
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        return order.getOrderItems();
    }
}

