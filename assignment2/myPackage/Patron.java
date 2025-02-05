package myPackage;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private int id;
    private String name;
    private List<Book> booksBorrowed;

    // Construtor
    Patron(int id, String name) {
        this.id=id;
        this.name=name;
        this.booksBorrowed=new ArrayList<>();
    }

    //Setter functions
    void setId(int id) {
        this.id=id;
    }

    void setName(String name) {
        this.name=name;
    }

    //Getter Functions
    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }


    // take book from library
    public void borrowBook(Book book) {
        if(this.booksBorrowed.indexOf(book)!=-1) { // Checking for if patron have already borrowed that book.
            System.out.println("Book Already Exist with given Patron");
            return;
        }

        // If not then borrowing that book and updating patron data.
        this.booksBorrowed.add(book);
        System.out.println("Book Borrowed succesfully.");
        
    }

    // return back to library
    public boolean returnBook(Book book) {
        if(this.booksBorrowed.indexOf(book)==-1) { // Checking for if that book is borrowed by patron or not.
            System.out.println("Book Not borrowed from the given Patron."); 
            // if book is not borrowed then i return true and returing from there in the folowing function.
            return true;
        }
        this.booksBorrowed.remove(book); // if bookBorrowed then updating the patron data.
        System.out.println("Book returned Successfully");
        System.out.println();
        return false;
    }

    // Getter function
    List<Book> getBooksBorrowed() {
        return this.booksBorrowed;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + " | Name : " + this.getName() + " | Books Borrowed : " + this.booksBorrowed.size();
    }

}
