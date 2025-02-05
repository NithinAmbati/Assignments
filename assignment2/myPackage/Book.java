package myPackage;

public class Book {

    // declaration of attributes of class
    private String title, author , isbn;
    private int quantity;

    //Constructor
    Book(String title, String author, String isbn, int quantity) {
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.quantity=quantity;
    }

    //setter functions
    void setTitle(String title){
        this.title=title;
    }

    void setAuthor(String author) {
        this.author=author;
    }

    void setIsbn(String isbn) {
        this.isbn=isbn;
    }

    void setQuantity(int q) {
        this.quantity=q;
    }

    //Getter functions
    String getTitle() {
        return this.title;
    }

    String getAuthor() {
        return this.author;
    }

    String getIsbn() {
        return this.isbn;
    }

    int getQuantity() {
        return this.quantity;
    }

    // Overriding the toString function to display the book object
    @Override
    public String toString() {
        return "Title: " + this.getTitle() + " | author : " + this.getAuthor() + " | ISBN : " + this.getIsbn() + " | quantity : " + this.getQuantity();
    }

}

