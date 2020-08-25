package clock;


import java.util.ArrayList;
import java.util.Map;

public class EmployeeSearch{

    private Map<Integer,Employee> employeeMap;
    private EmployeeInformationDialog empInfo;
    private ArrayList<Employee> searchResults;



    private EmployeeSearch(){
        employeeMap = null;
        empInfo = null;
        searchResults = new ArrayList<Employee>();
    }



    EmployeeSearch(EmployeeManager eManager, EmployeeInformationDialog employeeSearchParameters) throws EmployeeNotFoundException{
        this();
        employeeMap = eManager.getMap();
        empInfo = employeeSearchParameters;
        createSearchSource();
        doSearch();
        System.out.println("Results:\n" + searchResults);
    }

    /**
     * Initializes the search source dataset with full employee list
     */
    private void createSearchSource(){
        searchResults = new ArrayList<Employee>(employeeMap.values());
    }

    /**
     * Calls the search actions as specified in parameter string
     */
    private void doSearch() throws EmployeeNotFoundException{
        for(int pl = 0; pl < 4; pl++) {
            System.err.println(hasParamAt(pl));
            if(hasParamAt(pl)){
                callSearch(pl);
            }
        }
    }

    /**
     * Determines if usre has provided info for a specific parameter
     * @param pl character place in formatted string
     * @return boolean indication of if information has been provided in parameter
     */
    private boolean hasParamAt(int pl){
        if(Integer.parseInt(String.valueOf(empInfo.getFieldParams().charAt(pl))) == 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Calls the search actions to parse employee list
     * @param search search parameter choice
     */
    private void callSearch(int search) throws EmployeeNotFoundException{
            switch (search) {
                case 0:
                    searchByFirstName();
                    break;
                case 1:
                    searchByLastName();
                    break;
                case 2:
                    searchByPhone();
                    break;
                case 3:
                    searchByEmail();
                    break;
                default:
                    throw new EmployeeNotFoundException("Employee Not Found");
            }
    }

    /**
     * Searches employee map for employee with the specified first name in parameters
     * @throws EmployeeNotFoundException indicating no employees were found with specified parameter
     */
    private void searchByFirstName() throws EmployeeNotFoundException{
        ArrayList<Employee> tmpEmployeeList = new ArrayList<Employee>();
        for(int e = 0; e < searchResults.size(); e++) {
            if (searchResults.get(e).getFirstName().toLowerCase().equals(empInfo.getFirstName().toLowerCase())) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + empInfo.getFirstName() + '"' + " Was Not Found.");

        }else {
            searchResults = tmpEmployeeList;
        }

    }

    /**
     * Searches employee map for employee with the specified last name in parameters
     * @throws EmployeeNotFoundException indicating no employees were found with specified parameter
     */
    private void searchByLastName() throws EmployeeNotFoundException{
        ArrayList<Employee> tmpEmployeeList = new ArrayList<Employee>();
        for(int e = 0; e < searchResults.size(); e++) {
            if (searchResults.get(e).getLastName().toLowerCase().equals(empInfo.getLastName().toLowerCase())) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + empInfo.getLastName() + '"' + " Was Not Found.");

        }else {
            searchResults = tmpEmployeeList;
        }

    }

    /**
     * Searches employee map for employee with the specified phone number in parameters
     * @throws EmployeeNotFoundException indicating no employees were found with specified parameter
     */
    private void searchByPhone() throws EmployeeNotFoundException{
        ArrayList<Employee> tmpEmployeeList = new ArrayList<Employee>();
        for(int e = 0; e < searchResults.size(); e++) {
            if (searchResults.get(e).getPhoneNumber().equals(empInfo.getPhone())) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + empInfo.getPhone() + '"' + " Was Not Found.");

        }else {
            searchResults = tmpEmployeeList;
        }

    }

    /**
     * Searches employee map for employee with the specified email in parameters
     * @throws EmployeeNotFoundException indicating no employees were found with specified parameter
     */
    private void searchByEmail() throws EmployeeNotFoundException{
        ArrayList<Employee> tmpEmployeeList = new ArrayList<Employee>();
        for(int e = 0; e < searchResults.size(); e++) {
            if (searchResults.get(e).getEmail().equals(empInfo.getEmail())) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + empInfo.getEmail() + '"' + " Was Not Found.");

        }else {
            searchResults = tmpEmployeeList;
        }

    }

    /**
     * @return the results of the search
     */
    public ArrayList<Employee> getSearchResults() {
        return searchResults;
    }
}
