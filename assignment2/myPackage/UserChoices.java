package myPackage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// This class contains all static methods.
public class UserChoices {

    // Hashmaps are used to find the Patron by name and Book by ISBN
    public static Map<String, Patron> patronMap=new HashMap<>();
    public static Map<String, Book> bookMap=new HashMap<>();

    // If the user want to add a new Book to Library
     public static void userChoice1(BookManagement bookManagement, Scanner input) {
        try{
            System.out.println("Enter title, author, ISBN, quantity line by line : ");
            String title=input.next();
            String author=input.next();
            String isbn=input.next();
            int quantity=input.nextInt();
            bookManagement.addBook(title, author, isbn, quantity);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // If User want to remove a book from Library
    public static void userChoice2(BookManagement bookManagement, Scanner input) {
        try {
            System.out.print("Enter ISBN of the book to Delete : ");
            String isbn=input.next();
            bookManagement.removeBook(isbn);
        } catch (Exception e) {
            System.out.println(e);
            input.nextLine();
        }
    }

    // display all the books in library
    public static void userChoice3(BookManagement bookManagement) {
        bookManagement.displayBooks();
    }

    // Add a new Patron
    public static void userChoice4(PatronManagement patronManagement, Scanner input) {
        try {
            System.out.println("Enter id, name line by line : ");
            int id=input.nextInt();
            String name=input.next();
            patronManagement.addPatron(id, name);
        } catch (Exception e) {
            System.out.println(e);
            input.nextLine();
        }
    }

    // remove a Patron
    public static void userChoice5(PatronManagement patronManagement, Scanner input) {
        try {
            System.out.println("Enter id to Delete : ");
            int id=input.nextInt();
            patronManagement.removePatron(id);
        } catch (Exception e) {
            System.out.println(e);
            input.nextLine();
        }
    }

    // Displaying all the patrons with their details
    public static void userChoice6(PatronManagement patronManagement) {
        patronManagement.displayPatrons();
    }

    // Borrow a Book from Library by patron
    public static void userChoice7(BookManagement bookManagement, Scanner input) {
        try {
            System.out.println("Enter patronName and bookISBN line by line : ");
            String patronName=input.next();
            String bookIsbn=input.next();
            Book book=bookMap.get(bookIsbn);
            Patron patron=patronMap.get(patronName);
            bookManagement.borrowBook(patron, book);
        } catch (Exception e) {
            System.out.println(e);
            input.nextLine();
        }
    }

    // Return book to library by patron
    public static void userChoice8(BookManagement bookManagement, Scanner input) {
        try {
            System.out.println("Enter patronName and bookISBN line by line : ");
            String patronName=input.next();
            String bookIsbn=input.next();
            Book book=bookMap.get(bookIsbn);
            Patron patron=patronMap.get(patronName);
            bookManagement.returnBook(patron, book);
        } catch (Exception e) {
            System.out.println(e);
            input.nextLine();
        }
    }
}
