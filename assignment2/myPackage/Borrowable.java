package myPackage;


interface Borrowable {
    void borrowBook(Patron patron, Book book);
    void returnBook(Patron patron, Book book);
}

