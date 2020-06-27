package clock;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Punch {

    private boolean clockedOut;
    private String inDate;
    private String inTime;
    private LocalDateTime clockIn;
    private String outDate;
    private String outTime;
    private LocalDateTime clockOut;

    /**
     * Constructor - initiates values to default values and creates initial punch
     */
    public Punch() {
        clockedOut = false;
        clockIn = null;
        clockOut = null;
        inDate = "";
        inTime = "";
        outDate = "";
        outTime = "";
        setClockIn();
    }

    /**
     * Sets start date
     * @param newDate string date
     */
    public void setinDate(String newDate){
        inDate = newDate;
    }

    /**
     * Sets start time
     * @param newTime string date
     */
    public void setInTime(String newTime){
        inTime = newTime;
    }

    /**
     * Sets clock in time
     * @param newClockIn LocalDateTime value
     */
    public void setClockIn(LocalDateTime newClockIn) {
        clockIn = newClockIn;
        inDate = newClockIn.toLocalDate().toString();
    }

    /**
     * Sets clock out time
     * @param newClockOut LocalDateTime value
     */
    public void setClockOut(LocalDateTime newClockOut){
        clockOut = newClockOut;
    }

    /**
     * Sets boolean indicating if current punch card is complete
     * @param newClockedOut boolean representing if card is complete
     */
    public void setClockedOut(boolean newClockedOut){
        clockedOut = newClockedOut;
    }

    /**
     * Initiates a clock in
     */
    public void setClockIn(){
        clockIn = LocalDateTime.now();
        inDate = clockIn.toLocalDate().toString();
        inTime = clockIn.toLocalTime().toString();
    }

    /**
     * Initiates a clock out
     */
    public void setClockOut(){
        clockOut = LocalDateTime.now();
        outDate = clockOut.toLocalDate().toString();
        outTime = clockOut.toLocalTime().toString();
        clockedOut = true;
    }

    /**
     * @return boolean representing if the current card is complete
     */
    public boolean isClockedOut(){
        return clockedOut;
    }

    /**
     * @return string describing latest punch state date
     */
    public String getDate(){
        if(!clockedOut) {
            return inDate;
        }else{
            return outDate;
        }
    }

    /**
     * @return string describing latest punch state time
     */
    public String getTime(){
        if(!clockedOut) {
            return inTime;
        }else{
            return outTime;
        }
    }

    /* CALCULATE DURATIONS */

    /**
     * @return total time worked in days
     */
    double getInDays(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.DAYS);
    }

    /**
     * @return total time worked in hours
     */
    double getInHours(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.HOURS);
    }

    /**
     * @return total time worked in minutes
     */
    double getInMinutes(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.MINUTES);
    }

    /**
     * @return total time worked in seconds
     */
    double getInSeconds(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.SECONDS);
    }

    /**
     * @return string break down of punch card hours
     */
    private String getBreakdown(){
        LocalDateTime durInWhole = LocalDateTime.from(clockIn);
        long days = durInWhole.until(clockOut,ChronoUnit.DAYS);
        durInWhole.plusDays(days);
        long hours = durInWhole.until(clockOut,ChronoUnit.HOURS);
        durInWhole.plusHours(hours);
        long mins = durInWhole.until(clockOut,ChronoUnit.MINUTES);
        durInWhole.plusMinutes(mins);
        long secs = durInWhole.until(clockOut,ChronoUnit.SECONDS);
        durInWhole.plusDays(secs);
        return createBreakdownDescription(days,hours,mins,secs);
    }

    /**
     * builds string descriptor of punch card breakdown
     * @param d days
     * @param h hours
     * @param m minutes
     * @param s seconds
     * @return string descriptor built
     */
    private String createBreakdownDescription(long d, long h, long m, long s){
        String outString= new String();
        outString += (d + " Day" + ifPlural(d) + " ");
        outString += (h + " Hour" + ifPlural(h) + " ");
        outString += (m + " Minute" + ifPlural(m) + " ");
        outString += (s + " Second" + ifPlural(s) + " ");
        return outString;
    }

    /**
     * Determines if a numeric value is more than 1 of which
     * will return a 's' character to the time unit in descriptor
     * @param num numeric value for analysis
     * @return 's' character
     */
    private String ifPlural(long num){
        if(num > 1){
            return "s";
        }
        return "";
    }


    /**
     * @return punch card overview
     */
    @Override
    public String toString(){
        return getBreakdown();
    }



}