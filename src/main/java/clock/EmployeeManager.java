package clock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeManager{

    private Map<Integer,Employee> employeeMap;

    /**
     * Constructor - initiates the employee manager
     */
    public EmployeeManager(){
        employeeMap = new HashMap<>();
    }

    /**
     * Creates a new employee profile
     * @param firstName employee first name
     * @param lastName employee last name
     * @param email employee email
     * @param phone employee phone
     */
    void createEmployee(String firstName, String lastName, String email, String phone){
        int id = assignId();
        employeeMap.put(id,new Employee(assignId(), firstName, lastName, email, phone));
    }

    /**
     * Adds employee to the list
     * @param employeeToAdd employee profile to add to the list
     */
    void addEmployee(Employee employeeToAdd){
        if(!idExists(employeeToAdd.getId())){
            employeeMap.put(employeeToAdd.getId(),employeeToAdd);
        }else{
            int id = assignId();
            employeeToAdd.setId(assignId());
            employeeMap.put(id,employeeToAdd);
        }
    }

    /**
     * Assigns an id to employee - ensures no duplicates are present
     * @return id integer
     */
    private int assignId(){
        int proposedId = employeeMap.size();
        boolean valid = false;
        while(!valid){
            valid = idExists(proposedId);
            proposedId++;
        }
        return proposedId;
    }

    /**
     * Compares if an id exists within the employee master list
     * @param id id to compare
     * @return boolean representing if the id exists in the list
     */
    private boolean idExists(int id){
        if(!employeeMap.isEmpty()){
            return employeeMap.containsKey(id); //match
        }
        return false; //not-match
    }

    /**
     * @returns string overview of all employees
     */
     String displayAllEmployees(){
        String outString = "";
        for(Employee emp: new ArrayList<Employee>(employeeMap.values())){
            outString += emp.toString() + "\n\n";
        };
        return outString;
    }

    /*
                System.out.println("1 = by ID");
            System.out.println("2 = by First Name");
            System.out.println("3 = by Last Name");
            System.out.println("4 = by Email");
            System.out.println("5 = by Phone Number");
     */


    /**
     * Searches employee list for user requested employee
     * @param type type of search to be performed
     * @param searchCri search keywords
     * @return employee information
     */
    String employeeSearch(int type, String searchCri) {
        switch (type) {
            case 1:
                return searchByID(searchCri);
            case 2:
                return searchByName(1,searchCri);
            case 3:
                return searchByName(2,searchCri);
            case 4:
                System.err.print("Todo: make search by email");
                break;
            case 5:
                System.err.println("Todo: make search by phone");
                break;
            default:
                break;
        }
        return "";
    }

    private String searchByID(String searchCri){
         int id = 0;
         try{
             id = Integer.parseInt(searchCri);
             try{
                 return employeeMap.get(id).toString();
             }catch(NullPointerException npe) {
                 return "Employee Not Found";
             }
         }catch(NumberFormatException nfe){
             return "Invalid ID";
         }
    }

    private String searchByName(int place, String name){
        String out = "\n";
        for(Employee indiv : new ArrayList<>(employeeMap.values())){
            if (place == 1){
                if(indiv.getFirstName().toUpperCase().equals(name.toUpperCase())){
                    out += indiv.toString() + "\n\n";
                }
            }else{
                if(indiv.getLastName().toUpperCase().equals(name.toUpperCase())){
                    out += indiv.toString() + "\n\n";
                }
            }
        }
        return out;
    }


}
