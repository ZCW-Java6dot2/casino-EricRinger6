package io.zipcoder.casino.craps;
import io.zipcoder.casino.core.DiceGame;
import io.zipcoder.casino.core.GamblingGame;
import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;
import java.util.ArrayList;
public class Craps extends DiceGame implements GamblingGame {
    DiceGame diceGame;
    Console console;
    Player player;
    private Integer extraDieVal;
    ArrayList<Integer> dieRolls;
    ArrayList<Integer> dieArr = new ArrayList<Integer>();
    private Boolean firstRoll;
    private Boolean extraRoll;
    Integer hasMoney;
    Double playerMoney;
    private Double betAmt;
    public Craps(Player player, Console console) {
        this.player = player;
        playerMoney = player.getPlayerMoney();
        this.console = console;
        diceGame = new DiceGame();
    }
    public Craps()
    {
    }
    //Game start
    public void gameOn() {
        firstRoll = true;
        playerMoneyStart();
        while(firstRoll) {
            playerHasMoney();
            extraRoll = true;
            rollBet();
            while(extraRoll) {
                extraRollBet();
            }
        }
    }
    //getting the value of a roll and storing in dieArr
    public void getDie() {
        dieRolls = diceGame.tossAndList(2);
        extraDieVal = dieRolls.get(0) + dieRolls.get(1);
        dieArr.add(extraDieVal);
    }
    public void rollBet() {
        getDie();
        if(dieArr.get(0) == 7 || dieArr.get(0) == 11) {
            console.println("You win on the first round with a " + dieArr.get(0).toString());
            youWin();
            extraRoll = false;
            firstRoll = playAgainBoo();
        } else if (dieArr.get(0) == 2 || dieArr.get(0) == 3 || dieArr.get(0) == 12) {
            console.println("You lose on the first roll with a " + dieArr.get(0).toString());
            youLose();
            extraRoll = false;
            firstRoll = playAgainBoo();
        }
    }
    public void extraRollBet() {
        getDie();
        if(dieArr.get(0) == dieArr.get(dieArr.size() - 1)) {
            console.println("Your first roll was a " + dieArr.get(0).toString() + ", you matched that roll to win in a later round.");
            youWin();
            extraRoll = false;
            firstRoll = playAgainBoo();
        } else if (dieArr.get(dieArr.size() - 1) == 7) {
            console.println("Your first roll was a " + dieArr.get(0).toString() + " but you rolled a 7 in a later round.");
            youLose();
            extraRoll = false;
            firstRoll = playAgainBoo();
        }
    }
    public String playAgainStr() {
        String play = console.getStringInput("Would you like to play again?  (Y or N)");
        return play;
    }
    public Boolean playAgainBoo() {
        String playStr = playAgainStr().toUpperCase();
        return !playStr.equals("N");
    }
    public void playerHasMoney() {
        //hasMoney = 0;
        if(playerMoney >= 1) {
            //hasMoney = 1;
            checkPlayerBet();
        } else if (playerMoney < 1) {
            Menu menu = new Menu(console);
            console.println("Come back when you have money!!!");
            menu.selectGame();
        }
    }

    public void playerMoneyStart() {
        if(playerMoney >= 1) {

        } else if (playerMoney < 1) {
            Menu menu = new Menu(console);
            console.println("Come back when you have money!!!");
            menu.selectGame();
        }
    }

    public void checkPlayerBet() {
        betAmt = getPlayerBet();
        if(betAmt > player.getPlayerMoney()) {
            console.println("Need more cash to play!!!");
            firstRoll = false;
        }
    }
    public double setBetAmt(double betAmt){
        this.betAmt = betAmt;
        return betAmt;
    }

    public Double getBetAmt() {
        return betAmt;
    }

    public double getPlayerBet() {
        console.println("You currently have $" + player.getPlayerMoney());
        Double newBet = console.getDoubleInput("How much would you like to bet?");
        return newBet;
    }
    public double displayPlayerWallet() {
        return player.getPlayerMoney();
    }

    public void youWin() {
        player.setPlayerMoney(player.getPlayerMoney() + getBetAmt());
        console.println("Congrats you won $" + getBetAmt());
        dieArr.clear();
    }
    public void youLose() {
        player.setPlayerMoney(player.getPlayerMoney() - getBetAmt());
        console.println(" You Lost $" + getBetAmt());
        dieArr.clear();
    }
}