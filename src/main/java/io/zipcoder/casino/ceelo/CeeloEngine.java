package io.zipcoder.casino.ceelo;

import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

import java.util.ArrayList;
import java.util.Collections;

public class CeeloEngine {

    private CeeloGame game;
    private CeeloMenu menu;
    private Menu mainMenu;
    private ArrayList<Integer> diceTossedPlayer;
    private ArrayList<Integer> diceTossedComp;
    private Integer diceNum = 3;
    private Console console;
    private Integer[] compare;
    private Integer comboType;
    private Integer whoseTurn;

    public CeeloEngine(){
        game = new CeeloGame(diceNum);
        menu = new CeeloMenu();
        console = new Console(System.in, System.out);
        mainMenu = new Menu(console);
        diceTossedPlayer = new ArrayList<Integer>();
        diceTossedComp = new ArrayList<Integer>();
        compare = new Integer[2];
        comboType = 2;
        whoseTurn = 0;
;    }

    public void userPressOne() {
        boolean isPlaying = true;
        while (isPlaying) {
            promptRoll();
            whoseTurn = 0;
            diceTossedPlayer = Toss();
            if(comboType == 0){ //checks if player toss is auto win or lose
                console.println(menu.youWon());
                break;
            }
            if(comboType == 1){
                console.println(menu.youLose());
                break;
            }
            else{
                whoseTurn = 1;
                diceTossedComp = Toss();
            }
            console.println(determineWinner(comboType));
            if(compareToss(compare, diceTossedPlayer,diceTossedComp) != 2) {
                isPlaying = tryAgain();
            }
        }
    }

    public boolean tryAgain(){
        boolean playAgain = true;
        boolean loop = true;
        while (loop) {
            Integer userInput = console.getIntegerInput(menu.playAgain());
            if (userInput == 1) {
                loop = false;
            }
            else if(userInput == 2){
                loop = false;
                playAgain = false;
            }
        }
        return playAgain;
    }

    public String determineWinner(Integer comboType){
        String result = "";
        if(comboType == 0){
            result = menu.youLose();
        }
        else if (comboType == 1){
            result = menu.youWon();
        }
        else if(compareToss(compare,diceTossedPlayer,diceTossedComp) == 0){
            result = menu.youWon();
        }
        else if(compareToss(compare,diceTossedPlayer,diceTossedComp) == 1){
            result = menu.youLose();
        }
        else{
            whoseTurn = 0; //sets turn back to player because there is a shootout!
            result = menu.shootOut();
        }
        delay();
        return result;
    }

    public void promptRoll() {
        boolean loop = true;
        while (loop) {
            Integer userInput = console.getIntegerInput(menu.promptRoll());
            if (userInput == 1) {
                loop = false;
            }
        }
    }

    public ArrayList<Integer> Toss(){
        //Integer[]x = {2,2,2};
        //ArrayList<Integer> y = new ArrayList<Integer>(Arrays.asList(x));
        ArrayList<Integer> result = new ArrayList<Integer>();
        boolean loop = true;
        while(loop){
            result = game.tossAndList(diceNum);   //sub for debug, game.tossAndList(diceNum)
            if (whoseTurn == 1) {
                delay();
            }
            console.println(result.toString());
            if (whoseTurn == 1) {
                delay();
            }
            if(checkResult(result, whoseTurn)){
                if (whoseTurn == 0) {
                    Integer userInput = console.getIntegerInput(menu.gotNothing());
                }
            }
            else{
                loop = false;
            }
         }
        return result;
    }

    public boolean checkResult(ArrayList<Integer> result,Integer whoseTurn) {
    boolean loop = true;
        if (game.checkAutoWin(result)) {
            //console.println(menu.youWon());
            comboType = 0;
            loop = false;
        }
        else if (game.checkAutoLose(result)) {
            //console.println(menu.youLose());
            comboType = 1;
            loop = false;
        }
        else if (game.checkPoint(result)) {
            if (whoseTurn == 0) {
                console.println(menu.pointPrompt());
                compare[0] = 1;
            } else {
                console.println(menu.compPoint());
                compare[1] = 1;
            }
            loop = false;
        }
        else if (game.checkTriple(result)) {
                if(whoseTurn == 0) {
                    console.println(menu.triplePrompt());
                    compare[0] = 2;
                }
                else{
                    console.println(menu.compTriple());
                    compare[1] = 2;
                }
            loop = false;
        }
        return loop;
    }

    public Integer compareToss(Integer[] compare, ArrayList<Integer>player1, ArrayList<Integer>player2) {
        Integer winner = 2;
        if(compare[0] > compare[1]){
            winner = 0;
        }
        else if (compare[0] < compare[1]){
            winner = 1;
        }
        else {
            if(compare[0] == 2){
                winner = compareTriples(player1, player2);
            }
            else{
                winner =comparePoints(player1, player2);
            }
        }
        return winner;
    }

    public Integer comparePoints(ArrayList<Integer> player1, ArrayList<Integer> player2) {
        Integer winner = 2;
        Integer playerSingleton = 0;
        Integer compSingleton = 0;
        for(int i = 0; i<player1.size();i++){
            if(Collections.frequency(player1, player1.get(i))==1){
                playerSingleton = player1.get(i);
            }
            if(Collections.frequency(player2,player2.get(i))==1){
                compSingleton = player2.get(i);
            }
        }
        if(playerSingleton>compSingleton){
            winner = 0;
        }
        else if(playerSingleton<compSingleton){
            winner = 1;
        }
        return winner;
    }

    public Integer compareTriples(ArrayList<Integer> player1, ArrayList<Integer> player2) {
        Integer winner = 2;
        if(player1.get(0)>player2.get(0)){
            winner = 0;
        }
        else if (player1.get(0)<player2.get(0)){
            winner = 1;
        }
        return winner;
    }

    public void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
