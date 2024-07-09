package Controller;

import Model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.Constants;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    @FXML
    Button logout;
    @FXML
    Button returnToMainMenu;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button submit;
    @FXML
    Label label;
    @FXML
    private void handleReturnToMainMenuButtonAction(ActionEvent event){
        try{
            switchScene(event, "MainPage.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleSubmitButtonAction(ActionEvent event){
        String username = this.username.getText();
        String password = this.password.getText();
        if(username.isEmpty() || password.isEmpty()) label.setText(Constants.outputs.emptyField);
        else if (username.equals(Constants.loggedInUser.getUsername())) {
            label.setText("Choose another username!");
        }
        else if(Constants.registryMenu.findUserByUsername(username) == null){
            label.setText(Constants.outputs.usernameDoesNotExist);
        }
        else{
            User user = Constants.registryMenu.findUserByUsername(username);
            if(!user.getPassword().equals(password)) label.setText(Constants.outputs.wrongPasswordEntered);
            else{
                Constants.secondUser = user;
                label.setText("OK!");
                try{
                    switchScene(event, "GamePage.fxml");
                }
                catch (IOException e){
                    System.out.println(e);
                }
            }
        }
    }
    @FXML
    private void logout(ActionEvent event){
        try{
            Constants.writeInformationInFile();
            switchScene(event, "StarterPage.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
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
            switchScene(event, "guestLogin.fxml");
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
