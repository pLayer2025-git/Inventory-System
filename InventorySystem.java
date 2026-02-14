import java.io.*;
import java.util.*;

/**
 * 1. Design Product class with attributes.
 */
class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Seters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-15s | Qty: %-5d | Price: $%.2f",
                id, name, quantity, price);
    }
}

public class InventorySystem {
    // 2. Store products using HashMap (Key = Product ID)
    private static Map<Integer, Product> inventory = new HashMap<>();
    private static final String DATA_FILE = "inventory_data.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData(); // 6. Persist data (Load on startup)

        while (true) {
            displayMenu();
            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> updateProduct();
                case 3 -> deleteProduct();
                case 4 -> viewInventory();
                case 5 -> {
                    saveData(); // 6. Persist data (Save on exit)
                    System.out.println("Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Inventory Management System ===");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View Inventory Summary");
        System.out.println("5. Exit & Save");
    }

    // 3 & 4. Implement Add Operation with Unique ID Validation
    private static void addProduct() {
        int id = getIntInput("Enter Product ID: ");
        if (inventory.containsKey(id)) {
            System.out.println("Error: Product ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = scanner.next();
        int qty = getIntInput("Enter Quantity: ");
        double price = getDoubleInput("Enter Price: ");

        inventory.put(id, new Product(id, name, qty, price));
        System.out.println("Product added successfully.");
    }

    // 3. Update Operation
    private static void updateProduct() {
        int id = getIntInput("Enter Product ID to update: ");
        if (inventory.containsKey(id)) {
            Product p = inventory.get(id);
            p.setQuantity(getIntInput("New Quantity: "));
            p.setPrice(getDoubleInput("New Price: "));
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // 3. Delete Operation
    private static void deleteProduct() {
        int id = getIntInput("Enter Product ID to delete: ");
        if (inventory.remove(id) != null) {
            System.out.println("Product removed.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // 5. Display Inventory Summary
    private static void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        double totalValue = 0;
        for (Product p : inventory.values()) {
            System.out.println(p);
            totalValue += (p.getPrice() * p.getQuantity());
        }
        System.out.printf("Total Inventory Value: $%.2f\n", totalValue);
    }

    // 6. Persistence Logic
    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            inventory = (HashMap<Integer, Product>) ois.readObject();
        } catch (Exception e) {
            System.err.println("No existing data found or error loading file.");
        }
    }

    // 7. Handle Invalid User Inputs
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a decimal price.");
            }
        }
    }
}