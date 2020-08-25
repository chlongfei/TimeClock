package clock;

public class EmployeeNotFoundException extends Exception {
    EmployeeNotFoundException(){
        super();
    }

    EmployeeNotFoundException(String message){
        super(message);
    }

}
