package clock;

import javafx.application.Application;
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
    private Stage stg;

    /**
     * Initiates the clock gui
     */
    public ClockGui(){
        theClock = new Clock();
        theController = new GuiController();
    }

    /**
     * Generates the first ui screen
     */
     void scnMain (){
        BorderPane bp = new BorderPane();
        Text programName = new Text(5.0,18.0,"Employee Time Clock (beta 1.0)");
        programName.setFont(new Font(18));
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

    private VBox mainMenu(){
        VBox vb = new VBox();
        vb.alignmentProperty().set(Pos.TOP_CENTER);
        vb.getChildren().addAll(btnManageView());
        return vb;
    }

    /**
     * Manager view buttons
     * @return Buttons to enter manager view
     */
    private Button btnManageView(){
        Button manageView = new Button("Employee Manager");
        manageView.setOnAction(press -> new ManagerView(this,theClock,theController));
        return manageView;
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Time Clock - BETA");
        stg = primaryStage;
        scnMain();
        primaryStage.show();
    }
}