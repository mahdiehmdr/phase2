package Controller;

import Model.CaptchaGenerator;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import util.Constants;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegistryMenu {
    final private CaptchaGenerator captchaGenerator = new CaptchaGenerator();
    @FXML
    Label label;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField passwordConfirmationField;
    @FXML
    TextField emailField;
    @FXML
    TextField nicknameField;
    @FXML
    Button signup;
    @FXML
    Button submit;
    @FXML
    TextField numberOfRecoveryQuestionField;
    @FXML
    TextField answerField;
    @FXML
    TextField answerConfirmationField;
    @FXML
    Label recoveryErrors;
    @FXML
    TextField captchaField;
    @FXML
    Label captchaLabel;
    @FXML
    Button captchaSubmit;
    @FXML
    Button showCaptcha;
    @FXML
    Label randomPass;
    @FXML
    TextField randomPasswordConfirmation;
    @FXML
    Button randomPasswordSubmit;
    @FXML
    Button randomPasswordReturn;
    @FXML
    Button showMyPassword;
    @FXML
    private void returnToStarterMenu(ActionEvent event) {
        try {
            switchScene(event, "StarterPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSignupButtonAction(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String passwordConfirmation = passwordConfirmationField.getText();
        String email = emailField.getText();
        String nickname = nicknameField.getText();
        boolean whatToDo = Constants.registryMenu.signup(username, password, passwordConfirmation, email, nickname, label);
        if(whatToDo && !password.equals("random")) {
            openNextPage("ChooseRecoveryQuestionPage.fxml", signup);
        }
        if(whatToDo && password.equals("random")){
            try{
                switchScene(event, "randomPassword.fxml");
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }
    @FXML
    private void handleShowMyPasswordButtonAction(){
        randomPass.setText(captchaGenerator.generateRandomStringForPassword());
    }
    @FXML
    private void handleRandomPasswordSubmitButtonAction(ActionEvent event){
        String passwordConfirmation = randomPasswordConfirmation.getText();
        String password = randomPass.getText();
        if(passwordConfirmation.equals(password)){
            Constants.registryMenu.getSignedUpUser().changePassword(password);
            try{
                switchScene(event, "ChooseRecoveryQuestionPage.fxml");
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        else{
            randomPasswordConfirmation.clear();
            randomPass.setText(captchaGenerator.generateRandomStringForPassword());
        }
    }
    @FXML
    private void handleReturnButtonAction(ActionEvent event){
        try{
            switchScene(event, "SignupPage.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleSubmitButtonAction(){
        String number = numberOfRecoveryQuestionField.getText();
        String answer = answerField.getText();
        String answerConfirmation = answerConfirmationField.getText();
        boolean everythingOk = Constants.registryMenu.chooseSecurityQuestion(number, answer, answerConfirmation, recoveryErrors);
        if(everythingOk) {
            openNextPage("CaptchaPage.fxml", submit);
        }
    }
    @FXML
    private void handleShowTheCaptchaButtonAction(){
        String captcha = captchaGenerator.simpleCaptcha();
        captchaLabel.setText(captcha);
    }
    @FXML
    private void handleCaptchaSubmitButtonAction(ActionEvent event){
        String captcha = captchaLabel.getText();
        int result = captchaGenerator.resultOfSimpleCaptcha(captcha);
        String answer = captchaField.getText();
        int myAnswer = Integer.parseInt(answer);
        if(result == myAnswer){
            try{
                switchScene(event, "LoginPage.fxml");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            captchaField.clear();
            captcha = captchaGenerator.simpleCaptcha();
            captchaLabel.setText(captcha);
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
    }
    private void openNextPage(String fxml, Button button){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
