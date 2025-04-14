package com.eCommerce;

public class InvoiceGenerator {
    public void generateInvoice(Order order) {
        System.out.println("\n--- Invoice ---");
        for (OrderItem item : order.getItems()) {
            System.out.println(item.product.getName() + " x" + item.quantity + " = $" + item.getTotalPrice());
        }
        System.out.println("Total: $" + order.getTotalAmount());
        System.out.println("Customer: " + order.getCustomer().getContactInfo());
    }
}
