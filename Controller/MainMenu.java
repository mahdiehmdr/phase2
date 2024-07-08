package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    @FXML
    public void back(ActionEvent event) {
        try {
            switchScene(event, "MainPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void myProfileBtn(ActionEvent event) {
        try {
            switchScene(event, "ProfilePage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void startGameBtn(ActionEvent event) {
        try {
            switchScene(event, "GamePage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void historyBtn(ActionEvent event) {
        try {
            switchScene(event, "HistoryPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void shopMenuBtn(ActionEvent event) {
        try {
            switchScene(event, "ShopPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene newScene = new Scene(newPage);
        newScene.getStylesheets().add(getClass().getResource("CSS/Hello.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();

        newScene.setOnKeyPressed(this::handleKeyPressed);
    }
    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                System.out.println("Enter key pressed");
                break;
            case ESCAPE:
                Platform.exit();
                break;
            // Handle other keys if necessary
            default:
                break;
        }
    }
}
