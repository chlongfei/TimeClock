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

    public void setinDate(String newDate){
        inDate = newDate;
    }

    public void setInTime(String newTime){
        inTime = newTime;
    }

    public void setClockIn(LocalDateTime newClockIn) {
        clockIn = newClockIn;
        inDate = newClockIn.toLocalDate().toString();
    }

    public void setClockOut(LocalDateTime newClockOut){
        clockOut = newClockOut;
    }

    public void setClockedOut(boolean newClockedOut){
        clockedOut = newClockedOut;
    }

    public void setClockIn(){
        clockIn = LocalDateTime.now();
        inDate = clockIn.toLocalDate().toString();
        inTime = clockIn.toLocalTime().toString();
    }

    public void setClockOut(){
        clockOut = LocalDateTime.now();
        outDate = clockOut.toLocalDate().toString();
        outTime = clockOut.toLocalTime().toString();
        clockedOut = true;
    }

    public boolean isClockedOut(){
        return clockedOut;
    }

    public String getDate(){
        if(!clockedOut) {
            return inDate;
        }else{
            return outDate;
        }
    }

    public String getTime(){
        if(!clockedOut) {
            return inTime;
        }else{
            return outTime;
        }
    }

    /* CALCULATE DURATIONS */

    double getInDays(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.DAYS);
    }

    double getInHours(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.HOURS);
    }

    double getInMinutes(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.MINUTES);
    }

    double getInSeconds(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.SECONDS);
    }

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

    private String createBreakdownDescription(long d, long h, long m, long s){
        String outString= new String();
        outString += (d + " Day" + ifPlural(d) + " ");
        outString += (h + " Hour" + ifPlural(h) + " ");
        outString += (m + " Minute" + ifPlural(m) + " ");
        outString += (s + " Second" + ifPlural(s) + " ");
        return outString;
    }


    private String ifPlural(long num){
        if(num > 1){
            return "s";
        }
        return "";
    }


    @Override
    public String toString(){
        return getBreakdown();
    }



}