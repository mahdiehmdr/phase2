package Model;

import javafx.scene.control.Label;
import util.Constants;

import java.util.Scanner;
import java.util.regex.Matcher;
public class ProfileMenu extends RegistryMenu{
    public void changeUsername(String newUsername, Label label){
        if(!isUsernameCorrect(newUsername)){
            label.setText(output.wrongUsernameFormat);
        }
        else if(!isUsernameNew(newUsername)){
            label.setText(output.duplicateUsername);
        }
        else{
            Constants.loggedInUser.changeUsername(newUsername);
            label.setText("username changed successfully!");
        }
    }
    public void changeNickname(String newNickname, Label label){
        if(!isUsernameCorrect(newNickname)) label.setText("Please try again.");
        else{
            Constants.loggedInUser.changeNickname(newNickname);
            label.setText("Nickname changed successfully!");
        }
    }
    public void changeEmail(String newEmail, Label label){
        if(!isEmailCorrect(newEmail)) label.setText(output.wrongEmailFormat);
        else{
            Constants.loggedInUser.changeEmail(newEmail);
            label.setText("Email changed successfully!");
        }
    }
    public void changePassword(String oldPass, String newPass, Label label){
        if(oldPass.isEmpty() || newPass.isEmpty()) label.setText(output.emptyField);
        else if(!Constants.loggedInUser.getPassword().equals(oldPass)) label.setText(output.wrongPasswordEnteredForChange);
        else if(newPass.length() < 8 || newPass.length() > 32) label.setText(output.passwordLengthFault);
        else if(!doesPasswordContainsLowerCase(newPass) || !doesPasswordContainUpperCase(newPass)) label.setText(output.passwordDoesNotContainUpperOrLowerCase);
        else if(!doesPasswordContainNumber(newPass)) label.setText(output.passwordDoesNotContainNumber);
        else if(!passwordContainsCharacter(newPass)) label.setText(output.passwordDoesNotContainCharacters);
        else {
            Constants.loggedInUser.changePassword(newPass);
            label.setText(output.passwordChanged);
        }
    }
    public void showMyHistory(Scanner scanner, User user){
        if(user.getGames().isEmpty()) System.out.println("You haven't played yet. Returned to profile menu successfully.");
        else{
            System.out.println("sort by:\n1) date and time\n2) status(win/lose)\n3) the second player username\nEnter only the desired \"number\".");
            String num = scanner.nextLine();
            if(num.equals("1")){
                user.sortGamesByDateAndTime();
                String[] games = new String[user.getGames().size()];
                for(int i = 0; i < games.length; i++)
                    games[i] = user.getGames().get(i).toString();
                System.out.println("newest or oldest?");
                String condition = scanner.nextLine();
                if(condition.equals("newest")){
                    String[] upsideDown = new String[games.length];
                    int j = (games.length - 1);
                    for(int i = 0; i < games.length; i++){
                        upsideDown[j] = games[i];
                        j -= 1;
                    }
                    if(games.length <= 5){
                        for(int i = 0; i < games.length; i++)
                            System.out.println((i + 1) + ". " + upsideDown[i]);
                        System.out.println("You are in page: 1 / 1.\nYour current menu: Profile menu.");
                    }
                    else{
                        for(int i = 0; i < 5; i++)
                            System.out.println((i + 1) + ". " + upsideDown[i]);
                        int counter = 0;
                        int pageNumber = 1;
                        int maximumPages;
                        if(upsideDown.length % 5 == 0) maximumPages = upsideDown.length / 5;
                        else maximumPages = (upsideDown.length / 5 + 1);
                        System.out.println("You are in page 1 / " + maximumPages);
                        System.out.println("If you want to see other games:\n\tnext page\nTo return to the profile menu:\n\tback");
                        while(true) {
                            String command = scanner.nextLine();
                            if (command.equals("back")) {
                                System.out.println("Returned to profile menu successfully");
                                break;
                            }
                            else if (command.equals("next page")) {
                                if (pageNumber == maximumPages)
                                    System.out.println("invalid command!");
                                else {
                                    counter += 1;
                                    pageNumber += 1;
                                    if (((counter + 1) * 5) <= upsideDown.length) {
                                        for (int i = counter * 5; i < (counter + 1) * 5; i++) {
                                            System.out.println((i + 1) + ". " + upsideDown[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                    } else {
                                        for (int i = counter * 5; i < upsideDown.length; i++) {
                                            System.out.println((i + 1) + ". " + upsideDown[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                }
                            }
                            else if (command.equals("previous page")) {
                                if (pageNumber == 1) System.out.println("invalid command!");
                                else {
                                    counter -= 1;
                                    pageNumber -= 1;
                                    for (int i = (counter * 5); i < (counter + 1) * 5; i++) {
                                        System.out.println((i + 1) + ". " + upsideDown[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                    }
                }
                else if(condition.equals("oldest")){
                    if(games.length <= 5){
                        int i = 1;
                        for(String str : games) {
                            System.out.println(i + ". " + str);
                            i += 1;
                        }
                        System.out.println("You are in page: 1 / 1.\nYour current menu: Profile menu.");
                    }
                    else{
                        for(int i = 0; i < 5; i++)
                            System.out.println((i + 1) + ". " + games[i]);
                        int counter = 0;
                        int pageNumber = 1;
                        int maximumPages;
                        if(games.length % 5 == 0) maximumPages = games.length / 5;
                        else maximumPages = (games.length / 5 + 1);
                        System.out.println("You are in page 1 / " + maximumPages);
                        System.out.println("If you want to see other games:\n\tnext page\nTo return to the profile menu:\n\tback");
                        while(true) {
                            String command = scanner.nextLine();
                            if (command.equals("back")) {
                                System.out.println("Returned to profile menu successfully");
                                break;
                            }
                            else if (command.equals("next page")) {
                                if (pageNumber == maximumPages)
                                    System.out.println("invalid command!");
                                else {
                                    counter += 1;
                                    pageNumber += 1;
                                    if (((counter + 1) * 5) <= games.length) {
                                        for (int i = counter * 5; i < (counter + 1) * 5; i++) {
                                            System.out.println((i + 1) + ". " + games[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                    else {
                                        for (int i = counter * 5; i < games.length; i++) {
                                            System.out.println((i + 1) + ". " + games[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                }
                            }
                            else if (command.equals("previous page")) {
                                if (pageNumber == 1) System.out.println("invalid command!");
                                else {
                                    counter -= 1;
                                    pageNumber -= 1;
                                    for (int i = (counter * 5); i < (counter + 1) * 5; i++) {
                                        System.out.println((i + 1) + ". " + games[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                    }
                }
                else System.out.println("invalid order.\nReturned to profile menu successfully.");
            }
            else if(num.equals("2")){
                user.sortGamesBasedOnStatus();
                String[] games = new String[user.getGames().size()];
                for(int i = 0; i < games.length; i++)
                    games[i] = user.getGames().get(i).toString();
                System.out.println("win or lose first?");
                String whichOne = scanner.nextLine();
                if(whichOne.equals("win")){
                    if(games.length <= 5){
                        int i = 1;
                        for(int j = (games.length - 1); 0 <= j; j--){
                            System.out.println(i + ". " + games[j]);
                            i += 1;
                        }
                        System.out.println("You are in page: 1 / 1.\nYour current menu: Profile menu.");
                    }
                    else{
                        String[] revert = new String[games.length];
                        int j = 0;
                        for(int i = (games.length - 1); 0 <= i; i--){
                            revert[j] = games[i];
                            j += 1;
                        }
                        for(int i = 0; i < 5; i++)
                            System.out.println((i + 1) + ". " + revert[i]);
                        int counter = 0;
                        int pageNumber = 1;
                        int maximumPages;
                        if(games.length % 5 == 0) maximumPages = games.length / 5;
                        else maximumPages = (games.length / 5 + 1);
                        System.out.println("You are in page 1 / " + maximumPages);
                        System.out.println("If you want to see other games:\n\tnext page\nTo return to the profile menu:\n\tback");
                        while(true) {
                            String command = scanner.nextLine();
                            if (command.equals("back")) {
                                System.out.println("Returned to profile menu successfully");
                                break;
                            }
                            else if (command.equals("next page")) {
                                if (pageNumber == maximumPages)
                                    System.out.println("invalid command!");
                                else {
                                    counter += 1;
                                    pageNumber += 1;
                                    if (((counter + 1) * 5) <= revert.length) {
                                        for (int i = counter * 5; i < (counter + 1) * 5; i++) {
                                            System.out.println((i + 1) + ". " + revert[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                    else {
                                        for (int i = counter * 5; i < revert.length; i++) {
                                            System.out.println((i + 1) + ". " + revert[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                }
                            }
                            else if (command.equals("previous page")) {
                                if (pageNumber == 1) System.out.println("invalid command!");
                                else {
                                    counter -= 1;
                                    pageNumber -= 1;
                                    for (int i = (counter * 5); i < (counter + 1) * 5; i++) {
                                        System.out.println((i + 1) + ". " + revert[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                    }
                }
                else if(whichOne.equals("lose")){
                    if(games.length <= 5){
                        int i = 1;
                        for(String str : games){
                            System.out.println(i + ". " + str);
                            i += 1;
                        }
                        System.out.println("You are in page: 1 / 1.\nYour current menu: Profile menu.");
                    }
                    else{
                        for(int i = 0; i < 5; i++)
                            System.out.println((i + 1) + ". " + games[i]);
                        int counter = 0;
                        int pageNumber = 1;
                        int maximumPages;
                        if(games.length % 5 == 0) maximumPages = games.length / 5;
                        else maximumPages = (games.length / 5 + 1);
                        System.out.println("You are in page 1 / " + maximumPages);
                        System.out.println("If you want to see other games:\n\tnext page\nTo return to the profile menu:\n\tback");
                        while(true) {
                            String command = scanner.nextLine();
                            if (command.equals("back")) {
                                System.out.println("Returned to profile menu successfully");
                                break;
                            }
                            else if (command.equals("next page")) {
                                if (pageNumber == maximumPages)
                                    System.out.println("invalid command!");
                                else {
                                    counter += 1;
                                    pageNumber += 1;
                                    if (((counter + 1) * 5) <= games.length) {
                                        for (int i = counter * 5; i < (counter + 1) * 5; i++) {
                                            System.out.println((i + 1) + ". " + games[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                    else {
                                        for (int i = counter * 5; i < games.length; i++) {
                                            System.out.println((i + 1) + ". " + games[i]);
                                        }
                                        System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                        System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                    }
                                }
                            }
                            else if (command.equals("previous page")) {
                                if (pageNumber == 1) System.out.println("invalid command!");
                                else {
                                    counter -= 1;
                                    pageNumber -= 1;
                                    for (int i = (counter * 5); i < (counter + 1) * 5; i++) {
                                        System.out.println((i + 1) + ". " + games[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                    }
                }
                else System.out.println("invalid status! Your current menu: Profile menu");
            }
            else if(num.equals("3")){
                user.sortGamesBasedOnCompetitor();
                String[] games = new String[user.getGames().size()];
                for(int i = 0; i < games.length; i++)
                    games[i] = user.getGames().get(i).toString();
                if(games.length <= 5){
                    int i = 1;
                    for(String str : games) {
                        System.out.println(i + ". " + str);
                        i += 1;
                    }
                    System.out.println("You are in page: 1 / 1.\nYour current menu: Profile menu.");
                }
                else{
                    for(int i = 0; i < 5; i++)
                        System.out.println((i + 1) + ". " + games[i]);
                    int counter = 0;
                    int pageNumber = 1;
                    int maximumPages;
                    if(games.length % 5 == 0) maximumPages = games.length / 5;
                    else maximumPages = (games.length / 5 + 1);
                    System.out.println("You are in page 1 / " + maximumPages);
                    System.out.println("If you want to see other games:\n\tnext page\nTo return to the profile menu:\n\tback");
                    while(true) {
                        String command = scanner.nextLine();
                        if (command.equals("back")) {
                            System.out.println("Returned to profile menu successfully");
                            break;
                        }
                        else if (command.equals("next page")) {
                            if (pageNumber == maximumPages)
                                System.out.println("invalid command!");
                            else {
                                counter += 1;
                                pageNumber += 1;
                                if (((counter + 1) * 5) <= games.length) {
                                    for (int i = counter * 5; i < (counter + 1) * 5; i++) {
                                        System.out.println((i + 1) + ". " + games[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                                } else {
                                    for (int i = counter * 5; i < games.length; i++) {
                                        System.out.println((i + 1) + ". " + games[i]);
                                    }
                                    System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                    System.out.println("end.\nIf you want to go to the previous page:\n\tprevious page");
                                }
                            }
                        }
                        else if (command.equals("previous page")) {
                            if (pageNumber == 1) System.out.println("invalid command!");
                            else {
                                counter -= 1;
                                pageNumber -= 1;
                                for (int i = (counter * 5); i < (counter + 1) * 5; i++) {
                                    System.out.println((i + 1) + ". " + games[i]);
                                }
                                System.out.println("You are in page " + pageNumber + " / " + maximumPages);
                                System.out.println("next page or previous?\nIf you want to go to the previous page:\n\tprevious page");
                            }
                        }
                    }
                }
            }
            else System.out.println("invalid input! Returned to profile menu successfully!");
        }
    }
}
