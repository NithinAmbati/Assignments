import java.util.*;
import myPackage.*;


// This is Our Library.
class Library {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);

        // Creating a Instance of our Books and Patrons
        BookManagement bookManagement=new BookManagement();
        PatronManagement patronManagement=new PatronManagement();

        do {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("1. Add a new Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Display available Books");
            System.out.println("4. Add a new Patron");
            System.out.println("5. Remove a Patron");
            System.out.println("6. Display Patrons");
            System.out.println("7. Borrow a Book");
            System.out.println("8. Return a Book");
            System.out.println("9. Exit the Program");
            System.out.println();

            System.out.print("Enter 1 to 9 to go forward :");
            int userChoice;
            try {
                userChoice=input.nextInt();
            } catch (Exception e) {
                System.out.println("Input Mismatch Exception");
                break;
            }

            System.out.println();
            if(userChoice==9) break;
            switch (userChoice) {
                case 1:
                    UserChoices.userChoice1(bookManagement, input);
                    break;
                case 2:
                    UserChoices.userChoice2(bookManagement, input);
                    break;
                case 3:
                    UserChoices.userChoice3(bookManagement);
                    break;
                case 4:
                    UserChoices.userChoice4(patronManagement, input);
                    break;
                case 5:
                    UserChoices.userChoice5(patronManagement, input);
                    break;
                case 6:
                    UserChoices.userChoice6(patronManagement);
                    break;
                case 7:
                    UserChoices.userChoice7(bookManagement, input);
                    break;
                case 8:
                    UserChoices.userChoice8(bookManagement, input);
                    break;
                default:
                    break;
            }  
            
            
        } while(true);

    }
}