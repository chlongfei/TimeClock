package clock;

import java.util.Scanner;

public class Clock {

public static void main(String[] args){
    Scanner scnr = new Scanner(System.in);
    Punch pcard = new Punch();
    if(scnr.nextInt() == 1){
        pcard.setClockOut();
        System.out.println(pcard.toString());
    }
}




}
