package clock;

import java.util.ArrayList;
import java.util.Scanner;

public class Clock {

    private boolean quit;
    private ArrayList<Employee> employees;

    public Clock(){
        quit = false;
        employees = new ArrayList<Employee>();

    }

    public void setEmployees(Employee employee){
        employees.add(employee);
    }

    public void createEmployee(Scanner scnr){
        System.out.println("*********************************\n"
                            + "NEW EMPLOYEE CREATION UTIL.\n"
                            +"*********************************");
        System.out.print("First Name: ");
        String firstName = scnr.nextLine();
        System.out.print("Last Name: ");
        String lastName = scnr.nextLine();
        System.out.print("Email: ");
        String email = scnr.nextLine();
        System.out.print("Phone: ");
        String phone = scnr.nextLine();
        employees.add(new Employee(employees.size()+1, firstName, lastName, email, phone));
    }

    public ArrayList<Employee> getEmployee(String name){
        ArrayList<Employee> empList = new ArrayList<Employee>();
        for(Employee emp:employees){
            if(emp.getFirstName().equals(name)){
                empList.add(emp);
            }
        }
        if(empList.isEmpty()){
            return null;
        }
        return empList;
    }

    public Employee getEmployee(int id){
        for(Employee emp:employees){
            if(emp.getId() == id){
                return emp;
            }
        }
        return null;
    }

    public void findEmployee(Scanner scnr){
        ArrayList<Employee> empList = null;
        System.out.print("Search (ID, First Name): ");
        String searchCri = scnr.nextLine();
        try{
            empList = new ArrayList<Employee>();
            empList.add(getEmployee(Integer.parseInt(searchCri)));
        }catch(NumberFormatException nfe){
            empList = getEmployee(searchCri.toUpperCase());
        }
        if(empList  == null){
            System.out.println("Employee Not Found");
        }else{
            for(Employee emp: empList){
                System.out.println("\n" + emp.toString());
            }
        }
    }


    public void setQuit(boolean q){
        quit = q;
    }

    public boolean getQuit(){
        return quit;
    }

    public void welcomeScrn(){
        System.out.println("\n\n\n");
        System.out.println("--------------------------------------------------");
        System.out.println("EMPLOYEE TIME CLOCK");
        System.out.println("BETA 1.0");
        System.out.println("--------------------------------------------------");
        System.out.println("1 = Create New Employee");
        System.out.println("2 = View Employee Information");
        System.out.println("3 = Punch");
        System.out.println("Q = Quit");
    }

    public void seekInput(Scanner scnr){
        System.out.print("> ");
        switch(scnr.nextLine().toUpperCase()){
            case"1":
                System.out.println("Creating New Employee");
                createEmployee(scnr);
                break;
            case"2":
                System.out.println("Viewing Employee Information");
                findEmployee(scnr);
                break;
            case"3":
                System.out.println("Punch");
                break;
            case"Q":
                System.out.println("Quitting...");
                quit = true;
                break;
            default:
                System.out.println("Wrong Input. Please Try Again...");
                break;
        }
    }

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);

        Clock theClock = new Clock();


        Employee emp = new Employee("John","Smith","jsmith@email.com","9992220202");
        emp.setId(1);
        System.err.println(emp.setPunch());
        if(scnr.nextInt() == 1){
            System.err.println(emp.setPunch());
            System.out.println(emp.toString());
        }
        theClock.setEmployees(emp);



        while(!theClock.getQuit()){
                theClock.welcomeScrn();
                theClock.seekInput(scnr);
        }





    }



}
