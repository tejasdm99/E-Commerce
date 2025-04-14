import com.eCommerce.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Products
        Product laptop = new Product("Laptop", 1000.0, 10);
        Product phone = new Product("Smartphone", 500.0, 20);
        Product headphones = new Product("Headphones", 100.0, 30);

        List<Product> products = Arrays.asList(laptop, phone, headphones);

        // Customer Info
        System.out.println("Enter Customer Details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, email, address);
        Order order = new Order(customer);

        // Step 1: Select products
        System.out.println("\nStep 1: Customer selects products to add to the order.");
        while (true) {
            System.out.println("\nAvailable Products:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getDetails());
            }
            System.out.print("Enter product number to add to cart (or 0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice < 1 || choice > products.size()) {
                System.out.println("Invalid choice.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());
            order.addProduct(products.get(choice - 1), qty);
        }

        // If no products selected, exit
        if (order.getItems().isEmpty()) {
            System.out.println("\nNo products selected. Exiting without processing the order.");
            return;
        }

        // Ask for payment method
        System.out.print("\nSelect payment type (credit/paypal): ");
        String paymentType = scanner.nextLine().toLowerCase();

        IPaymentProcessor paymentProcessor;
        switch (paymentType) {
            case "credit":
                paymentProcessor = new CreditCardProcessor();
                break;
            case "paypal":
                paymentProcessor = new PayPalProcessor();
                break;
            default:
                System.out.println("Invalid payment type.");
                return;
        }

        OrderProcessor processor = new OrderProcessor(paymentProcessor, new InvoiceGenerator());
        processor.processOrder(order);

        scanner.close();
    }
}