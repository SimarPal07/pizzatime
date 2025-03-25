import java.util.ArrayList;
import java.util.Scanner;

public class PizzaTime {


static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
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
        
    private static String Validcity(Scanner scanner) {
         //welcome
         while (true) {
            System.out.println("WELCOME TO PIZZA DELIVERY PLACE!!!");
            //input city, must be winnipeg
            System.out.println("Enter city (must be 'Winnipeg'): ");
            String City = scanner.nextLine().toLowerCase();
            //validate
            if (!City.equals("winnipeg")) { // Case-insensitive comparison
                System.out.println("That is out of our delivery radius. Try again.");
            } else {
                return "Winnipeg"; // Preserve correct capitalization in output
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
                        System.out.println("Street number must be between 1 and 999.");
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
       


