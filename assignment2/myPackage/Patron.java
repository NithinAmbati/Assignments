package myPackage;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private int id;
    private String name;
    private List<Book> booksBorrowed;

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
        if(this.booksBorrowed.indexOf(book)!=-1) {
            System.out.println("Book Already Exist with given Patron");
            return;
        }
        this.booksBorrowed.add(book);
        System.out.println("Book Borrowed succesfully.");
        
    }

    // return back to library
    public boolean returnBook(Book book) {
        if(this.booksBorrowed.indexOf(book)==-1) {
            System.out.println("Book Not borrowed from the given Patron.");
            return true;
        }
        this.booksBorrowed.remove(book);
        System.out.println("Book returned Successfully");
        System.out.println();
        return false;
    }

    List<Book> getBooksBorrowed() {
        return this.booksBorrowed;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + " | Name : " + this.getName() + " | Books Borrowed : " + this.booksBorrowed.size();
    }

}
