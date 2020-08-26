package clock;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EmployeeListDisplay {

    private ArrayList<Employee> dataSet;
    private TableView tableView;

    private EmployeeListDisplay(){
        tableView = null;
    }

    EmployeeListDisplay(ArrayList<Employee> src){
        this();
        dataSet = src;
        createResultTable();
        populateTable();

    }

    /**
     * Create table to display search results
     * @return TableView showing search results
     */
    private void createResultTable(){
        tableView= new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<String, Employee> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<String, Employee> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<String, Employee> emailCol = new TableColumn<>("eMail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<String, Employee> phoneNumberCol = new TableColumn<>("Phone Number");
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<String, Employee> totHrsWorkedCol = new TableColumn<>("Total Hrs. Worked");
        totHrsWorkedCol.setCellValueFactory(new PropertyValueFactory<>("totHoursWorked"));
        totHrsWorkedCol.setStyle("-fx-alignment:center");
        TableColumn<String, Employee> currInCol = new TableColumn<>("Curr. In");
        currInCol.setCellValueFactory(new PropertyValueFactory<>("currIn"));
        currInCol.setStyle("-fx-alignment:center");
        tableView.getColumns().addAll(firstNameCol,lastNameCol,emailCol,phoneNumberCol,totHrsWorkedCol, currInCol);
    }

    /**
     * Populates the table with the search result data
     */
    private void populateTable(){
        for(Employee emp: dataSet) {
            tableView.getItems().add(emp);
        }
    }

    /**
     * @return the table
     */
    public TableView getTableView() {
        return tableView;
    }
}
