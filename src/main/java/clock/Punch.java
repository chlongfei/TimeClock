package clock;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Punch {

    private LocalDateTime clockIn;
    private LocalDateTime clockOut;

    public Punch() {
        clockIn = LocalDateTime.now();
        clockOut = null;
    }

    public void setClockIn(LocalDateTime clockIn) {
        this.clockIn = clockIn;
    }

    public void setClockOut(LocalDateTime clockOut){
        this.clockOut = clockOut;
    }


    public void setClockOut(){
        this.clockOut = LocalDateTime.now();
    }

    /* CALCULATE DURATIONS */

    private double getInDays(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.DAYS);
    }

    private double getInHours(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.HOURS);
    }

    private double getInMinutes(){
        LocalDateTime durInSec = LocalDateTime.from(clockIn);
        return durInSec.until(clockOut, ChronoUnit.MINUTES);
    }

    private double getInSeconds(){
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