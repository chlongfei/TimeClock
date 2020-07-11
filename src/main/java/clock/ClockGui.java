package clock;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClockGui extends Application {

    private void scnMain (Stage stg){
        Group grp = new Group();
        Text programName = new Text(5.0,18.0,"Employee Time Clock (beta 1.0)");
        programName.setFont(new Font(18));
        grp.getChildren().add(programName);
        Scene scn = new Scene(grp,400,200);
        stg.setScene(scn);
    }




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Time Clock - BETA");
        scnMain(primaryStage);
        primaryStage.show();
    }
}
