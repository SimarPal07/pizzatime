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
        if (!streetname[0].equals(streetinput)) {
            System.out.println("Not within radius, please try again.");
            continue;

        }
        if (!streetname[1].equals(streetinput)) {
            System.out.println("Not within radius, please try again.");
            continue;

        }
        if (!streetname[2].equals(streetinput)) {
            System.out.println("Not within radius, please try again.");
            continue;

        }
        if (!streetname[3].equals(streetinput)) {
            System.out.println("Not within radius, please try again.");
            continue;

        }
        if (!streetname[4].equals(streetinput)) {
            System.out.println("Not within radius, please try again.");
            continue;

        }
    
        }
        
        

    }
}

