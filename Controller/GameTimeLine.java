package Controller;

import Model.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXMLLoader;
import javafx.util.Duration;
import util.Constants;

public class GameTimeLine implements Initializable {
    @FXML
    Label hostName, guestName, hostHP, guestHP, hostDamage, guestDamage, round;
    @FXML
    Button hostDeploy, guestDeploy;
    @FXML
    ProgressBar hostBar, guestBar;
    private cardBlock[] hostLine = new cardBlock[21], guestLine = new cardBlock[21];
    private cardBlock[] guestDeck = new cardBlock[6], hostDeck = new cardBlock[6];
    private Timeline hostProgressTimeline, guestProgressTimeline;

    private cardBlock hostP, guestP;
    @FXML
    ImageView H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, H18, H19, H20, H21,
            G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15, G16, G17, G18, G19, G20, G21,
            HD1, HD2, HD3, HD4, HD5, HD6, GD1, GD2, GD3, GD4, GD5, GD6, hostProf, guestProf, voldy;
    MediaPlayer mediaPlayer=new MediaPlayer(Constants.gameBackGroung);

    private int draggingDeckIndex;
    private String draggingType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constants.BackGround.pause();
        if(!Constants.mute) {
            mediaPlayer.play();
            mediaPlayer.setVolume(Constants.volume);
        }
        Constants.game.run(Constants.cards, Constants.spells, Constants.registryMenu);

        ImageView[] hostImageViews = {H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, H18, H19, H20, H21};
        for (int i = 0; i < 21; i++) {
            ImageView hostImageView = hostImageViews[i];
            hostLine[i] = new cardBlock(hostImageView, i, "host");
            enableDragOver(hostLine[i]);
        }

        ImageView[] guestImageViews = {G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15, G16, G17, G18, G19, G20, G21};
        for (int i = 0; i < 21; i++) {
            ImageView guestImageView = guestImageViews[i];
            guestLine[i] = new cardBlock(guestImageView, i, "guest");
            enableDragOver(guestLine[i]);
        }

        ImageView[] guestCards = {GD1, GD2, GD3, GD4, GD5, GD6};
        ImageView[] hostCards = {HD1, HD2, HD3, HD4, HD5, HD6};

        for (int i = 0; i < 6; i++) {
            ImageView guestImageView = guestCards[i];
            guestDeck[i] = new cardBlock(guestImageView, i, "guest");
            enableDragAndDrop(guestDeck[i]);

            ImageView hostImageView = hostCards[i];
            hostDeck[i] = new cardBlock(hostImageView, i, "host");
            enableDragAndDrop(hostDeck[i]);
        }

        hostName.setText(Constants.hostPlayer.getUsername());
        guestName.setText(Constants.guestPlayer.getUsername());
        hostP = new cardBlock(hostProf, -1, "host");
        guestP = new cardBlock(guestProf, -1, "guest");

        hostBar.setVisible(false);
        guestBar.setVisible(false);
        hostDeploy.setVisible(false);
        guestDeploy.setVisible(false);

        scan();
    }

    private void enableDragAndDrop(cardBlock card) {
        card.getImageView().setOnDragDetected(event -> {
            if ((card.getType().equals("host") && Constants.game.isHostPlaying()) || (card.getType().equals("guest") && !Constants.game.isHostPlaying())) {
                Dragboard db = card.getImageView().startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(card.getImageView().getImage());
                db.setContent(content);
                draggingDeckIndex = card.getIndex();
                draggingType = card.getType();
                event.consume();
            }
        });

        card.getImageView().setOnDragDone(DragEvent::consume);
    }

    private void enableDragOver(cardBlock card) {
        card.getImageView().setOnDragOver(event -> {
            if (event.getGestureSource() != card.getImageView() && event.getDragboard().hasImage()) {
                if ((card.getType().equals("host") && Constants.game.isHostPlaying()) || (card.getType().equals("guest") && !Constants.game.isHostPlaying())) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            }
            event.consume();
        });

        card.getImageView().setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                deploy(draggingDeckIndex, card.getIndex(), card.getType());
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }
    public void deploy(int deckIndex, int lineIndex, String type) {
        int block = lineIndex + 1;
        Card card;
        if (type.equals("host"))
            card = Constants.game.getHostCards()[deckIndex];
        else
            card = Constants.game.getGuestCards()[deckIndex];

        if (card.getClass().equals(Damage_Heal.class)) {
            if(Constants.game.isHostPlaying())
                Constants.game.deploy("place card " + card.getName() + " in block " + block,"");
            else
                Constants.game.deploy("place card " + card.getName() + " in block " + block,"");
            Constants.game.checkTimeLine();
            MediaPlayer media=new MediaPlayer(Constants.drop);
            if(!Constants.mute){
                media.play();
                media.setVolume(Constants.volume);
            }
            scan();
            if(Constants.game.isHostPlaying())
                Constants.game.setGuestPlaying();
            else
                Constants.game.setHostPlaying();
        } else {
            int n=Constants.random.nextInt(5);
            Constants.game.deploy("deploy card " + card.getName(), String.valueOf(n));
            Constants.game.checkTimeLine();
            scan();
            if(Constants.game.isHostPlaying())
                Constants.game.setGuestPlaying();
            else
                Constants.game.setHostPlaying();        }
        if(Constants.game.getRound()>=5)
            endGame();
    }

    private void switchScene(String fxmlFile) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene newScene = new Scene(newPage);
        newScene.getStylesheets().add(getClass().getResource("CSS/Hello.css").toExternalForm());

        Stage stage = (Stage) hostProf.getScene().getWindow();

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

    public void scan() {
        for (int i = 0; i < 21; i++) {
            if (Constants.game.getGuestTimeLine()[i].isDestroyed())
                guestLine[i].blockDestroy();
            else if (Constants.game.getGuestTimeLine()[i].getCard() == null)
                guestLine[i].setImage(Constants.block);
            else
                guestLine[i].setImage(getCardImage(Constants.game.getGuestTimeLine()[i].getCard()));
            if(Constants.game.getGuestTimeLine()[i].hasFailed())
                guestLine[i].setFailed();

            if (Constants.game.getHostTimeLine()[i].isDestroyed())
                hostLine[i].blockDestroy();
            else if (Constants.game.getHostTimeLine()[i].getCard() == null)
                hostLine[i].setImage(Constants.block);
            else
                hostLine[i].setImage(getCardImage(Constants.game.getHostTimeLine()[i].getCard()));
            if(Constants.game.getHostTimeLine()[i].hasFailed())
                hostLine[i].setFailed();

            if (i < 6) {
                if (Constants.game.getHostCards()[i] != null)
                    hostDeck[i].setImage(getCardImage(Constants.game.getHostCards()[i]));
                if (Constants.game.getGuestCards()[i] != null)
                    guestDeck[i].setImage(getCardImage(Constants.game.getGuestCards()[i]));
            }
            round.setText("Round: "+String.valueOf(Constants.game.getRound()));
        }
        hostHP.setText("HP: " + Constants.hostPlayer.getHp());
        hostDamage.setText("Damage: " + Constants.game.getHostDamage());
        guestHP.setText("HP: " + Constants.guestPlayer.getHp());
        guestDamage.setText("Damage: " + Constants.game.getGuestDamage());
        hostP.setImage(getCharIm(Constants.hostPlayer.getCharacter()));
        guestP.setImage(getCharIm(Constants.guestPlayer.getCharacter()));
    }

    public class cardBlock {
        private ImageView image;
        private int index;
        private String type;

        public cardBlock(ImageView image, int index, String type) {
            this.image = image;
            this.index = index;
            this.type = type;
        }

        public ImageView getImageView() {
            return image;
        }

        public int getIndex() {
            return index;
        }

        public String getType() {
            return type;
        }

        public void blockDestroy() {
            this.image.setVisible(false);
        }

        public void setImage(Image image) {
            this.image.setImage(image);
        }
        public void setFailed(){
            this.image.setOpacity(0.4);
            Effect box = new BoxBlur();
            this.image.setEffect(box);
        }
        public void reset(){
            this.image.setImage(Constants.block);
            this.image.setVisible(true);
            this.image.setOpacity(1);
            this.image.setEffect(null);
        }
    }
    public void endGame(){
        for(int i=0; i<6; i++){
            hostDeck[i].blockDestroy();
            guestDeck[i].blockDestroy();
            round.setVisible(false);
            voldy.setVisible(true);
        }
        timeLine();
    }
    public void timeLine() {
        double startX = -100;
        double endX = 1200;

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(7), voldy);
        translateTransition.setFromX(startX);
        translateTransition.setToX(endX);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(7), voldy);
        rotateTransition.setByAngle(1500);

        ParallelTransition parallelTransition = new ParallelTransition(voldy, translateTransition, rotateTransition);

        AtomicInteger counter = new AtomicInteger(0);

        Timeline damageTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int currentIndex = counter.get();
            int endIndex = currentIndex + 3;

            if (endIndex > 21) {
                endIndex = 21;
            }

            for (int i = currentIndex; i < endIndex; i++) {
                if (Constants.game.getHostTimeLine()[i].getCard() != null &&
                        !Constants.game.getHostTimeLine()[i].hasFailed() &&
                        !Constants.game.getHostTimeLine()[i].isDestroyed()) {

                    Damage_Heal hostCard = (Damage_Heal) Constants.game.getHostTimeLine()[i].getCard();
                    Constants.guestPlayer.reduceHP(hostCard.getDamage() / hostCard.getDuration());
                    if (Constants.guestPlayer.getHp() <= 0)
                        return;
                }

                if (Constants.game.getGuestTimeLine()[i].getCard() != null &&
                        !Constants.game.getGuestTimeLine()[i].hasFailed() &&
                        !Constants.game.getGuestTimeLine()[i].isDestroyed()) {

                    Damage_Heal guestCard = (Damage_Heal) Constants.game.getGuestTimeLine()[i].getCard();
                    Constants.hostPlayer.reduceHP(guestCard.getDamage() / guestCard.getDuration());
                    if (Constants.hostPlayer.getHp() <= 0)
                        return;
                }
                hostLine[i].reset();
                guestLine[i].reset();
            }

            counter.addAndGet(3);

            scan();
        }));

        damageTimeline.setCycleCount(7);

        parallelTransition.setOnFinished(event -> {
            voldy.setVisible(false);
            hostBar.setVisible(false);
            guestBar.setVisible(false);
            hostDeploy.setVisible(false);
            guestDeploy.setVisible(false);

            hostProgressTimeline.stop();
            guestProgressTimeline.stop();

            if (Constants.hostPlayer.getHp() > 0 && Constants.guestPlayer.getHp() > 0) {
                reinitializeGame();
            } else {
                try {
                    switchScene("PrizePage.fxml");
                    mediaPlayer.pause();
                    if(!Constants.mute) {
                        Constants.restartBackGroundMedia();
                        Constants.BackGround.setVolume(Constants.volume);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Show progress bars and buttons
        hostBar.setVisible(true);
        guestBar.setVisible(true);
        hostDeploy.setVisible(true);
        guestDeploy.setVisible(true);

        hostProgressTimeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
            hostBar.setProgress(Math.random());
        }));
        hostProgressTimeline.setCycleCount(Timeline.INDEFINITE);
        hostProgressTimeline.play();

        guestProgressTimeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
            guestBar.setProgress(Math.random());
        }));
        guestProgressTimeline.setCycleCount(Timeline.INDEFINITE);
        guestProgressTimeline.play();

        parallelTransition.play();
        damageTimeline.play();
    }

    @FXML
    public void hostDeploy() {
        hostProgressTimeline.stop();
        if(Constants.guestPlayer.getHp()>0)
            Constants.guestPlayer.reduceHP((int) hostBar.getProgress()*Constants.guestPlayer.getHp());
    }

    @FXML
    public void guestDeploy() {
        guestProgressTimeline.stop();
        if(Constants.hostPlayer.getHp()>0)
            Constants.hostPlayer.reduceHP((int) guestBar.getProgress()*Constants.hostPlayer.getHp());
    }

    private void reinitializeGame() {
        Constants.game.run(Constants.cards, Constants.spells, Constants.registryMenu);
        ImageView[] hostImageViews = {H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, H18, H19, H20, H21};
        for (int i = 0; i < 21; i++) {
            ImageView hostImageView = hostImageViews[i];
            hostLine[i] = new cardBlock(hostImageView, i, "host");
            enableDragOver(hostLine[i]);
            hostLine[i].reset();
        }

        ImageView[] guestImageViews = {G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15, G16, G17, G18, G19, G20, G21};
        for (int i = 0; i < 21; i++) {
            ImageView guestImageView = guestImageViews[i];
            guestLine[i] = new cardBlock(guestImageView, i, "guest");
            enableDragOver(guestLine[i]);
            guestLine[i].reset();
        }

        ImageView[] guestCards = {GD1, GD2, GD3, GD4, GD5, GD6};
        ImageView[] hostCards = {HD1, HD2, HD3, HD4, HD5, HD6};

        for (int i = 0; i < 6; i++) {
            ImageView guestImageView = guestCards[i];
            guestDeck[i] = new cardBlock(guestImageView, i, "guest");
            enableDragAndDrop(guestDeck[i]);
            guestDeck[i].reset();
            ImageView hostImageView = hostCards[i];
            hostDeck[i] = new cardBlock(hostImageView, i, "host");
            enableDragAndDrop(hostDeck[i]);
            hostDeck[i].reset();
        }
        round.setVisible(true);

        scan();
    }

    public Image getCardImage(Card card) {
        switch (card.getName()) {
            case "QUILIN":
                return Constants.quilin;
            case "HIPPOGRIFF":
                return Constants.hippogriff;
            case "PHONIX":
                return Constants.phonix;
            case "CENTAUR":
                return Constants.centaur;
            case "UNICORN":
                return Constants.unicorn;
            case "NIFFLER":
                return Constants.niffler;
            case "THESTRAL":
                return Constants.thestral;
            case "HOUSEELF":
                return Constants.dobby;
            case "DEATHEATOR":
                return Constants.deatheator;
            case "BOGGART":
                return Constants.boggart;
            case "WERWOLF":
                return Constants.werwolf;
            case "ARAGOG":
                return Constants.aragog;
            case "VAMPIRE":
                return Constants.vampire;
            case "BASILLISK":
                return Constants.basillisk;
            case "DEMENTOR":
                return Constants.dementor;
            case "PEEVES":
                return Constants.peeves;
            case "SECTUMSEMPRA":
                return Constants.sectumsempra;
            case "CORNISH PIXIE":
                return Constants.pixie;
            case "HEDWIG":
                return Constants.hedwig;
            case "MADAM POMFREY":
                return Constants.madam;
            case "MERPEOPLE":
                return Constants.merpeople;
            case "WHOMPING WILLOW":
                return Constants.willow;
            case "GOBLIN":
                return Constants.goblin;
            case "PATRONUS":
                return Constants.patronus;
            case "FLUFFY":
                return Constants.fluffy;
        }
        return null;
    }

    public Image getCharIm(String character) {
        switch (character) {
            case "Harry Potter":
                return Constants.harryProf;
            case "Draco Malfoy":
                return Constants.dracoProf;
            case "Hermione Granger":
                return Constants.hermioneProf;
            case "Ronald Weasley":
                return Constants.ronProf;
        }
        return null;
    }
}
