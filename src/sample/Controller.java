package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    VBox mainVBoxPane;

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

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.setItems(Main.contactData.getContacts());


    }

    @FXML
    public void displayNewContactDialog() throws IOException {
        Dialog<ButtonType> dialog = getButtonTypeDialog();
        FXMLLoader fxmlLoader = loadFXMLView(dialog);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            NewContactDialogController controller = fxmlLoader.getController();
            Contact newItem = controller.processResults();
            Main.contactData.addContact(newItem);
            table.getSelectionModel().select(newItem);
        }
    }

    private FXMLLoader loadFXMLView(Dialog<ButtonType> dialog) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newContactDialog.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());
        return fxmlLoader;
    }

    private Dialog<ButtonType> getButtonTypeDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainVBoxPane.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        dialog.setHeaderText("Use this dialog to create a new contact item");
        return dialog;
    }

    @FXML
    public void closeTheApp() {
        Platform.exit();
    }
}
