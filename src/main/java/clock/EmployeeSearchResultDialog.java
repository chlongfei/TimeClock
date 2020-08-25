package clock;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EmployeeSearchResultDialog extends Dialog<ButtonType> {

    private static final String CLOSETXT = "Close";
    private GuiController theController;
    private EmployeeSearch search;

    EmployeeSearchResultDialog(GuiController ctrl,EmployeeInformationDialog info) throws EmployeeNotFoundException{
        super();
        search = null;
        theController = ctrl;
        doSearch(info);
        getDialogPane().setPrefWidth(800);
    }

    /**
     * Performs the search
     * @param info instance of EmployeeInformationDialog collecting search parameters
     */
    private void doSearch(EmployeeInformationDialog info)throws EmployeeNotFoundException{
        try{
            search = new EmployeeSearch(theController.getTheManager(), info);
            setButtons();
            setTitle("Search Results");
            createDisplay();
        }catch(EmployeeNotFoundException enfe){
            showENFError();
            throw new EmployeeNotFoundException();
        }
    }

    /**
     * Displays error prompt if employee is not found
     */
    private void showENFError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Employee Not Found");
        alert.setContentText("The employee you're trying to locate is not in the database." +
                "\nPlease review your parameters and try again.");
        alert.show();
    }

    /**
     * Sets buttons for accept or cancel of user input
     */
    private void setButtons(){
        ButtonType close = new ButtonType(CLOSETXT,ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(close);
        setButtonActions(close);
    }

    /**
     * Sets actions for the user buttons
     * @param close button type
     */
    private void setButtonActions(ButtonType close){
        Button closeBtn = (Button) getDialogPane().lookupButton(close);
        closeBtn.setOnAction(push->close());

    }

    /**
     * Creates employee profile creation form
     */
    private void createDisplay(){
        VBox vb = new VBox();
        vb.setStyle("-fx-padding: 10 10 10 10;");
        vb.setSpacing(5);
        Text pageTitle = new Text(15.0,18.0,"Results:");
        pageTitle.setFont(new Font(15));
        vb.getChildren().addAll(pageTitle,createResultTable());
        getDialogPane().setContent(vb);
    }

    /**
     * Create table to display search results
     * @return TableView showing search results
     */
    private TableView createResultTable(){
        TableView tbView = new TableView();
        tbView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
        tbView.getColumns().addAll(firstNameCol,lastNameCol,emailCol,phoneNumberCol,totHrsWorkedCol);
        populateTable(tbView);
        return tbView;
    }

    /**
     * Populates the table with the search result data
     * @param tbv TableView table to populate
     */
    private void populateTable(TableView tbv){
        ArrayList<Employee> resultList = search.getSearchResults();
        for(Employee emp: resultList) {
            tbv.getItems().add(emp);
        }

    }



}
