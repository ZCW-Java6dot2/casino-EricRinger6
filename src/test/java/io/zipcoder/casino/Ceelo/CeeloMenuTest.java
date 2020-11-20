package io.zipcoder.casino.Ceelo;

import io.zipcoder.casino.ceelo.CeeloMenu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CeeloMenuTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void welcome() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "Welcome to Ceelo!\n"+
                "Rules:\n"+
                "4-5-6\n" +
                "The highest possible roll. If you roll 4-5-6, you AUTOMATICALLY win.\n" +
                "Triple\n" +
                "Rolling three same numbers is known as rolling a trip. Higher trips beat lower trips, so 4-4-4 is better than 3-3-3. Any trips beats any established point.\n" +
                "Point\n" +
                "Rolling a pair, and another number, establishes the singleton as a \"point\". A higher point beats a lower point, so 2-2-6 is better than 5-5-2.\n" +
                "1-2-3\n" +
                "The lowest possible roll. If you roll 1-2-3, you AUTOMATICALLY lose.\n"+
                "Tie\n"+
                "SHOOTOUT WITH COMPUTER!!!";
        String actual = menu.welcome();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void promptRoll() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "Press 1 to roll the dice!";
        String actual = menu.promptRoll();
        Assert.assertEquals(expected, actual);


    }

    @Test
    public void youWon() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "YOU WIN!";
        String actual = menu.youWon();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void youLose() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "You lose!";
        String actual = menu.youLose();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void pointPrompt() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "You got a Point! computer challenges you to a duel!";
        String actual = menu.pointPrompt();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void triplePrompt() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "You got a Triple! computer challenges you to a duel!";
        String actual = menu.triplePrompt();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void gotNothing() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "Nothing exciting here, roll again!";
        String actual = menu.gotNothing();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compTriple() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "Computer got a Triple!";
        String actual = menu.compTriple();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compPoint() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "Computer got a Point!";
        String actual = menu.compPoint();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shootOut() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "IT'S A TIE! WE GOT A SHOOTOUT! ROLL AGAIN!";
        String actual = menu.shootOut();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void playAgain() {
        CeeloMenu menu = new CeeloMenu();
        String expected = "press 1 to play again!\n" +
                "press 2 to exit the game";
        String actual = menu.playAgain();
        Assert.assertEquals(expected, actual);
    }
}
