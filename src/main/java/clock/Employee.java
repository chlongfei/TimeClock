package clock;

import java.util.ArrayList;

public class Employee {

    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private ArrayList<Punch> punchCards;


    public Employee(){
        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = 0;
        punchCards = new ArrayList<Punch>();
    }

    public Employee(String newfirstName, String newlastName, String newEmail, int newPhoneNumber){
        this();
        firstName = newfirstName;
        lastName = newlastName;
        email = newEmail;
        phoneNumber = newPhoneNumber;
    }

    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }
    public void setLastName(String newLastName){
        lastName = newLastName;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }
    public void setPhoneNumber(int newPhoneNumber){
        phoneNumber = newPhoneNumber;
    }
    public void setPunchCards(Punch punchCard){
        punchCards.add(punchCard);
    }

}
