import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class representing an item in the menu
class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Class representing the billing system
public class Bill {
    private static Map<String, MenuItem> menu;
//We can change following items as we want to add.(As our preferance)
    static {
        menu = new HashMap<>();
        menu.put("Tea", new MenuItem("Tea", 10.0));
        menu.put("Masala Tea", new MenuItem("Masala Tea", 15.0));
        menu.put("BunMaska", new MenuItem("BunMaska", 20.0));
        menu.put("Coffe", new MenuItem("Coffe", 10.0));
        menu.put("Cold Coffee", new MenuItem("Cold Coffee", 20.0));        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display the menu
        System.out.println("Menu:");
        for (String itemName : menu.keySet()) {
            MenuItem item = menu.get(itemName);
            System.out.println(item.getName() + " - Rs" + item.getPrice());
        }

        // Take orders
        Map<String, Integer> orders = new HashMap<>();
        while (true) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String itemName = scanner.nextLine();

            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            if (!menu.containsKey(itemName)) {
                System.out.println("Invalid item name. Please try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            orders.put(itemName, quantity);
        }

        // Calculate and display the bill
        double totalBill = generateBill(orders);
        System.out.println("Total Bill: Rs" + totalBill);

        scanner.close();
    }

    private static double generateBill(Map<String, Integer> orders) {
        double totalBill = 0.0;
        for (String itemName : orders.keySet()) {
            MenuItem item = menu.get(itemName);
            int quantity = orders.get(itemName);
            totalBill += item.getPrice() * quantity;
        }
        return totalBill;
    }
}
