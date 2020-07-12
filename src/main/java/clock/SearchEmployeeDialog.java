package clock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class SearchEmployeeDialog extends Dialog<String> {

    SearchEmployeeDialog(){
        super();
        setTitle("Search Employees");

    }


    private void createForm(){
        BorderPane bp = new BorderPane();
        ObservableList<String> items = FXCollections.observableArrayList("ID", "First Name", "Last Name",
                "Email Address", "Phone Number");
        VBox vb = new VBox();
        Label prompt = new Label("Search by:");
        ComboBox<String> byWhat = new ComboBox<>();
        byWhat.setItems(items);
        TextField tf = new TextField();
        Button searchBtn = new Button();
        searchBtn.setOnAction(push->searchEmployee(byWhat.getValue(), tf.getText()));
        vb.getChildren().addAll(prompt, byWhat);

    }

    private void searchEmployee(String  searchBy, String keywords){
        System.out.println("TODO: FINISH SEARCH");
    }






}
