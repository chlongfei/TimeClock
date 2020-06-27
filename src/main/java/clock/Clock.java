package clock;

import java.util.Scanner;

public class Clock {

    private boolean quit;
    private EmployeeManager manager;

    /**
     * Constructor - creates a new clock instance
     */
    public Clock(){
        quit = false;
        manager = new EmployeeManager();

    }

    /**
     * Sets quit value
     * @param q boolean indicating if program should terminate
     */
    public void setQuit(boolean q){
        quit = q;
    }

    /**
     * @return quit value determining if program should terminate
     */
    public boolean getQuit(){
        return quit;
    }

    /**
     * Adds an employee to list of employees
     * @param employee employee to add
     */
    public void setEmployees(Employee employee){
        manager.addEmployee(employee);
    }


    public void searchEmployee(Scanner scnr){
            System.out.println("*********************************\n"
                    + "EMPLOYEE SEARCH\n"
                    +"*********************************");
            System.out.println("1 = by ID");
            System.out.println("2 = by First Name");
            System.out.println("3 = by Last Name");
            System.out.println("4 = by Email");
            System.out.println("5 = by Phone Number");
            System.out.println("0 = CANCEL");
            int response = validateSelection(scnr);

            if (response == 0){
                return;
            }else{
                System.out.println(seekInputSearchEmp(response,scnr));
            }
    }


    private String seekInputSearchEmp(int choice, Scanner scnr){
        switch (choice) {
            case 1:
                System.out.print("ID: ");
                break;
            case 2:
                System.out.print("First Name: ");
                break;
            case 3:
                System.out.print("Last Name: ");
                break;
            case 4:
                System.out.print("Email: ");
                break;
            case 5:
                System.out.print("Phone Number:");
                break;
            default:
                System.out.println("Invalid Selection.");
                return null;
        }
        return manager.employeeSearch(choice, scnr.nextLine());
    }

    /**
     * Accepts and validates user input
     * @param scnr scanner for user input
     * @return user selection
     */
    private int validateSelection(Scanner scnr){
        System.out.print("> ");
        String input = scnr.nextLine();
        try{
            int sel = Integer.parseInt(input);
            return sel;
        }catch(NumberFormatException nfe){
            System.out.println("Invalid Selection");
        }
        return 0;
    }


    /**
     * Displays all employees in text
     */
    public void showAllEmployees(){
        System.out.println(manager.displayAllEmployees());
    }

    /**
     * Creates a new employee profile
     * @param scnr scanner for user input
     */
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
        manager.createEmployee(firstName,lastName,email,phone);
        System.out.println("Success!");
    }

    /**
     * Text interface for employee management menus
     * @param scnr scanner for user input
     */
    private void employeeManager(Scanner scnr){
        boolean exit = false;
        while(!exit){
            System.out.println("\n\n\n");
            System.out.println("--------------------------------------------------");
            System.out.println("            EMPLOYEE MANAGEMENT");
            System.out.println("--------------------------------------------------");
            System.out.println("1 = Create New Employee");
            System.out.println("2 = Search Employee");
            System.out.println("3 = View All Employees");
            System.out.println("0 = CANCEL");
            exit = seekInputEmpMenu(scnr);
        }
    }

    /**
     * Handles user input and responses accordingly for employee manager
     * @param scnr scanner for user input
     * @return boolean value indicating if employee management menu should exit
     */
    private boolean seekInputEmpMenu(Scanner scnr){
            System.out.print("> ");
            switch(scnr.nextLine().toUpperCase()){
                case"1":
                    createEmployee(scnr);
                    break;
                case"2":
                    searchEmployee(scnr);
                    break;
                case"3":
                    showAllEmployees();
                    break;
                case"0":
                    return true;
                default:
                    System.out.println("Wrong Input. Please Try Again...");
                    break;
            }
        return false;
    }


    private void punchClock(Scanner scnr){
        System.out.println("\n\n\n");
        System.out.println("--------------------------------------------------");
        System.out.println("            EMPLOYEE SIGN-IN");
        System.out.println("--------------------------------------------------");
        System.out.print("PHONE NUMBER: ");
        if(manager.createPunch(scnr.nextLine())){
            System.out.println("PUNCH ACCEPTED");
        }else{
            System.out.println("PUNCH NOT ACCEPTED");
        }
    }
    /**
     * Text interface welcome screen
     */
    public void welcomeScrn(){
        System.out.println("\n\n\n");
        System.out.println("--------------------------------------------------");
        System.out.println("EMPLOYEE TIME CLOCK");
        System.out.println("BETA 1.0");
        System.out.println("--------------------------------------------------");
        System.out.println("1 = Employee Manager");
        System.out.println("2 = Punch");
        System.out.println("0 = Quit");
    }

    /**
     * Handles uer input and responses accordingly for main user menu
     * @param scnr scanner for user input
     */
    public void seekInput(Scanner scnr){
        System.out.print("> ");
        switch(scnr.nextLine().toUpperCase()){
            case"1":
                employeeManager(scnr);
                break;
            case"2":
                punchClock(scnr);
                break;
            case"0":
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
        while(!theClock.getQuit()){
                theClock.welcomeScrn();
                theClock.seekInput(scnr);
        }





    }



}
