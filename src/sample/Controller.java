package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {

    @FXML
    TableView table;

    public void initialize() {
        System.out.println("Initializing controller");

//        table.setItems(Main.contactData.getContacts());
    }
}
