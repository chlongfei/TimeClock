package clock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalTime;


public class ManagerView {

    private ClockGui gui;
    private Clock clock;
    private GuiController controller;

    ManagerView(ClockGui theGui, Clock theClock, GuiController theController) {
        gui = theGui;
        clock = theClock;
        controller = theController;
        genManagerView();
    }

    /**
     * Generates manager view interface
     */
    private void genManagerView() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
        Text pageTitle = new Text(15.0, 18.0, "Manager View");
        pageTitle.setFont(new Font(15));
        bp.setTop(pageTitle);
        bp.setLeft(managerMenus());
        bp.setBottom(liveClock());
        Scene scn = new Scene(bp, 400, 200);
        gui.sendToScene(scn);
    }

    /**
     * Generates manager menus
     *
     * @return VBox containing the managers actionables
     */
    private VBox managerMenus() {
        EmployeeInformationDialog infoDialog;
        Button backBtn = new Button("Back");
        backBtn.setOnAction(push -> controller.toClockGui());
        VBox vb = new VBox();
        vb.setStyle("-fx-padding: 10 0 0 0;");
        vb.setSpacing(5);
        vb.getChildren().addAll(createNewEmployeeButton(), createSearchEmployeeBtn(), createListAllEmployeesBtn(),
                backBtn);
        return vb;
    }

    /**
     * Creates a live clock
     *
     * @return text live clock
     */
    private Text liveClock() {
        Text time = new Text();
        time.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText("Current Time: " + currentTime.getHour() + ":" + currentTime.getMinute() + ":"
                    + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        return time;
    }



    /* CREATE NEW EMPLOYEE */

    /**
     * Creates "create new employee" button
     *
     * @return action button
     */
    private Button createNewEmployeeButton() {
        Button newEmployeeBtn = new Button("Create New Employee");
        newEmployeeBtn.setOnAction(pressed -> popupCreateEmployee());
        return newEmployeeBtn;
    }

    /**
     * Popup dialog allowing manager to input new employee information
     */
    private void popupCreateEmployee() {
        EmployeeInformationDialog infoDialog = new EmployeeInformationDialog("Create Employee");
        infoDialog.showAndWait().get();
        if (verifyAllFields(infoDialog)) {
            clock.makeEmployee(infoDialog.getFirstName(), infoDialog.getLastName(), infoDialog.getEmail(),
                    infoDialog.getPhone());
            clock.showAllEmployees();
        }
    }

    /**
     * Validates all fields have been filled
     *
     * @param infoDialog employee info dialog
     * @return boolean indicating if all filled
     */
    private boolean verifyAllFields(EmployeeInformationDialog infoDialog) {
        try {
            if (infoDialog.getFirstName().isEmpty() || infoDialog.getLastName().isEmpty() ||
                    infoDialog.getEmail().isEmpty() || infoDialog.getPhone().isEmpty()) {
                showAllRequiredError();
                return false;
            }
            return true;
        } catch (NullPointerException npe) {
            return false;
        }
    }

    /**
     * Displays error prompt if new employee profile has empty fields
     */
    private void showAllRequiredError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Fields");
        alert.setContentText("Information is missing from the new employee's profile.\nPlease ensure all "
                + "fields are filled.\n\nNo new employee created.");
        alert.show();
    }
    /* --- */


    /* SEARCH EMPLOYEE LIST */

    /**
     * Creates "search employee" action button
     * @return search employee button
     */
    private Button createSearchEmployeeBtn() {
        Button searchEmployeeBtn = new Button("Search Employee");
        searchEmployeeBtn.setOnAction(push -> popupSearchEmployee());
        return searchEmployeeBtn;
    }

    /**
     * Creates a input box for user to enter search parameters
     */
    private void popupSearchEmployee() {
        EmployeeInformationDialog infoDialog = new EmployeeInformationDialog("Search Employee");
        ButtonType btnType = infoDialog.showAndWait().get();
        try {
            EmployeeSearchResultDialog empDialog = new EmployeeSearchResultDialog(controller, infoDialog);
            empDialog.showAndWait().get();
        } catch (EmployeeNotFoundException enfe) {
        } catch (NullPointerException npe) {
        }
    }
    /* -- */

    /* Display All Employees */
    /**
     * Creates "search employee" action button
     * @return search employee button
     */
    private Button createListAllEmployeesBtn() {
        Button listAllEmployees = new Button("List ALL Employee");
        listAllEmployees.setOnAction(push -> popupShowAllEmployees());
        return listAllEmployees;
    }

    private void popupShowAllEmployees(){
        EmployeeShowAllDialog showAll = new EmployeeShowAllDialog(controller);
        showAll.showAndWait();
    }
}
