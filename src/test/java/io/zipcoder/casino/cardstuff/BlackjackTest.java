package io.zipcoder.casino.cardstuff;

import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.utilities.Console;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlackjackTest {

    @Test
    public void playBlackJackTest() {
    }

    @Test
    public void gameplayLoopTest() {
    }

    @Test
    public void clearHandsTest() {
    }

    @Test
    public void dealCardsTest() {
//        Console console = new Console(System.in, System.out);
//        Player player = new Player(100, "peter");
//        Blackjack blackjack = new Blackjack(player, console);
//        Deck playingDeck = new Deck();
//        playingDeck.createFullDeck(1);
//        Deck playerHand = new Deck();
//        Deck dealerHand = new Deck();
//
//        boolean expected = true;
//        boolean actual = blackjack.dealCards();
//
//        assertEquals(expected, actual);
    }

    @Test
    public void hitOrStandTest() {
    }

    @Test
    public void dealersTurnTest() {
        Console console = new Console(System.in, System.out);
        Player player = new Player(100, "peter");
        Blackjack blackjack = new Blackjack(player, console);
        Card playerCard1 = new Card(Suit.SPADE, Value.TWO);
        Card playerCard2 = new Card(Suit.HEART, Value.TWO);
        Card dealerCard1 = new Card(Suit.DIAMOND, Value.KING);
        Card dealerCard2 = new Card(Suit.CLUB, Value.ACE);

        blackjack.dealerHand.addCard(dealerCard1);
        blackjack.dealerHand.addCard(dealerCard2);

        String expected = "Dealer wins";
        String actual = blackjack.dealersTurn();

        assertEquals(expected, actual);
    }

    @Test
    public void determineWinnerTest() {
        Console console = new Console(System.in, System.out);
        Player player = new Player(100, "peter");
        Blackjack blackjack = new Blackjack(player, console);

        String expected = "Push. Nobody wins";
        String actual = blackjack.determineWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void playerLosesTest() {
        Console console = new Console(System.in, System.out);
        Player player = new Player(100, "peter");
        Blackjack blackjack = new Blackjack(player, console);

        boolean actual = blackjack.playerLoses();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void playerWinsTest() {
        Console console = new Console(System.in, System.out);
        Player player = new Player(100, "peter");
        Blackjack blackjack = new Blackjack(player, console);

        boolean actual = blackjack.playerWins();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void getPlayerBetTest() {
//        Console console = new Console(System.in, System.out);
//        Player player = new Player(100, "peter");
//        Blackjack blackjack = new Blackjack(player, console);
//
//        double expected = 20;
//        double actual = blackjack.getPlayerBet();
//
//        assertEquals(expected, actual, .001);
    }

    @Test
    public void displayPlayerWalletTest() {
        Console console = new Console(System.in, System.out);
        Player player = new Player(100, "peter");
        Blackjack blackjack = new Blackjack(player, console);

        double expected = 100;
        double actual = blackjack.displayPlayerWallet();

        assertEquals(expected, actual, .0001);
    }
}