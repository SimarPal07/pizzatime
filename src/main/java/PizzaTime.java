//Simar Pal
import java.util.ArrayList;
import java.util.Scanner;

//this class stores information for each order, such as city, street address, postal code, pizza size, toppings, and total cost.
class Order {
    private String city;
    private String streetAddress;
    private String postalCode;
    private String pizzaSize;
    private ArrayList<String> toppings;
    private double totalCost;

    public Order(String city, String streetAddress, String postalCode, String pizzaSize, ArrayList<String> toppings, double totalCost) {
        //takes in values for all the fields and assigns them to the instance variables.
        this.city = city;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.pizzaSize = pizzaSize;
        this.toppings = toppings;
        this.totalCost = totalCost;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public double getTotalCost() {
        return totalCost;
    }

    
    //display order history formatting
    public String toString() {
        return "Order: " + pizzaSize + " pizza, " + (toppings.isEmpty() ? "No toppings" : String.join(", ", toppings)) + "\n" +
                "Delivery Address: " + streetAddress + ", " + city + ", " + postalCode + "\n" +
                "Total Cost: $" + totalCost;
    }
}
public class PizzaTime {
// This ArrayList stores all the orders that have been placed by users. It's used to keep track of past orders so that they can be viewed later.
static ArrayList<Order> orderHistory = new ArrayList<>();
static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        while (true) {
            // Menu options
            System.out.println("\nPizza Order System");
            System.out.println("1. Place a new order");
            System.out.println("2. View order history");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            

            if (choice.equals("1")) {
                //place order            
                placeOrder();  
            } else if (choice.equals("2")) {
                //view order history
                viewOrderHistory();  
            } else if (choice.equals("3")) {
                System.out.println("Thank you for using the Pizza Ordering System!");
                break;  
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void placeOrder() {
        // Get and validate city
        String city = Validcity(scanner);

        // Get and validate street
        String streetAddress = Validstreet(scanner);

        // Get and validate postal code
        String postalCode = ValidPostalCode(scanner);

        // Order customization: size and toppings
        String pizzaSize = selectPizzaSize(scanner);
        ArrayList<String> toppings = selectToppings(scanner);

        // Calculate total cost
        double totalCost = calculateTotalCost(pizzaSize, toppings);

        // Create an Order object and add it to the order history
        Order newOrder = new Order(city, streetAddress, postalCode, pizzaSize, toppings, totalCost);
        orderHistory.add(newOrder);

        // Confirmation message
        System.out.println("\nDelivery Confirmed");
        System.out.println(streetAddress + ", " + city + ", " + postalCode);
        System.out.println("Your order is on the way!");

        // Display order summary with total cost
        System.out.println("\n--- Order Summary ---");
        System.out.println("Pizza Size: " + pizzaSize);
        System.out.println("Toppings: " + (toppings.isEmpty() ? "None" : String.join(", ", toppings)));
        System.out.println("Total Cost: $" + totalCost);
    }

    private static void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders in history.");
        } else {
            System.out.println("\n--- Order History ---");
            for (Order order : orderHistory) {
                System.out.println(order);
                System.out.println("--------------------");
            }
        }
    }
        
    private static String Validcity(Scanner scanner) {
         //welcome
         while (true) {
            System.out.println("WELCOME TO PIZZA DELIVERY PLACE!!!");
            //input city, must be winnipeg
            System.out.println("Enter city (must be 'Winnipeg'): ");
            String City = scanner.nextLine().toLowerCase().trim();
            //validate
            if (!City.equals("winnipeg")) { 
                System.out.println("That is out of our delivery radius. Try again.");
            } else {
                return "Winnipeg"; 
            }
         }    
    }
    
    private static String Validstreet(Scanner scanner) {

    String[] validStreets = {"Burrows", "Main", "Henderson", "Magnus", "Mountain"};

        while (true) {
            // Input street name
            System.out.println("Enter street name (Must be 'Burrows', 'Main', 'Henderson', 'Magnus', 'Mountain'): ");
            String streetInput = scanner.nextLine().trim().toLowerCase(); // Convert input to lowercase for case-insensitive comparison

            String matchedStreet = null;
            // Check if the input matches any valid street name
            for (String street : validStreets) {
                if (street.toLowerCase().equals(streetInput)) { 
                    matchedStreet = street; 
                    break;
                }
            }
            //if user input dont match any validstreets than out of bounds
            if (matchedStreet == null) {
                System.out.println("That street is out of bounds. Please try again.");
                continue; 
            }

            // Get street number
            int streetNum;
            while (true) {
                System.out.println("Enter street number (between 1 and 1000):");
                //check if int
                if (scanner.hasNextInt()) {
                    streetNum = scanner.nextInt();
                    scanner.nextLine();
                    //check if within bounds
                    if (streetNum > 0 && streetNum < 1000) {
                        return matchedStreet + " " + streetNum; 
                    } else {
                        System.out.println("Street number must be between 1 and 1000.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); 
                }
                
            }
        }
        
    }

        

   
    private static String ValidPostalCode(Scanner scanner) {
         // Define valid postal code prefixes, winnipeg prefixes
    String[] validPrefixes = {"R2", "R3", "R4"};
        while (true){
             //get postal code
        System.out.println("Enter postal code (must be formatted A1A 1A1)");

        // set input top uppercase, and trim any additional spaces
        String postalCode = scanner.nextLine().toUpperCase().trim();
        //Canadian postal code pattern: letter-number-letter-space-number-letter-number
        String regex = "^[A-Z]\\d[A-Z] \\d[A-Z]\\d$";
          
        // Check if the postal code matches the format
        if (postalCode.matches(regex)) {
            // Extract the first two characters (prefix) of the postal code
            String prefix = postalCode.substring(0, 2);
            
            // Validate that the prefix matches an acceptable area
            boolean isValidPrefix = false;
            for (String validPrefix : validPrefixes) {
                if (prefix.equals(validPrefix)) {
                    isValidPrefix = true;
                    break;
                }
            }

            // If valid prefix, return the postal code; otherwise, ask for re-entry
            if (isValidPrefix) {
                return postalCode;
            } else {
                System.out.println("This postal code is outside of our delivery area. Please enter a valid postal code.");
            }
        } else {
            System.out.println("Invalid format. Please enter in A1A 1A1 format.");
        }
    }
        
        }
        
    
    
    // Pizza size selection
    private static String selectPizzaSize(Scanner scanner) {
        while (true) {
            System.out.println("\nSelect pizza size (Small, Medium, Large): ");
            String size = scanner.nextLine().trim().toLowerCase();

            switch (size) {
                case "small":
                case "medium":
                case "large":
                    return size.substring(0, 1).toUpperCase() + size.substring(1);  // Capitalize the first letter
                default:
                    System.out.println("Invalid size. Please choose Small, Medium, or Large.");
            }
        }
    }

    // Topping selection
    private static ArrayList<String> selectToppings(Scanner scanner) {
        ArrayList<String> toppings = new ArrayList<>();
        String[] availableToppings = {"Pepperoni", "Mushrooms", "Onions", "Olives", "Cheese", "Peppers"};

        while (true) {
            System.out.println("\nAvailable toppings: Pepperoni, Mushrooms, Onions, Olives, Cheese, Peppers");
            System.out.println("Enter a topping to add (or type 'done' to finish): ");
            String topping = scanner.nextLine().trim();
            //if done adding toppings, finish. else, add toppings to a variable and continue till done
            if (topping.equalsIgnoreCase("done")) {
                break;
            
            } else {
                //if a topping matches a topping on the topping list adds to list
                boolean validTopping = false;
                for (String availableTopping : availableToppings) {
                    if (topping.equalsIgnoreCase(availableTopping)) {
                        toppings.add(availableTopping);
                        validTopping = true;
                        break;
                    }
                }
                if (!validTopping) {
                    System.out.println("Invalid topping. Please choose from the available toppings.");
                }
            }
        }
        return toppings;
    }

    // Calculate the total cost based on size and toppings
    private static double calculateTotalCost(String pizzaSize, ArrayList<String> toppings) {
        double sizeCost = 0;
        double toppingCost = 1.5; 

        // Determine the cost based on pizza size
        switch (pizzaSize.toLowerCase()) {
            case "small":
                sizeCost = 8.00;
                break;
            case "medium":
                sizeCost = 10.00;
                break;
            case "large":
                sizeCost = 12.00;
                break;
        }

        // Calculate the total cost
        return sizeCost + (toppings.size() * toppingCost);
    }
        
    }
       


