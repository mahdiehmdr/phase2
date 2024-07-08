package Controller;

import Model.Outputs;
import Model.RegistryMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import Model.Game;
import Model.User;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import util.Constants;

public class GameMenu implements Initializable {

    @FXML
    ImageView harryIm, ronIm, hermioneIm, dracoIm;
    @FXML
    Button doubleBtn, betBtn;
    @FXML
    Text modePick, hostCharPick, guestCharPick, guestCoin, hostCoin, error;
    @FXML
    TextField hostAdd, guestAdd, hostChar, guestChar;
    private int hostPay, guestPay;
    private boolean bet;
    private String guestCharacter, hostCharacter;
    public void guestLogin(){
        if(bet) {
            hostCoin.setVisible(true);
            hostAdd.setVisible(true);
            guestCoin.setVisible(true);
            guestAdd.setVisible(true);
        }
        else
            chooseChars();
    }
    public void chooseChars(){
        harryIm.setVisible(true);
        ronIm.setVisible(true);
        hermioneIm.setVisible(true);
        dracoIm.setVisible(true);
        hostCharPick.setVisible(true);
        guestCharPick.setVisible(true);
        guestChar.setVisible(true);
        hostChar.setVisible(true);
    }
    @FXML
    public void doubleBtn(ActionEvent event) {
        Constants.game.chooseMood("double");
        guestLogin();
    }
    @FXML
    public void betBtn(ActionEvent event) {
        bet=true;
        guestLogin();
    }
    @FXML
    public void hostAdd(ActionEvent event) throws IOException {
        if(Constants.game.getBetCoinsFromHost(hostAdd.getText()).isEmpty()){
            hostPay=Integer.parseInt(hostAdd.getText());
            Constants.hostPlayer.addCoin(-1*hostPay);
            Constants.game.addBetToCoin(guestPay);
            if(hostPay!=0&&guestPay!=0&&Constants.game.getBetCoinsFromHost(hostAdd.getText()).isEmpty())
                chooseChars();
        }
        else
            error.setText(Constants.game.getBetCoinsFromHost(hostAdd.getText()));
    }
    @FXML
    public void guestAdd(ActionEvent event) throws IOException {
        if(Constants.game.getBetCoinsFromGuest(guestAdd.getText()).isEmpty()){
            guestPay=Integer.parseInt(guestAdd.getText());
            Constants.guestPlayer.addCoin(-1*guestPay);
            Constants.game.addBetToCoin(guestPay);
            if(hostPay!=0&&guestPay!=0&&Constants.game.getBetCoinsFromGuest(guestAdd.getText()).isEmpty())
                chooseChars();
        }
        else
            error.setText(Constants.game.getBetCoinsFromGuest(guestAdd.getText()));
    }
    @FXML
    public void guestChar(ActionEvent event) throws IOException {
        guestCharacter=guestChar.getText();
        if(!Constants.game.chooseGuestCharachter(guestCharacter))
            guestCharacter=null;
        if(guestCharacter!=null&&hostCharacter!=null){
            switchScene(event,"TimeLine.fxml");
        }
    }
    @FXML
    public void hostChar(ActionEvent event) throws IOException {
        hostCharacter=hostChar.getText();
        if(!Constants.game.chooseHostCharachter(hostCharacter))
            hostCharacter=null;
        if(guestCharacter!=null&&hostCharacter!=null){
            switchScene(event,"TimeLine.fxml");
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
            case ESCAPE:
                Platform.exit();
                break;
            // Handle other keys if necessary
            default:
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        harryIm.setVisible(false);
        ronIm.setVisible(false);
        hermioneIm.setVisible(false);
        dracoIm.setVisible(false);
        hostAdd.setVisible(false);
        guestAdd.setVisible(false);
        hostCoin.setVisible(false);
        guestCoin.setVisible(false);
        hostChar.setVisible(false);
        guestChar.setVisible(false);
        hostCharPick.setVisible(false);
        guestCharPick.setVisible(false);
        hostPay=0;
        guestPay=0;
        hostCharacter=null;
        guestCharacter=null;
        error.setText("");
        bet=false;
        Constants.hostPlayer.setUsername("Not Mahdieh");
//        hostPlayer=new User("mahdieh","mhd","email","mmm");
//        guestPlayer=new User("parnia","rzie","yahoo","ppp");
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
}
