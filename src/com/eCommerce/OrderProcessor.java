package com.eCommerce;

public class OrderProcessor {
    private IPaymentProcessor paymentProcessor;
    private InvoiceGenerator invoiceGenerator;

    public OrderProcessor(IPaymentProcessor paymentProcessor, InvoiceGenerator invoiceGenerator) {
        this.paymentProcessor = paymentProcessor;
        this.invoiceGenerator = invoiceGenerator;
    }

    public void processOrder(Order order) {
        System.out.println("\nStep 2: Checking stock and processing order...");

        for (OrderItem item : order.getItems()) {
            if (!item.product.checkStock(item.quantity)) {
                System.out.println("Order failed: Not enough stock for " + item.product.getName());
                return;
            }
        }

        double total = order.getTotalAmount();
        boolean paid = paymentProcessor.processPayment(total);

        if (paid) {
            for (OrderItem item : order.getItems()) {
                item.product.updateStock(item.quantity);
            }
            System.out.println("Step 3: Payment successful.");
            invoiceGenerator.generateInvoice(order);
            System.out.println("Step 4: Invoice generated and stock updated.");
        } else {
            System.out.println("Payment failed. Order not completed.");
        }
    }
}
