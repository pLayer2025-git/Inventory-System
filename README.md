# Inventory-System
A java console based inventory system
📦 Inventory Management System (Console-Based)A robust, lightweight Java application designed to manage product stock efficiently using the Java Collections Framework and Object Persistence.

🎯 Project OverviewThis system allows businesses to track their inventory through a command-line interface. It utilizes a HashMap for high-performance data retrieval and implements native Java Serialization to ensure data is not lost when the program is closed.

🛠️ Technical Implementation1. Data Structure: HashMap<Integer, Product>We chose a HashMap because it provides $O(1)$ time complexity for basic operations.Key: Product ID (Integer) — Ensures every product has a unique identifier.Value: Product Object — Stores name, quantity, and price.2. Persistence: File HandlingThe system uses ObjectOutputStream and ObjectInputStream to save the entire state of the inventory to a file named inventory_data.dat.Automatic Loading: When the app starts, it checks for an existing data file.Manual Save: Data is committed to the disk when the user selects the "Exit" option.3. Input Validation & Error HandlingData Integrity: Prevents duplicate Product IDs.Crash Prevention: Uses try-catch blocks within input methods to handle NumberFormatException (e.g., if a user enters "abc" for a price).🚀 Features✅ Create: Add new products with unique IDs.

✅ Read: View a formatted summary of all stock, including total inventory value.
✅ Update: Modify existing product quantities and prices.
✅ Delete: Remove discontinued items from the records.
✅ Data Recovery: Automatic state restoration upon startup.
