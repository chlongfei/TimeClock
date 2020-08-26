package clock;

import org.apache.commons.text.WordUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private boolean currIn;

    /**
     * Constructor - initializes employee object with default values
     */
    public Employee(){
        id = 0;
        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        punchCards = new ArrayList<Punch>();
        currIn = false;
    }

    /**
     * Constructor - initializes employee object with employee profile values
     * @param newFirstName employee first name
     * @param newLastName employee last name
     * @param newEmail employee email
     * @param newPhoneNumber employee phone number
     */
    public Employee(String newFirstName, String newLastName, String newEmail, String newPhoneNumber){
        this();
        firstName = WordUtils.capitalizeFully(newFirstName.trim(),' ');
        lastName = WordUtils.capitalizeFully(newLastName.trim(),' ');
        email = newEmail.trim();
        phoneNumber = newPhoneNumber.trim();
    }

    /**
     * Constructor - initializes employee object with employee profile values and employee id
     * @param newId employee id
     * @param newFirstName employee first name
     * @param newLastName employee last name
     * @param newEmail employee email
     * @param newPhoneNumber employee phone number
     */
    public Employee(int newId, String newFirstName, String newLastName, String newEmail, String newPhoneNumber){
        this(newFirstName, newLastName, newEmail, newPhoneNumber);
        id = newId;
    }

    /**
     * Sets employee id
     * @param newId employee id
     */
    public void setId(int newId){
        id = newId;
    }

    /**
     * @return employee id
     */
    public int getId(){
        return id;
    }

    /**
     * Sets employee first name
     * @param newFirstName
     */
    public void setFirstName(String newFirstName){
        firstName = newFirstName.trim();
    }

    /**
     * @return employee first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Sets employee last name
     * @param newLastName employee last name
     */
    public void setLastName(String newLastName){
        lastName = newLastName.trim();
    }

    /**
     * @return employee last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Sets employee email
     * @param newEmail employee email
     */
    public void setEmail(String newEmail){
        email = newEmail.trim();
    }

    /**
     * @return employee email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets employee phone number
     * @param newPhoneNumber employee phone numebr
     */
    public void setPhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber.trim();
    }

    /**
     * @return employee phone number
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Adds punch card to employee file
     * @param punchCard employee punch card
     */
    public void setPunchCards(Punch punchCard){
        punchCards.add(punchCard);
    }

    /**
     * Sets a punch in/out
     * @return confirmation prompt of successful punch
     */
    public String setPunch(){
        Punch newPunch = null;
        if(!hasOpenPunch()) {
            punchCards.add(new Punch());
            newPunch = punchCards.get(punchCards.size()-1);
            currIn = true;
            return "ACCEPTED-IN (" + getFirstName() + ", " + getLastName() + " | " + newPunch.getDate() + " "
                    + newPunch.getTime() + ")";
        }else{
            newPunch = punchCards.get(punchCards.size()-1);
            newPunch.setClockOut();
            currIn = false;
            return "ACCEPTED-OUT (" + getFirstName() + ", " + getLastName() + " | " + newPunch.getDate() + " "
                    + newPunch.getTime() + ")";
        }
    }

    /**
     * Determines if a punch card is present that has not been closed
     * @return boolean representing open punch card
     */
    public boolean hasOpenPunch(){
        try{
            return !punchCards.get(punchCards.size()-1).isClockedOut();
        }catch(Exception e){
            return false;
        }
    }


    /**
     * Calculates total employee has worked, if value too small for hours, minutes will be
     * returned instead
     * @return string description of total hours worked
     */
    public String getTotHoursWorked(){
        double hoursWorked = 0;
        for(Punch each:punchCards){
            if (each.isClockedOut()) {
                hoursWorked += each.getInSeconds();
            }
        }
        hoursWorked /= SECONDSINHOUR;
        BigDecimal bd = new BigDecimal(hoursWorked).setScale(2, RoundingMode.HALF_UP);
        if(bd.doubleValue() < 1.0){
            return getTotMinutesWorked();
        }
        return bd.doubleValue() + "Hours";
    }

    /**
     * @return string description of total minutes worked
     */
    public String getTotMinutesWorked(){
        double minutesWorked = 0;
        for(Punch each:punchCards){
            if(each.isClockedOut()){
                minutesWorked += each.getInSeconds();
            }
        }
        minutesWorked /= SECONDSINMINUTE;
        BigDecimal bd = new BigDecimal(minutesWorked).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue() + " Minutes";
    }

    /**
     * @return string description of the most recent punch
     */
    String getLastPunch(){
        try{
            return punchCards.get(punchCards.size()-1).getDate()
                    + " " + punchCards.get(punchCards.size()-1).getTime();
        }catch(IndexOutOfBoundsException ioobe){
            return "(none)";
        }
    }

    /**
     *
     * @return boolean representing if employee is currently working or not
     */
    public boolean isCurrIn() {
        return currIn;
    }

    /**
     * @return employee profile description
     */
    @Override
    public String toString() {
        return "[ " + id + " ] Employee <" + firstName + " " + lastName + "> :\n"
                + "Email: " + email + "\nPhone Number: " + phoneNumber + "\n"
                + "Last Punch: " +  getLastPunch() + "\n"
                + "Total Hours Worked: " + getTotHoursWorked();
    }

}
