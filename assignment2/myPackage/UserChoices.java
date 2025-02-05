package myPackage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class UserChoices {

    public static Map<String, Patron> patronMap=new HashMap<>();
    public static Map<String, Book> bookMap=new HashMap<>();

     public static void userChoice1(BookManagement bookManagement, Scanner input) {
        System.out.println("Enter title, author, ISBN, quantity line by line : ");
        String title=input.next();
        String author=input.next();
        String isbn=input.next();
        int quantity=input.nextInt();
        bookManagement.addBook(title, author, isbn, quantity);
    }

    public static void userChoice2(BookManagement bookManagement, Scanner input) {
        System.out.print("Enter ISBN of the book to Delete : ");
        String isbn=input.next();
        bookManagement.removeBook(isbn);
    }

    public static void userChoice3(BookManagement bookManagement) {
        bookManagement.displayBooks();
    }

    public static void userChoice4(PatronManagement patronManagement, Scanner input) {
        System.out.println("Enter id, name line by line : ");
        int id=input.nextInt();
        String name=input.next();
        patronManagement.addPatron(id, name);
    }

    public static void userChoice5(PatronManagement patronManagement, Scanner input) {
        System.out.println("Enter id to Delete : ");
        int id=input.nextInt();
        patronManagement.removePatron(id);
    }

    public static void userChoice6(PatronManagement patronManagement) {
        patronManagement.displayPatrons();
    }

    public static void userChoice7(BookManagement bookManagement, Scanner input) {
        System.out.println("Enter patronName and bookISBN line by line : ");
        String patronName=input.next();
        String bookIsbn=input.next();

        Book book=bookMap.get(bookIsbn);
        Patron patron=patronMap.get(patronName);
        bookManagement.borrowBook(patron, book);
    }

    public static void userChoice8(BookManagement bookManagement, Scanner input) {
        System.out.println("Enter patronName and bookISBN line by line : ");
        String patronName=input.next();
        String bookIsbn=input.next();

        Book book=bookMap.get(bookIsbn);
        Patron patron=patronMap.get(patronName);
        bookManagement.returnBook(patron, book);
    }
}
