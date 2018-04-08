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

    void fillInFields(Contact item) {
        firstNameField.setText(item.getFirstName());
        lastNameField.setText(item.getLastName());
        phoneNumberField.setText(item.getPhoneNumber());
        notesArea.setText(item.getNotes());
    }

    Contact processResults() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String notes = notesArea.getText().trim();
        return new Contact(firstName, lastName, phoneNumber, notes);
    }

    void processResults(Contact item) {
        item.setFirstName(firstNameField.getText().trim());
        item.setLastName(lastNameField.getText().trim());
        item.setPhoneNumber(phoneNumberField.getText().trim());
        item.setNotes(notesArea.getText().trim());
    }
}
