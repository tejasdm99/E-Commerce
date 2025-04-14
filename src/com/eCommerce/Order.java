package com.eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        if (product.checkStock(quantity)) {
            items.add(new OrderItem(product, quantity));
            product.updateStock(quantity);
            System.out.println(quantity + " x " + product.getName() + " added to order.");
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public Customer getCustomer() {
        return customer;
    }
}
