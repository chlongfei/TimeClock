package clock;

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

    void toClockGui(){
        theGui.scnMain();
    }

    EmployeeManager getTheManager(){
        return theManager;
    }








}
