
package util;

import Model.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Constants {
    public static final User hostPlayer = new User("mahdieh","mhd","email","mmm"), guestPlayer=new User("parnia","rzie","yahoo","ppp");
    public static User loggedInUser;
    public static final RegistryMenu registryMenu=new RegistryMenu();
    public static final ProfileMenu profileMenu = new ProfileMenu();
    public static final MainMenu mainMenu = new MainMenu();
    public static final Outputs outputs=new Outputs();
    public static Game game = new Game();
    public static boolean login(String username, String password, Label label){
        User user = registryMenu.findUserByUsername(username);
        boolean problem = true;
        if(password.equals(user.getPassword())){
            loggedInUser = user;
            label.setText(outputs.loggedInSuccessfully);
            problem = false;
        }
        return problem;
    }
    public static final Damage_Heal QUILIN = new Damage_Heal("QUILIN", 37, 4, 48, 5, 50, 245);
    public static final Damage_Heal HIPPOGRIFF = new Damage_Heal("HIPPOGRIFF", 28, 4, 32, 3, 30, 108);
    public static final Damage_Heal PHOENIX = new Damage_Heal("PHOENIX", 35, 5, 50, 7, 91, 315);
    public static final Damage_Heal CENTAUR = new Damage_Heal("CENTAUR", 25, 3, 24, 2, 20, 66);
    public static final Damage_Heal UNICORN = new Damage_Heal("UNICORN", 31, 5, 50, 4, 45, 164);
    public static final Damage_Heal NIFFLER = new Damage_Heal("NIFFLER", 23, 1, 10, 2, 15, 66);
    public static final Damage_Heal THESTRAL = new Damage_Heal("THESTRAL", 24, 1, 11, 2, 10, 70);
    public static final Damage_Heal HOUSEELF = new Damage_Heal("HOUSEELF", 30, 3, 27, 4, 35, 156);
    public static final Damage_Heal DEATHEATER = new Damage_Heal("DEATHEATER", 33, 4, 36, 6, 72, 256);
    public static final Damage_Heal BOGGART = new Damage_Heal("BOGGART", 21, 1, 10, 2, 20, 62);
    public static final Damage_Heal WEREWOLF = new Damage_Heal("WEREWOLF", 24, 3, 21, 3, 30, 91);
    public static final Damage_Heal ARAGOG = new Damage_Heal("ARAGOG", 22, 2, 16, 2, 25, 60);
    public static final Damage_Heal VAMPIRE = new Damage_Heal("VAMPIRE", 26, 3, 30, 4, 40, 144);
    public static final Damage_Heal BASILISK = new Damage_Heal("BASILISK", 35, 5, 40, 7, 84, 301);
    public static final Damage_Heal DEMENTOR = new Damage_Heal("DEMENTOR", 39, 5, 50, 8, 100, 392);

    public static final ArrayList<Damage_Heal> cards = new ArrayList<>(
            Arrays.asList(QUILIN, HIPPOGRIFF, PHOENIX, CENTAUR, UNICORN, NIFFLER, THESTRAL, HOUSEELF, DEATHEATER,
                    BOGGART, WEREWOLF, ARAGOG, VAMPIRE, BASILISK, DEMENTOR)
    );

    public static final Spell PEEVES = new Spell("PEEVES", 153);
    public static final Spell SECTUMSEMPRA = new Spell("SECTUMSEMPRA", 417);
    public static final Spell CORNISHPIXIE = new Spell("CORNISH PIXIE", 186);
    public static final Spell HEDWIG = new Spell("HEDWIG", 384);
    public static final Spell MADAM = new Spell("MADAM POMFREY", 285);
    public static final Spell MERPEOPLE = new Spell("MERPEOPLE", 318);
    public static final Spell WILLOW = new Spell("WHOMPING WILLOW", 252);
    public static final Spell GOBLIN = new Spell("GOBLIN", 351);
    public static final Spell PATRONUS = new Spell("PATRONUS", 450);
    public static final Spell FLUFFY = new Spell("FLUFFY", 219);

    public static final ArrayList<Spell> spells = new ArrayList<>(
            Arrays.asList(PEEVES, SECTUMSEMPRA, CORNISHPIXIE, HEDWIG, MADAM, MERPEOPLE, WILLOW,
                    GOBLIN, PATRONUS, FLUFFY)
    );
    public static final Random random=new Random();
    public static final Image quilin = new Image(Constants.class.getResourceAsStream("/cards/quilin.jpg"));
    public static final Image hippogriff= new Image(Constants.class.getResourceAsStream("/cards/hippogriff.jpg"));
    public static final Image phonix = new Image(Constants.class.getResourceAsStream("/cards/phonix.jpg"));
    public static final Image centaur = new Image(Constants.class.getResourceAsStream("/cards/centaur.jpg"));
    public static final Image unicorn = new Image(Constants.class.getResourceAsStream("/cards/unicorn.jpg"));
    public static final Image niffler = new Image(Constants.class.getResourceAsStream("/cards/niffler.jpg"));
    public static final Image thestral = new Image(Constants.class.getResourceAsStream("/cards/thestral.jpg"));
    public static final Image dobby = new Image(Constants.class.getResourceAsStream("/cards/elf.jpg"));
    public static final Image deatheator = new Image(Constants.class.getResourceAsStream("/cards/deatheator.jpg"));
    public static final Image boggart = new Image(Constants.class.getResourceAsStream("/cards/boggart.jpg"));
    public static final Image willow = new Image(Constants.class.getResourceAsStream("/cards/werwolf.jpg"));
    public static final Image aragog = new Image(Constants.class.getResourceAsStream("/cards/aragog.jpg"));
    public static final Image vampire = new Image(Constants.class.getResourceAsStream("/cards/vampire.jpg"));
    public static final Image basillisk = new Image(Constants.class.getResourceAsStream("/cards/basillisk.jpg"));
    public static final Image dementor = new Image(Constants.class.getResourceAsStream("/cards/dementor.jpg"));
    public static final Image peeves = new Image(Constants.class.getResourceAsStream("/cards/peeves.png"));
    public static final Image sectumsempra = new Image(Constants.class.getResourceAsStream("/cards/sectumsempra.png"));
    public static final Image pixie = new Image(Constants.class.getResourceAsStream("/cards/pixie.png"));
    public static final Image hedwig = new Image(Constants.class.getResourceAsStream("/cards/hedwig.png"));
    public static final Image madam = new Image(Constants.class.getResourceAsStream("/cards/madam.png"));
    public static final Image merpeople = new Image(Constants.class.getResourceAsStream("/cards/merpeople.png"));
    public static final Image goblin = new Image(Constants.class.getResourceAsStream("/cards/goblin.png"));
    public static final Image patronus = new Image(Constants.class.getResourceAsStream("/cards/patronus.png"));
    public static final Image fluffy = new Image(Constants.class.getResourceAsStream("/cards/fluffy.png"));
    public static final Image werwolf = new Image(Constants.class.getResourceAsStream("/cards/werwolf.jpg"));
    public static final Image block = new Image(Constants.class.getResourceAsStream("/cards/block.png"));
    public static final Image harryProf = new Image(Constants.class.getResourceAsStream("/cards/harryProf.png"));
    public static final Image ronProf = new Image(Constants.class.getResourceAsStream("/cards/ronProf.png"));
    public static final Image hermioneProf = new Image(Constants.class.getResourceAsStream("/cards/hermioneProf.png"));
    public static final Image dracoProf = new Image(Constants.class.getResourceAsStream("/cards/dracoProf.png"));

}
