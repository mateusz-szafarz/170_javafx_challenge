package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    @FXML
    TableView<Contact> table;

    @FXML
    TableColumn<Contact, String> firstName;
    @FXML
    TableColumn<Contact, String> lastName;
    @FXML
    TableColumn<Contact, String> phoneNumber;
    @FXML
    TableColumn<Contact, String> notes;

    public void initialize() {
//        table.

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.setItems(Main.contactData.getContacts());


    }

    @FXML
    public void displayNewContactDialog() {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Contact contact = Main.contactData.getContacts().get(0);
        contact.setNotes(formatter.format(new Date()));
    }
}
