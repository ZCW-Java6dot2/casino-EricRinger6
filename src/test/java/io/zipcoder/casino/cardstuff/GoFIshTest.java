package io.zipcoder.casino.cardstuff;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.zipcoder.casino.cardstuff.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class GoFIshTest {

    @Test
    public void testAiTurn(){
        GoFish goFish = new GoFish();

        Card card1 = new Card(Suit.DIAMOND, Value.QUEEN);
        Card card2 = new Card(Suit.CLUB, Value.QUEEN);

        goFish.addCard(goFish.getAiHand(), card1);
        goFish.addCard(goFish.getPlayerHand(), card2);

        goFish.turnAi();

        Integer actual = 1;
        Integer expected = goFish.getAiPairs();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPlayerTurn(){
        GoFish goFish = new GoFish();

        Card card1 = new Card(Suit.DIAMOND, Value.QUEEN);
        Card card2 = new Card(Suit.CLUB, Value.QUEEN);

        goFish.addCard(goFish.getAiHand(), card1);
        goFish.addCard(goFish.getPlayerHand(), card2);



        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("queen".getBytes());
        System.setIn(in);

        goFish.turnPlayer();

        Integer actual = 1;
        Integer expected = goFish.getPlayerPairs();

        System.setIn(sysInBackup);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testRun(){
        GoFish goFish = new GoFish();
        goFish.setPlayerPairs(13);
        goFish.setAiPairs(13);

        String actual = "Nobody";
        String expected = goFish.run();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeck(){
        GoFish goFish = new GoFish();
        goFish.createDeck();

        Integer actual = 52;
        Integer expected = goFish.getDeck().getNumCards();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDeal(){
        GoFish goFish = new GoFish();
        goFish.createDeck();
        goFish.dealCards();

        Integer actual = 5;
        Integer expected1 = goFish.getAiHand().getNumCards();
        Integer expected2 = goFish.getPlayerHand().getNumCards();

        Assert.assertEquals(actual, expected1);
        Assert.assertEquals(actual, expected2);
    }

    @Test
    public void testFish(){
        GoFish goFish = new GoFish();
        Deck deck = new Deck();
        goFish.createDeck();

        Integer actual = deck.getNumCards() + 1;
        goFish.fish(deck);
        Integer expected = deck.getNumCards();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWinner(){
        GoFish goFish = new GoFish();

        goFish.setAiPairs(10);
        goFish.setPlayerPairs(4);

        String actual = "AI";
        String expected = goFish.winningMessage();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCheckPairs(){
        GoFish goFish = new GoFish();
        Deck deck = new Deck();
        Card card1 = new Card(Suit.CLUB, Value.ACE);
        Card card2 = new Card(Suit.HEART, Value.ACE);
        goFish.addCard(deck, card1);
        goFish.addCard(deck, card2);

        Integer actual = 1;
        Integer expected = goFish.checkPairs(deck);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCheckCards(){
        GoFish goFish = new GoFish();
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        Card card1 = new Card(Suit.CLUB, Value.ACE);
        Card card2 = new Card(Suit.DIAMOND, Value.ACE);
        goFish.addCard(deck1, card1);
        goFish.addCard(deck2, card2);

        Boolean actual = true;
        Boolean expected = goFish.checkCards(deck1, deck2, card1);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCheckCardAsking(){
        GoFish goFish = new GoFish();
        Deck deck = new Deck();
        Card card1 = new Card(Suit.CLUB, Value.ACE);
        Card card2 = new Card(Suit.DIAMOND, Value.ACE);

        goFish.addCard(deck, card1);
        goFish.addCard(deck, card2);

        Boolean actual = true;
        Boolean expected = goFish.checkCardAskedFor(deck, card1);

        Assert.assertEquals(expected, actual);
    }
}
