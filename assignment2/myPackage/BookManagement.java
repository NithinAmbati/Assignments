package myPackage;
import java.util.ArrayList;
import java.util.List;

public class BookManagement implements Borrowable {

    // List of books declaring private , only this class will have the access of books list
    private List<Book> books;


    public BookManagement() {
        this.books=new ArrayList<>();
    }

    //Add Books function
    void addBook(String title, String author, String isbn, int quantity) {
        Book book=new Book(title, author, isbn, quantity);
        if(UserChoices.bookMap.get(isbn)!=null) {
            int ind=this.books.indexOf(book);
            this.books.get(ind).setQuantity(quantity+UserChoices.bookMap.get(isbn).getQuantity());
            book.setQuantity(quantity+UserChoices.bookMap.get(isbn).getQuantity());
            UserChoices.bookMap.put(isbn, book);
            
        }
        
        this.books.add(book);
        UserChoices.bookMap.put(book.getIsbn(), book);
        System.out.println("Book Added Successfully");
    }

    // Remove Books Funtion
    void removeBook(String isbn) {
        System.out.println("sdfsdf");
        for(int i=0;i<this.books.size();i++) {
            if(this.books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                UserChoices.bookMap.remove(isbn);
                System.out.println("Book removed Successfully");
            }
        }
        System.out.println("No Book Found with given ISBN");
    }

    // Getter funtion for books
    List<Book> getBooks() {
        return this.books;
    }

    
    void displayBooks() {
        if(this.books.size()==0) {
            System.out.println("No Books available.");
            return;
        }
        System.out.println("Below are the Availble books.");
        for (Book book : books) {
            System.err.println(book.toString());
        }
    }

    public void borrowBook(Patron patron, Book book) {
        int ind=this.books.indexOf(book);
        if(ind==-1) {
            System.out.println("Book Not Found.");
        }
        else if(this.books.get(ind).getQuantity()==0) {
            System.out.println("Books out of stock.");
        }
        else {
            this.books.get(ind).setQuantity(this.books.get(ind).getQuantity()-1);
            UserChoices.bookMap.put(book.getIsbn(), this.books.get(ind));
            patron.borrowBook(book);
        }
    }

    public void returnBook(Patron patron, Book book) {
        int ind=this.books.indexOf(book);
        if(patron.returnBook(book)==true) return; 
        this.books.get(ind).setQuantity(this.books.get(ind).getQuantity()+1);
        UserChoices.bookMap.put(book.getIsbn(), this.books.get(ind));
       
    }

}