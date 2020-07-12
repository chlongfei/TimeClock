package clock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeManager{

    private Map<Integer,Employee> employeeMap;
    private int employeeCount;

    /**
     * Constructor - initiates the employee manager
     */
    public EmployeeManager(){
        employeeMap = new HashMap<>();
        employeeCount = 1;
    }

    public void setEmployeecount(int count){
        employeeCount = count;
    }


    /**
     * Creates a new employee profile
     * @param firstName employee first name
     * @param lastName employee last name
     * @param email employee email
     * @param phone employee phone
     */
    void createEmployee(String firstName, String lastName, String email, String phone){
        try{
            employeeMap.put(employeeCount,new Employee(employeeCount, firstName, lastName, email, phone));
            employeeCount ++;
        }catch(NullPointerException npe){
        }

    }

    /**
     * Adds employee to the list
     * @param employeeToAdd employee profile to add to the list
     */
    void addEmployee(Employee employeeToAdd){
        if(!idExists(employeeToAdd.getId())){
            employeeMap.put(employeeToAdd.getId(),employeeToAdd);
        }else{
            employeeCount++;
            employeeToAdd.setId(employeeCount);
            employeeMap.put(employeeCount,employeeToAdd);
        }
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
     String displayAllEmployees() {
         String outString = "";
         for (Employee emp : new ArrayList<Employee>(employeeMap.values())) {
             outString += emp.toString() + "\n\n";
         }
         if(outString.length() < 1){
             return "(no employees)";
         }
         return outString;
     }

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
                return searchByEmail(searchCri);
            case 5:
                return searchByPhone(searchCri);
            default:
                break;
        }
        return "";
    }

    /**
     * Searches employee list by id
     * @param searchCri employee id
     * @return employee profile
     */
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

    /**
     * Searches employee list by either first or last name determined by the place value
     * @param place indicates if first or last name should be used
     * @param name name to be searched for
     * @return employee provfiles
     */
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

    /**
     * Searches employee list by employee email
     * @param email employee email
     * @return employee profile
     */
    private String searchByEmail(String email){
        String out = "\n";
        for(Employee indiv : new ArrayList<>(employeeMap.values())){
            if(indiv.getEmail().toUpperCase().equals(email.toUpperCase())){
                out += indiv.toString() + "\n\n";
            }
        }
        if(out.length() < 1){
            return "Employee Not Found";
        }
        return out;
    }

    /**
     * Searches employee list by employee phone number
     * @param phone employee phone number
     * @return employee profile
     */
    private String searchByPhone(String phone){
        String out = "\n";
        for(Employee indiv : new ArrayList<>(employeeMap.values())) {
            if (indiv.getPhoneNumber().equals(phone)) {
                out += indiv.toString() + "\n\n";
            }
        }
        return out;
    }

    public boolean createPunch(String phone){
        for(Employee indiv : new ArrayList<>(employeeMap.values())) {
            if (indiv.getPhoneNumber().equals(phone)){
                indiv.setPunch();
                return true;
            }
        }
        return false;
    }

}
