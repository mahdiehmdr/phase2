package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.Constants;

import java.io.IOException;

public class ShowProfileController {
    @FXML
    private Button returnHome;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private Label email;
    @FXML
    private Label nickname;
    @FXML
    private Label exp;
    @FXML
    private Label hp;
    @FXML
    private Label coin;
    @FXML
    public void initialize(){
        if(username != null)
            username.setText(Constants.loggedInUser.getUsername());
        if(password != null)
            password.setText(Constants.loggedInUser.getPassword());
        if(email != null)
            email.setText(Constants.loggedInUser.getEmail());
        if(nickname != null)
            nickname.setText(Constants.loggedInUser.getNickname());
        if(exp != null)
            exp.setText(Integer.toString(Constants.loggedInUser.getExp()));
        if(hp != null)
            hp.setText(Integer.toString(Constants.loggedInUser.getHp()));
        if(coin != null)
            coin.setText(Integer.toString(Constants.loggedInUser.getCoin()));
    }
    @FXML
    private void handleReturnHomeButtonAction(ActionEvent event){
        try{
            switchScene(event, "ProfilePage.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    private void switchScene(javafx.event.ActionEvent event, String fxmlFile) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene newScene = new Scene(newPage);
        newScene.getStylesheets().add(getClass().getResource("CSS/Hello.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
    }
}