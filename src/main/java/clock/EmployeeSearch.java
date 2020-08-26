package clock;


import java.util.ArrayList;
import java.util.Map;

public class EmployeeSearch{

    private Map<Integer,Employee> employeeMap;
    private EmployeeInformationDialog empInfo;
    private ArrayList<Employee> searchResults;

    private String searchForFirstName;
    private String searchForLastName;
    private String searchForEmail;
    private String searchForPhone;



    private EmployeeSearch(){
        employeeMap = null;
        empInfo = null;
        searchResults = new ArrayList<Employee>();
        searchForFirstName = "";
        searchForLastName = "";
        searchForEmail = "";
        searchForPhone = "";
    }


    EmployeeSearch(EmployeeManager eManager, int searchBy, String keyword)throws EmployeeNotFoundException{
        this();
        employeeMap = eManager.getMap();
        assignKeyword(searchBy,keyword);
        createSearchSource();
        callSearch(searchBy);
    }

    EmployeeSearch(EmployeeManager eManager, EmployeeInformationDialog employeeSearchParameters) throws EmployeeNotFoundException{
        this();
        employeeMap = eManager.getMap();
        empInfo = employeeSearchParameters;
        searchForFirstName = empInfo.getFirstName();
        searchForLastName = empInfo.getLastName();
        searchForEmail = empInfo.getEmail();
        searchForPhone = empInfo.getPhone();
        createSearchSource();
        doSearch();

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

    private void assignKeyword(int search, String keyword){
        switch (search) {
            case 0:
                searchForFirstName = keyword;
                break;
            case 1:
                searchForLastName = keyword;
                break;
            case 2:
                searchForPhone = keyword;
                break;
            case 3:
                searchForEmail = keyword;
                break;
            default:
                break;
        }
    }

    /**
     * Searches employee map for employee with the specified first name in parameters
     * @throws EmployeeNotFoundException indicating no employees were found with specified parameter
     */
    private void searchByFirstName() throws EmployeeNotFoundException{
        ArrayList<Employee> tmpEmployeeList = new ArrayList<Employee>();
        for(int e = 0; e < searchResults.size(); e++) {
            if (searchResults.get(e).getFirstName().toLowerCase().equals(searchForFirstName.toLowerCase())) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + searchForFirstName + '"' + " Was Not Found.");

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
            if (searchResults.get(e).getLastName().toLowerCase().equals(searchForLastName.toLowerCase())) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + searchForLastName + '"' + " Was Not Found.");

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
            if (searchResults.get(e).getPhoneNumber().equals(searchForPhone)) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + searchForPhone + '"' + " Was Not Found.");

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
            if (searchResults.get(e).getEmail().equals(searchForEmail)) {
                tmpEmployeeList.add(employeeMap.get(e+1));
            }
        }
        if (tmpEmployeeList.isEmpty()) {
            searchResults = null;
            throw new EmployeeNotFoundException("Employee " + '"' + searchForEmail + '"' + " Was Not Found.");

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
