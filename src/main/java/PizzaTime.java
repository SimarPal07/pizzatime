import java.util.Scanner;
public class PizzaTime {


static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
    
        

        //welcome
        while (true) {
        System.out.println("WELCOME TO PIZZA DELIVERY PLACE!!!");
        //input city, must be winnipeg
        System.out.println("Enter city (must be 'Winnipeg'): ");
        String City = scanner.nextLine();
        //validate
        if (!City.equals("Winnipeg")) {
            System.out.println("Unfortunately, that is out of our delivery radius. Try again.");
            continue;
        }
     
        //build array for categorized streets
        String [] streetname;
        streetname = new String[]{"Burrows", "Main", "Henderson", "Magnus", "Mountain"};
        //input street name
        System.out.println("Enter street name: ");
        String streetinput = scanner.nextLine();
        //create boolean
        Boolean validStreet = false;
        //iterate over elements of arrays or collections, without having to manually manage the index.
        for (String street : streetname) {
            if (street.equals(streetinput)) {
                validStreet = true;
                break;

            }


        }
        if (!validStreet) {
            System.out.println("Out of bounds, please try again.");
            continue;
        }
        }
        
        

    }
}

