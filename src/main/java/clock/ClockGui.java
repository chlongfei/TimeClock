package clock;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClockGui extends Application {

    private Clock theClock;
    private GuiController theController;
    private EmployeeManager theManager;
    private Stage stg;

    /**
     * Initiates the clock gui
     */
    public ClockGui(){
        theClock = new Clock();
        theManager = theClock.getManager();
        theController = new GuiController(this,theClock,theManager);
    }

    /**
     * Generates the first ui screen
     */
     void scnMain (){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
        Text programName = new Text(15.0,18.0,"Employee Time Clock (beta 1.0)");
        programName.setFont(new Font(15));
        bp.setTop(programName);
        bp.setCenter(mainMenu());
        Scene scn = new Scene(bp,400,200);
        sendToScene(scn);
    }

    /**
     * Sets user interface output to user
     * @param s scene to output to user
     */
    void sendToScene(Scene s){
        stg.setScene(s);
    }

    /**
     * Generates the main menu option item area
     * @return
     */
    private VBox mainMenu(){
        VBox vb = new VBox();
        vb.setStyle("-fx-padding: 10 0 0 0;");
        vb.setSpacing(5);
        vb.alignmentProperty().set(Pos.TOP_CENTER);
        vb.getChildren().addAll(btnManageView(),btnClose());
        return vb;
    }

    /**
     * Manager view buttons
     * @return Buttons to enter manager view
     */
    private Button btnManageView(){
        Button manageView = new Button("Employee Manager");
        manageView.setOnAction(press -> theController.toEmployeeManager());
        return manageView;
    }

    /**
     * Close program button
     * @return Buttons to enter close program
     */
    private Button btnClose(){
        Button closeBtn = new Button("Quit");
        closeBtn.setOnAction(press -> stg.close());
        return closeBtn;
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Time Clock - BETA");
        primaryStage.resizableProperty().set(false);
        stg = primaryStage;
        scnMain();
        primaryStage.show();
    }
}