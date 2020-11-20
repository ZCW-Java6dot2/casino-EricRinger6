package io.zipcoder.casino.utilities;

import io.zipcoder.casino.cardstuff.Blackjack;
import io.zipcoder.casino.cardstuff.GoFish;
import io.zipcoder.casino.ceelo.CeeloEngine;
import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.craps.Craps;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {

    Console console;
    Blackjack blackjack;
    CeeloEngine ceelo;
    GoFish goFish;

    public Menu(Console console) {
        this.console = console;
    }

    public void mainMenuLoop() {
        welcomeMessage();
        //createPlayer();
        selectGame();
    }

    public void welcomeMessage() {
        //System.out.println("Welcome to C2's casino");
        console.println("Welcome to C2's Casino");
    }

    /**
     * method for walking user through creating a new Player object for themselves
     * @return returns a new player object
     */
    public Player createPlayer() {
        Integer testInput = console.getIntegerInput("Are you gambling today? (1)yes (2)no");
        //console.println(String.valueOf(testInput));
        switch (testInput) {
            case 1:
                /**
                 * if they want to gamble create gambling player
                 */
                double startingWallet = console.getDoubleInput("How much would you like to gamble?");
                String gamblingPlayerName = console.getStringInput("What is your name?");
                Player gamblingPlayer = new Player(startingWallet, gamblingPlayerName);
                return gamblingPlayer;
            case 2:
                /**
                 * If they want to create a non-gambling player
                 */
                String nonGamblingPlayerName = console.getStringInput("What is your name?");
                Player nonGamblingPlayer = new Player(nonGamblingPlayerName);
                return nonGamblingPlayer;
            default:
                console.println("not a valid input");
                return null;
        }
    }

    /**
     * Method displaying menu from which user can choose what game they want to play
     * After user has chosen that game is executed
     */
    public void selectGame() {
        //calls the createPlayer() method internally to create a player that can be passed to various games
        Player newPlayer = createPlayer();
        //boolean to determine whether or not user wants to continue playing
        boolean isPlaying = true;
        while(isPlaying) {
            Integer gameMenu = console.getIntegerInput("Which game would you like to play?\n" +
                    "1: BlackJack\n2: Craps\n3: Go Fish\n4: Cee-Lo\n5: Quit playing");
            switch (gameMenu) {
                case 1:
                    blackjack = new Blackjack(newPlayer, console);
                    blackjack.playBlackJack();
                    console.println("After playing you now have $" + blackjack.displayPlayerWallet());
                    break;
                case 2:
                    Craps craps = new Craps(newPlayer, console);
                    craps.gameOn();
                    break;
                case 3:
                    goFish = new GoFish();
                    goFish.run();
                    break;
                case 4:
                    ceelo = new CeeloEngine();
                    ceelo.userPressOne();
                    break;
                default:
                    String quitConfirm = console.getStringInput("Are you sure you want to quit? y/n");
                    quitConfirm.toLowerCase();
                    if(quitConfirm.equals("y")) {
                        isPlaying = false;
                    }
                    console.println("Thanks for playing!");
                    break;
            }
        }
    }


}
