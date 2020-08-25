package clock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmployeeInformationDialog extends Dialog<ButtonType> {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String fieldParams;

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField phoneField;
    private TextField emailField;

    private static final String APPLYTXT = "Apply";
    private static final String CANCELTXT = "Cancel";

    public EmployeeInformationDialog(String name) {
        super();
        createForm();
        setTitle(name);

    }

    /**
     * Creates employee profile creation form
     */
    private void createForm(){
        BorderPane bp = new BorderPane();
        bp.setLeft(createInputArea());
        getDialogPane().setContent(bp);
    }

    /**
     * Creates user input text fields
     */
    private void createInputFields(){
        firstNameField = new TextField();
        lastNameField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();
    }

    /**
     * Creates area for user input
     * @return node containing user input area
     */
    private Node createInputArea(){
        createInputFields();
        VBox vb = new VBox();
        vb.setPadding(new Insets(10));
        vb.setSpacing(10.0);
        vb.setAlignment(Pos.CENTER_LEFT);
        HBox fNameHBox = new HBox(new Label("First Name: "),firstNameField);
        fNameHBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox lNameHBox = new HBox(new Label("Last Name: "), lastNameField);
        lNameHBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox phoneHBox = new HBox(new Label("Phone Number: "), phoneField);
        phoneHBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox emailHBox = new HBox(new Label("Email Address: "), emailField);
        emailHBox.setAlignment(Pos.BASELINE_RIGHT);
        vb.getChildren().addAll(fNameHBox,lNameHBox,phoneHBox,emailHBox);
        setButtons();
        return vb;
    }

    /**
     * Sets buttons for accept or cancel of user input
     */
    private void setButtons(){
        ButtonType submit = new ButtonType(APPLYTXT,ButtonBar.ButtonData.APPLY);
        ButtonType cancel = new ButtonType(CANCELTXT, ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(submit,cancel);
        setButtonActions(submit, cancel);
    }

    /**
     * Sets actions for the user buttons
     * @param submit button type
     * @param cancel button type
     */
    private void setButtonActions(ButtonType submit, ButtonType cancel){
        Button submitBtn = (Button) getDialogPane().lookupButton(submit);
        Button cancelBtn = (Button) getDialogPane().lookupButton(cancel);

        submitBtn.setOnAction(push->collectValues());
        cancelBtn.setOnAction(push->close());

    }

    /**
     * Collect user inputted values
     */
    private void collectValues(){
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        phone = phoneField.getText();
        email = emailField.getText();
        fieldParams = evalFormat();
        close();
    }

    /**
     * @return 1 if an first name entry exists, 0 otherwise
     */
    private String hasFirstName(){
        if(!firstName.isEmpty()){
            return "1";
        }
        return "0";
    }
    /**
     * @return 1 if an last name entry exists, 0 otherwise
     */
    private String hasLastName(){
        if(!lastName.isEmpty()){
            return "1";
        }
        return "0";
    }
    /**
     * @return 1 if an phone number entry exists, 0 otherwise
     */
    private String hasPhone(){
        if(!phone.isEmpty()){
            return "1";
        }
        return "0";
    }
    /**
     * @return 1 if an email entry exists, 0 otherwise
     */
    private String hasEmail(){
        if(!email.isEmpty()){
            return "1";
        }
        return "0";
    }

    /**
     * Evaluates the fields that user has filled, creates a representation of filled fields
     * using 1 and 0
     * @return string combination of 1 and 0 to indicate fields filled by user
     */
    private String evalFormat(){
        return hasFirstName()+hasLastName()+hasPhone()+hasEmail();
    }



    /**
     * @return employee first name
     */
    String getFirstName(){
        return firstName;
    }
    /**
     * @return employee last name
     */
    String getLastName() {
        return lastName;
    }
    /**
     * @return employee phone
     */
    String getPhone() {
        return phone;
    }
    /**
     * @return employee email
     */
    String getEmail() {
        return email;
    }
    /**
     * @return field format representation
     */
    String getFieldParams(){
        return fieldParams;
    }


    /**
     * Dumps info in all fields
     * @return string representing information entered in the fields
     */
    @Override
    public String toString() {
        return "EmployeeInformationDialog{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", field format='" + fieldParams + '\''+
                '}';
    }
}
