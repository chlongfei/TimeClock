package clock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Employee {

    private static final int SECONDSINHOUR = 3600;
    private static final int SECONDSINMINUTE = 60;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private ArrayList<Punch> punchCards;


    public Employee(){
        id = 0;
        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        punchCards = new ArrayList<Punch>();
    }

    public Employee(String newfirstName, String newlastName, String newEmail, String newPhoneNumber){
        this();
        firstName = newfirstName;
        lastName = newlastName;
        email = newEmail;
        phoneNumber = newPhoneNumber;
    }

    public void setId(int newId){
        id = newId;
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
    public void setPhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber;
    }
    public void setPunchCards(Punch punchCard){
        punchCards.add(punchCard);
    }

    public String setPunch(){
        Punch newPunch = null;
        System.err.println("<debug> Has Open Punch: " + hasOpenPunch());
        if(!hasOpenPunch()) {
            punchCards.add(new Punch());
            newPunch = punchCards.get(punchCards.size()-1);
            return "ACCEPTED IN (" + newPunch.getDate() + " " + newPunch.getTime() + ")";
        }else{
            newPunch = punchCards.get(punchCards.size()-1);
            newPunch.setClockOut();
            return "ACCEPTED OUT (" + newPunch.getDate() + " " + newPunch.getTime() + ")";
        }
    }

    public boolean hasOpenPunch(){
        try{
            return !punchCards.get(punchCards.size()-1).isClockedOut();
        }catch(Exception e){
            return false;
        }
    }


    public String getTotHoursWorked(){
        double hoursWorked = 0;
        for(Punch each:punchCards){
            hoursWorked += each.getInSeconds();
        }
        hoursWorked /= SECONDSINHOUR;
        BigDecimal bd = new BigDecimal(hoursWorked).setScale(2, RoundingMode.HALF_UP);
        if(bd.doubleValue() < 1.0){
            return getTotMinutesWorked();
        }
        return bd.doubleValue() + "Hours";
    }

    public String getTotMinutesWorked(){
        double hoursWorked = 0;
        for(Punch each:punchCards){
            hoursWorked += each.getInSeconds();
        }
        hoursWorked /= SECONDSINMINUTE;
        BigDecimal bd = new BigDecimal(hoursWorked).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue() + " Minutes";
    }

    @Override
    public String toString() {
        return "Employee <" + firstName + " " + lastName + "> :\n"
                + "Email: " + email + "\nPhone Number: " + phoneNumber + "\n"
                + "Last Punch: " + punchCards.get(punchCards.size()-1).getDate() + " "
                + punchCards.get(punchCards.size()-1).getTime() + "\n"
                + "Total Hours Worked: " + getTotHoursWorked();
    }

}
