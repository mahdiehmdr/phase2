package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import Model.*;
import util.Constants;

public class ProfileMenu {
    //main page:
    @FXML
    Button myProfile;
    @FXML
    Button changeUsername;
    @FXML
    Button changePassword;
    @FXML
    Button changeEmail;
    @FXML
    Button changeNickname;
    @FXML
    Button showDeck;
    @FXML
    Button mainMenu;
    //username changer:
    @FXML
    TextField newUsername;
    @FXML
    Button usernameSubmit;
    @FXML
    Button usernameReturn;
    @FXML
    Label labelUsername;
    //password changer:
    @FXML
    TextField oldPass;
    @FXML
    TextField newPass;
    @FXML
    Button passwordSubmit;
    @FXML
    Button passwordReturn;
    @FXML
    Label labelPassword;
    //email changer:
    @FXML
    TextField newEmail;
    @FXML
    Button emailSubmit;
    @FXML
    Button emailReturn;
    @FXML
    Label emailLabel;
    //nickname changer:
    @FXML
    TextField newNickname;
    @FXML
    Button nicknameSubmit;
    @FXML
    Button nicknameReturn;
    @FXML
    Label nicknameLabel;
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event){
        try{
            switchScene(event, "MainPage.fxml");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleMyProfileButtonAction(ActionEvent event){
        System.out.println(Constants.loggedInUser);
        try{
            switchScene(event, "showProfile.fxml");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleChangeUsernameButtonAction(ActionEvent event){
        try{
            switchScene(event, "changeUsername.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleUsernameSubmitButtonAction(ActionEvent event){
        String newUsername = this.newUsername.getText();
        Constants.profileMenu.changeUsername(newUsername, labelUsername);
    }
    @FXML
    private void returnToProfileMenuButtonAction(ActionEvent event){
        try{
            switchScene(event, "ProfilePage.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleChangePasswordButtonAction(ActionEvent event){
        try {
            switchScene(event, "changePassword.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handlePasswordSubmitButtonAction(){
        String oldPassword = oldPass.getText();
        String newPassword = newPass.getText();
        Constants.profileMenu.changePassword(oldPassword, newPassword, labelPassword);
    }
    @FXML
    private void handleChangeEmailButtonAction(ActionEvent event){
        try{
            switchScene(event, "changeEmail.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleEmailSubmitButtonAction(){
        String newEmail = this.newEmail.getText();
        Constants.profileMenu.changeEmail(newEmail, emailLabel);
    }
    @FXML
    private void handleNicknameSubmitButtonAction(){
        String newNickname = this.newNickname.getText();
        Constants.profileMenu.changeNickname(newNickname, nicknameLabel);
    }
    @FXML
    private void handleChangeNicknameButtonAction(ActionEvent event){
        try{
            switchScene(event, "changeNickname.fxml");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void handleShowDeckButtonAction(ActionEvent event){

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
