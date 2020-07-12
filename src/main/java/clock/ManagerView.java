package clock;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ManagerView {

    private ClockGui gui;
    private Clock clock;
    private GuiController controller;

    ManagerView(ClockGui theGui, Clock theClock, GuiController theController){
        gui = theGui;
        clock = theClock;
        controller = theController;
        genManagerView();
    }

    /**
     * Generates manager view interface
     */
    private void genManagerView(){
        BorderPane bp = new BorderPane();
        Text pageTitle = new Text(5.0,18.0,"Manager View");
        bp.setTop(pageTitle);
        bp.setLeft(managerMenus());
        Scene scn = new Scene(bp,400,200);
        gui.sendToScene(scn);
    }

    /**
     * Generates manager menus
     * @return VBox containing the managers actionables
     */
    private VBox managerMenus(){
        EmployeeInformationDialog infoDialog;
        Button newEmployeeBtn = new Button("Create New Employee");
        newEmployeeBtn.setOnAction(pressed->popupCreateEmployee());
        Button backBtn = new Button("Back");
        backBtn.setOnAction(push->gui.scnMain());
        VBox vb = new VBox();
        vb.getChildren().addAll(newEmployeeBtn);
        return vb;
    }

    /**
     * Popup dialog allowing manager to input new employee information
     */
    private void popupCreateEmployee(){
        EmployeeInformationDialog infoDialog = new EmployeeInformationDialog();
        infoDialog.showAndWait();
        clock.makeEmployee(infoDialog.getFirstName(),infoDialog.getLastName(),infoDialog.getEmail(),
                            infoDialog.getPhone());
    }



}
