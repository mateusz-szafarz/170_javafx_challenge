package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
    private ContextMenu tableContextMenu;

    @FXML
    TableColumn<Contact, String> firstName, lastName, phoneNumber, notes;

    public void initialize() {

        initializeTableContextMenu();
        table.setRowFactory(param -> {
            TableRow<Contact> row = new TableRow<>();

            row.emptyProperty().addListener(
                    ((observable, wasEmpty, isNowEmpty) -> {
                        if (isNowEmpty) {
                            row.setContextMenu(null);
                        } else {
                            row.setContextMenu(tableContextMenu);
                        }
                    })
            );

            return row;
        });

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.setItems(Main.contactData.getContacts());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().selectFirst();
    }

    private void initializeTableContextMenu() {
        tableContextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(event -> {
            Contact item = table.getSelectionModel().getSelectedItem();
            try {
                displayEditContactDialog(item);
            } catch (IOException e) {
                System.out.println("couldn't load fxml for contact dialog");
                e.printStackTrace();
            }
        });
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(event -> {
            Contact item = table.getSelectionModel().getSelectedItem();
            deleteItem(item);
        });
        tableContextMenu.getItems().addAll(editMenuItem, deleteMenuItem);
    }

    private void deleteItem(Contact item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact Item");
        alert.setHeaderText("Delete item: " + item.getFirstName());
        alert.setContentText("Are you sure?  Press OK to confirm, or cancel to Back out.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            Main.contactData.deteteContact(item);
        }
    }

    private void displayEditContactDialog(Contact item) throws IOException {
        Dialog<ButtonType> dialog = getButtonTypeDialog();
        FXMLLoader fxmlLoader = loadFXMLViewAndSetDialogContent(dialog);
        ContactDialogController controller = fxmlLoader.getController();

        dialog.setTitle("Edit Contact");
        dialog.setHeaderText("Use this dialog to edit a contact item");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        controller.fillInFields(item);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.processResults(item);
        }
    }

    @FXML
    public void displayNewContactDialog() throws IOException {
        Dialog<ButtonType> dialog = getButtonTypeDialog();
        FXMLLoader fxmlLoader = loadFXMLViewAndSetDialogContent(dialog);
        ContactDialogController controller = fxmlLoader.getController();

        dialog.setTitle("Add New Contact");
        dialog.setHeaderText("Use this dialog to create a new contact item");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Contact newItem = controller.processResults();
            Main.contactData.addContact(newItem);
            table.getSelectionModel().select(newItem);
        }
    }

    private FXMLLoader loadFXMLViewAndSetDialogContent(Dialog<ButtonType> dialog) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());
        return fxmlLoader;
    }

    private Dialog<ButtonType> getButtonTypeDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainVBoxPane.getScene().getWindow());
        return dialog;
    }

    @FXML
    public void closeTheApp() {
        Platform.exit();
    }
}
