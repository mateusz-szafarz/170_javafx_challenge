package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactDialogController {

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextArea notesArea;

    Contact processResults() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String notes = notesArea.getText().trim();
        return new Contact(firstName, lastName, phoneNumber, notes);
    }
}
