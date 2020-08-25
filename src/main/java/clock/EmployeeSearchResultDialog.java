package clock;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class EmployeeSearchResultDialog extends Dialog<ButtonType> {

    private static final String CLOSETXT = "Close";
    private GuiController theController;

    public EmployeeSearchResultDialog(GuiController ctrl,EmployeeInformationDialog info){
        super();
        theController = ctrl;
        setButtons();
        createDisplay();
        setTitle("Search Results");
        new EmployeeSearch(theController.getTheManager(), info);
        //doSearch(info.getFieldParams());

    }


    /**
     * Performs the search
     * @param params combination of 1 and 0 representing the parameters user used for search
     */
    private void doSearch(String params){
        for(int pl = 0; pl < 4; pl++) {
            System.err.println(xtractParam(params, pl));
        }
    }

    /**
     * Extracts the individual parameters and turns them into binary values for
     * program to determine parameters user use for search
     * @param params combination of 1 and 0 representing the parameters user used for search
     * @param pl the decimal place of which the search algorithm is working on - decimal place indicates
     *           the parameter of which the user has provided information for, which will be used for the search
     * @return integer 1 or 0 in representation of binary values indicating the users use of a parameter in search
     */
    private int xtractParam(String params, int pl){
        return Integer.parseInt(String.valueOf(params.charAt(pl)));
    }


    /**
     * Sets buttons for accept or cancel of user input
     */
    private void setButtons(){
        ButtonType close = new ButtonType(CLOSETXT,ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(close);
        setButtonActions(close);
    }

    /**
     * Sets actions for the user buttons
     * @param close button type
     */
    private void setButtonActions(ButtonType close){
        Button closeBtn = (Button) getDialogPane().lookupButton(close);
        closeBtn.setOnAction(push->close());

    }

    /**
     * Creates employee profile creation form
     */
    private void createDisplay(){
        BorderPane bp = new BorderPane();
        Text prompt = new Text("Results:");
        bp.getChildren().addAll(prompt);
        getDialogPane().setContent(bp);
    }

}
