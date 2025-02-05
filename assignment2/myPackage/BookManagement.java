package myPackage;
import java.util.ArrayList;
import java.util.List;


public class BookManagement implements Borrowable {

    // List of books declaring private, only this class will have the access of books list
    private List<Book> books;


    // construtor
    public BookManagement() {
        this.books=new ArrayList<>();
    }

    //Add Books function
    void addBook(String title, String author, String isbn, int quantity) {
        Book book=new Book(title, author, isbn, quantity);
        if(UserChoices.bookMap.get(isbn)!=null) { // Checking for if book already exist in library.
            // Instead of creating new book object I am increasing the quantity of the previous one.
            int ind=this.books.indexOf(book);
            this.books.get(ind).setQuantity(quantity+UserChoices.bookMap.get(isbn).getQuantity());
            book.setQuantity(quantity+UserChoices.bookMap.get(isbn).getQuantity());
            UserChoices.bookMap.put(isbn, book);
            return;
        }
        
        // If book not found then creating New book in Library.
        this.books.add(book);
        UserChoices.bookMap.put(book.getIsbn(), book);
        System.out.println("Book Added Successfully");
    }

    // Remove Books Funtion
    void removeBook(String isbn) {
        System.out.println("sdfsdf");
        for(int i=0;i<this.books.size();i++) {
            if(this.books.get(i).getIsbn().equals(isbn)) { // If book found in library then deleting from map and library.
                books.remove(i);
                UserChoices.bookMap.remove(isbn);
                System.out.println("Book removed Successfully");
                return;
            }
        }
        System.out.println("No Book Found with given ISBN");
    }

    // Getter funtion for books
    List<Book> getBooks() {
        return this.books;
    }

    // Printing all the available books
    void displayBooks() {
        if(this.books.size()==0) { // Checking for Unavailablility of books.
            System.out.println("No Books available.");
            return;
        }
        System.out.println("Below are the Availble books.");
        for (Book book : books) { // Printing all the books available in Library.
            System.err.println(book.toString());
        }
    }

    public void borrowBook(Patron patron, Book book) {
        int ind=this.books.indexOf(book);
        if(ind==-1) { // Checking for is Book found in Library.
            System.out.println("Book Not Found in Library.");
        }
        else if(this.books.get(ind).getQuantity()==0) { // Chekcing for stock is available.
            System.out.println("Books out of stock.");
        }
        else { // if everyhng is fine then we can update the data.
            this.books.get(ind).setQuantity(this.books.get(ind).getQuantity()-1); // updateing the books
            UserChoices.bookMap.put(book.getIsbn(), this.books.get(ind)); // updating BooksMap.
            patron.borrowBook(book); // updating the booksBorrowed by the patron
        }
    }

    public void returnBook(Patron patron, Book book) {
        int ind=this.books.indexOf(book);
        if(patron.returnBook(book)==true) return; // true means book is not borrwoed by Patron so No update needed.
        this.books.get(ind).setQuantity(this.books.get(ind).getQuantity()+1);   // otherwise updating library. updating patron in above funtion call.
        UserChoices.bookMap.put(book.getIsbn(), this.books.get(ind));
       
    }

}