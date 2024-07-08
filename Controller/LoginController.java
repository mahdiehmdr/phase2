package Controller;

import Model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Constants;

import java.io.IOException;

public class LoginController {
    private int counter = 0;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button submit;
    @FXML
    Label loginErrors;
    @FXML
    Button returnToHome;
    @FXML
    private void handleSubmitButtonAction(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = Constants.registryMenu.findUserByUsername(username);
        if(user == null) loginErrors.setText(Constants.outputs.usernameDoesNotExist);
        else{
            boolean problem = Constants.login(username, password, loginErrors);
            if(problem){
                passwordField.clear();
                counter += 1;
                int delay = counter * 5;
                displayCoolDownMessage(delay);
            }
            else{
                counter = 0;
                loginErrors.setText("");
                try{
                    switchScene(event, "MainPage.fxml");
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void handleReturnToHomeButtonAction(ActionEvent event){
        try{
            switchScene(event, "StarterPage.fxml");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void displayCoolDownMessage(int cooldownSeconds){
        final int[] remainingTime = { cooldownSeconds };
        loginErrors.setText("Please try again in " + cooldownSeconds + " seconds.");
        submit.setDisable(true);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingTime[0] -= 1;
                    if(remainingTime[0] > 0){
                        loginErrors.setText("Please try again in " + remainingTime[0] + " seconds.");
                    }
                    else{
                        loginErrors.setText("");
                        timeline.stop();
                        submit.setDisable(false);
                    }
                })
        );
        timeline.setCycleCount(cooldownSeconds);
        timeline.play();
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
}