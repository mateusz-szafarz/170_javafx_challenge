package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static ContactData contactData = new ContactData();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start");
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Contacts");
        primaryStage.setScene(new Scene(root, 750, 475));
        primaryStage.show();

        for (Contact contact : contactData.getContacts()) {
            System.out.println(contact.getFirstName());
        }
    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
        super.init();

        contactData.loadContacts();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        super.stop();

        contactData.saveContacts();
    }
}
