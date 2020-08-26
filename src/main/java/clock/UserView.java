package clock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.ArrayList;


public class UserView {

    private ClockGui gui;
    private Clock clock;
    private GuiController controller;
    private TextField txtField;
    private VBox responseBox;

    /**
     * Initiates the user view with required class linkages
     * @param theGui the graphical interface
     * @param theClock the logical back-end
     * @param theController the bridge between all other guis
     */
    UserView(ClockGui theGui, Clock theClock, GuiController theController) {
        gui = theGui;
        clock = theClock;
        controller = theController;
        responseBox = null;
        genUserView();
    }

    /**
     * Generates user view interface
     */
    private void genUserView() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(80));
        Text pageTitle = new Text(15.0, 18.0, "EMPLOYEE TIMECLOCK");
        pageTitle.setStyle("-fx-font-weight:bold;-fx-font-size:50;");
        bp.setTop(pageTitle);
        bp.setCenter(userMenus());
        bp.setBottom(liveClock());
        Scene scn = new Scene(bp, 400, 200);
        gui.sendToScene(scn);
    }

    /**
     * Creates a live clock
     * @return text live clock
     */
    private Text liveClock() {
        Text time = new Text();
        time.setStyle("-fx-font-weight: bold; -fx-font-size: 40;");
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText("Current Time:\t " + currentTime.getHour() + ":" + currentTime.getMinute() + ":"
                    + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        return time;
    }

    /**
     * Generates manager menus
     * @return VBox containing the managers actionables
     */
    private HBox userMenus() {
        HBox hb = new HBox();
        hb.setStyle("-fx-padding: 10 0 0 0;");
        hb.setSpacing(5);
        hb.getChildren().addAll(signIn(),response());
        return hb;
    }

    /**
     * Creates the area of which the computer output to user would be displayed
     * @return VBox containing the response data to user
     */
    private VBox response(){
        responseBox = new VBox();
        responseBox.setStyle("-fx-padding: 10 0 0 0;");
        responseBox.setSpacing(5);
        responseBox.setAlignment(Pos.CENTER);
        return responseBox;
    }

    /**
     * User input area for which user will enter information for punch in/out processes
     * @return
     */
    private VBox signIn(){
        VBox vb = new VBox();
        vb.setStyle("-fx-padding: 10 300 30 0;");
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setSpacing(5);
        vb.getChildren().addAll(textFieldLabel(),textField(),createSignInBtn());
        return vb;
    }

    private Text textFieldLabel(){
        Text txt = new Text("Enter your credentials:");
        txt.setStyle("-fx-font-size: 20");
        return txt;
    }

    private TextField textField() {
        txtField = new TextField();
        txtField.setStyle("-fx-font-size:20");
        txtField.setOnMouseClicked(e -> responseBox.getChildren().clear());
        txtField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                    createPunch();
                }
            });
        return txtField;
    }


    private Button createSignInBtn() {
        Button signInBtn = new Button("Submit");
        signInBtn.setPrefSize(400,100);
        signInBtn.setStyle("-fx-font-size: 30");
        signInBtn.setOnAction(pressed -> System.out.println(createPunch()));
        return signInBtn;
    }


    private boolean createPunch(){
        String tmpTxt = txtField.getText();
        txtField.setText("");
        responseBox.getChildren().clear();
        try {
            EmployeeSearch employeeSearch = new EmployeeSearch(controller.getTheManager(), 2, tmpTxt);
            ArrayList<Employee> employeesFound =  employeeSearch.getSearchResults();
            if (employeesFound.size() > 1){
                //TODO:CREATE USER SELECT MULTI RESULT
            }else{
                Employee employee = (Employee)clock.getManager().getMap().get(employeesFound.get(0).getId());
                acceptedPunch(employee.setPunch());
            }
        }catch(EmployeeNotFoundException enfe){
            noUserFoundErrorPrompt();
        }
        return true;
    }

    private void noUserFoundErrorPrompt()  {
        Text errTxt = new Text("User Not Found!");
        errTxt.setStyle("-fx-font-size:30; -fx-fill: red; -fx-font-weight:bolder; -fx-alignment: center");
        responseBox.getChildren().addAll(errTxt);
    }

    private void acceptedPunch(String emp)  {
        Text txt = new Text("Punch Accepted!\n");
        txt.setStyle("-fx-font-size:30; -fx-fill: green; -fx-font-weight:bolder; -fx-alignment: center");
        Text user = new Text(emp);
        user.setStyle("-fx-font-size:20");
        responseBox.getChildren().addAll(txt,user);
    }





}
