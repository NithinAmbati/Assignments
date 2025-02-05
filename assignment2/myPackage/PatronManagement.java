package myPackage;
import java.util.ArrayList;
import java.util.List;


public class PatronManagement {
    // all the patrons are in users List
    private List<Patron> users;

    public PatronManagement() {
        this.users=new ArrayList<>();
    }

    // Add new Patron here
    void addPatron(int id, String name) {
        Patron patron=new Patron(id, name);
        this.users.add(patron);
        UserChoices.patronMap.put(name, patron);
        System.out.println("User Added succesfully.");
    }

    void removePatron(int id) {
        for(int i=0;i<this.users.size();i++) {
            if(this.users.get(i).getId()==id) { // if patron found with his id deleting the patron
                UserChoices.patronMap.remove(this.users.get(i).getName());
                this.users.remove(i);
                System.out.println("User Deleted Succesfully.");
            } 
        }
        System.out.println("User Not found");
    }

    void displayPatrons() {
        if(this.users.size()==0) { // condition for No patrons.
            System.out.println("No Patrons Found.");
            return;
        }
        System.out.println("Below are list of Patrons");
        for (Patron patron : users) {
            System.out.println(patron.toString());
        }
    }
}
