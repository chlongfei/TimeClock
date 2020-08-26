package clock;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EmployeeShowAllDialog extends Dialog<ButtonType> {

    private static final String CLOSETXT = "Close";
    private ArrayList<Employee> theList;

    private EmployeeShowAllDialog(){
        super();
        setButtons();
        setTitle("Employee Master List");
        getDialogPane().setPrefWidth(800);
    }

    EmployeeShowAllDialog(GuiController ctrl) {
        this();
        theList = new ArrayList<>(ctrl.getTheManager().getMap().values());
        createDisplay();
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
        VBox vb = new VBox();
        vb.setStyle("-fx-padding: 10 10 10 10;");
        vb.setSpacing(5);
        Text pageTitle = new Text(15.0,18.0,"Employee List:");
        pageTitle.setFont(new Font(15));
        vb.getChildren().addAll(pageTitle,new EmployeeListDisplay(theList).getTableView());
        getDialogPane().setContent(vb);
    }

}
