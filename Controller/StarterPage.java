package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.Constants;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class StarterPage implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constants.getInformationFromFile();
        Constants.BackGround.play();
    }
    @FXML
    public void signupBtn(ActionEvent event) {
        try {
            switchScene(event, "SignupPage.fxml");
            Constants.getInformationFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loginBtn(ActionEvent event) {
        try {
            switchScene(event, "LoginPage.fxml");
            Constants.getInformationFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void exitBTN(){
       // Constants.writeInformationInFile();
        Platform.exit();
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
            case ESCAPE:
                Platform.exit();
                break;
            default:
                break;
        }
    }
}
