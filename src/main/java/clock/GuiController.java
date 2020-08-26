package clock;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class GuiController {

    private ClockGui theGui;
    private Clock theClock;
    private EmployeeManager theManager;

    GuiController(ClockGui gui, Clock clock, EmployeeManager manager){
        theGui = gui;
        theClock = clock;
        theManager = manager;
    }

    void toEmployeeManager(){
        new ManagerView(theGui,theClock,this);
    }
    void toUser(){
        new UserView(theGui,theClock,this);
        theGui.getStg().setFullScreen(true);
    }
    void toClockGui(){
        theGui.getStg().setFullScreen(false);
        theGui.scnMain();
    }

    EmployeeManager getTheManager(){
        return theManager;
    }








}
