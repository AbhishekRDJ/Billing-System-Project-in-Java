import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer: " + name + "\nAddress: " + address;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product: " + name + "\nPrice: $" + price;
    }
}

class InvoiceItem {
    private Product product;
    private int quantity;

    public InvoiceItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateItemTotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + "\t\t" + product.getPrice() + "\t\t" + quantity + "\t\t" + calculateItemTotal();
    }
}

class Invoice {
    private Customer customer;
    private List<InvoiceItem> items;

    public Invoice(Customer customer) {
        this.customer = customer;
        items = new ArrayList<>();
    }

    public void addItem(InvoiceItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (InvoiceItem item : items) {
            total += item.calculateItemTotal();
        }
        return total;
    }

    public void printInvoice() {
        System.out.println("Invoice for: ");
        System.out.println(customer);
        System.out.println("\n|Product|\t|Price|\t|Quantity|\t|Total|");
        for (InvoiceItem item : items) {
            System.out.println(item+"\t\t");
        }
        System.out.println("\nTotal: Rs." + calculateTotal());
    }
}


public class BillingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer information from the user
        System.out.print("||Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("||Enter customer address: ");
        String customerAddress = scanner.nextLine();
        Customer customer = new Customer(customerName, customerAddress);

        // Create an invoice and add items
        Invoice invoice = new Invoice(customer);

        while (true) {
        
            System.out.print("Enter product name (or type 'done' to finish): ");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Product product = new Product(productName, productPrice);
            InvoiceItem item = new InvoiceItem(product, quantity);
            invoice.addItem(item);
        }

        // Print the invoice
        invoice.printInvoice();
        
        scanner.close();
    }
}
