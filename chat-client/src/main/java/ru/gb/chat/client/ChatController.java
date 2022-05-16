package ru.gb.chat.client;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private String nametochatarea = "Broadcast: ";

    @FXML
    private VBox mainPanel;

    @FXML
    private TextArea chatArea;

    @FXML
    private ListView<String> contacts;

    @FXML
    private TextField inputField;

    @FXML
    private Button btnSend;

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("mock");
    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }


    public void sendMessage(ActionEvent actionEvent) {
        String text = inputField.getText();
        if (text == null || text.isBlank()) {
            return;
        }
        chatArea.appendText(nametochatarea + text + System.lineSeparator());
        inputField.clear();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> names = List.of("Send to everyone", "Vasya", "Masha", "Petya", "Valera", "Nastya");
        contacts.setItems(FXCollections.observableList(names));
        contacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1 == "Send to everyone") {
                    nametochatarea = "Broadcast: ";
                }
                else nametochatarea = t1 + ": ";
            }
        });

    }

    public void openHelpWindow(ActionEvent actionEvent) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        TextArea helptext = new TextArea();
        helptext.setText("Help info!");
        helptext.setEditable(false);

        Scene scene = new Scene(helptext, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}