package clock;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EmployeeInformationDialog extends Dialog<Integer> {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField phoneField;
    private TextField emailField;

    private static final String APPLYTXT = "Apply";
    private static final String CANCELTXT = "Cancel";

    public EmployeeInformationDialog() {
        super();
        createForm();

    }

    /**
     * Creates employee profile creation form
     */
    private void createForm(){
        BorderPane bp = new BorderPane();
        Text prompt = new Text("NEW Employee Information");
        prompt.setFont(new Font(12));
        bp.setTop(prompt);
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
        HBox fNameHBox = new HBox(new Label("First Name:"),firstNameField);
        HBox lNameHBox = new HBox(new Label("Last Name:"), lastNameField);
        HBox phoneHBox = new HBox(new Label("Phone Number:"), phoneField);
        HBox emailHBox = new HBox(new Label("Email Address:"), emailField);
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
     * @param submit
     * @param cancel
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
        close();
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
}
