package io.zipcoder.casino.craps;

import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.utilities.Console;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CrapsTest {

    @Test
    public void gameOn() {
    }

    @Test
    public void getDie() {
    }

    @Test
    public void rollBet() {
    }

    @Test
    public void extraRollBet() {
    }

    @Test
    public void playAgainStr() {
    }

    @Test
    public void playAgainBoo() {
        Craps newGame = new Craps();
        Console console = new Console(System.in, System.out);
        Player player = new Player(100.00,"Player1");
        String expectedOutput = newGame.playAgainStr().toUpperCase();
        Boolean playerAnswer = expectedOutput.equals("N");

        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("N".getBytes());
        System.setIn(in);

        Boolean outcome = newGame.playAgainBoo();

        System.setIn(sysInBackup);
        Assert.assertTrue(outcome);
    }

//    public String playAgainStr() {
//        String play = console.getStringInput("Would you like to play again?  (Y or N)");
//        return play;
//    }
//    public Boolean playAgainBoo() {
//        String playStr = playAgainStr().toUpperCase();
//        return !playStr.equals("N");

    @Test
    public void playerHasMoney() {
    }

    @Test
    public void checkPlayerBet() {
    }

    @Test
    public void setBetAmt() {
        Craps craps = new Craps();
        double bet = 100.00;

        double expectedBet = craps.setBetAmt(bet);
        double actualBet = craps.getBetAmt();

        Assert.assertEquals(expectedBet, actualBet, .001);
        }


    @Test
    public void getPlayerBet() {
    }

    @Test
    public void displayPlayerWallet() {
    }

    @Test
    public void youWin() {
    }

    @Test
    public void youLose() {
    }

    @Test
    public void playerMoneyStart(){

    }
}