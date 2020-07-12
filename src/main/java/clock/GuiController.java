package clock;

public class GuiController {

    private ClockGui theGui;
    private Clock theClock;

    GuiController(ClockGui gui, Clock clock){
        theGui = gui;
        theClock = clock;
    }

    void toEmployeeManager(){
        new ManagerView(theGui,theClock,this);
    }

    void toClockGui(){
        theGui.scnMain();
    }






}
