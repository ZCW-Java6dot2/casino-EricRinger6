package io.zipcoder.casino.cardstuff;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void createFullDeck() {
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck(1);

        int expected = 52;
        int actual = playingDeck.getNumCards();

        assertEquals(expected, actual);
    }

    @Test
    public void shuffleDeck() {
        Deck d = new Deck();
        d.createFullDeck(1);
        Deck d1 = new Deck();
        d1.createFullDeck(1);
        Deck d2 = new Deck();
        d2.createFullDeck(1);
        d2.shuffleDeck();

        assertEquals(d.toString(), d1.toString());
        assertNotEquals(d, d2);

    }

    @Test
    public void removeCard() {
        Deck d = new Deck();
        d.createFullDeck(1);
        d.removeCard(0);

        int expected = 51;
        int actual = d.getNumCards();

        assertEquals(expected, actual);
    }

    @Test
    public void getCard() {
        Deck d = new Deck();
        Card newCard = new Card(Suit.SPADE, Value.TWO);
        d.addCard(newCard);

        Card expected = newCard;
        Card actual = d.getCard(0);

        assertEquals(expected, actual);
    }

    @Test
    public void addCard() {
        Deck d = new Deck();
        Card newCard = new Card(Suit.SPADE, Value.TWO);
        d.addCard(newCard);

        Card expected = newCard;
        Card actual = d.getCard(0);

        assertEquals(expected, actual);
    }

    @Test
    public void drawCard() {
        Deck d = new Deck();
        d.createFullDeck(1);
        Deck d1 = new Deck();
        d1.drawCard(d);

        int expected = 1;
        int actual = d1.getNumCards();

        assertEquals(expected, actual);
    }

    @Test
    public void handValue() {
        Deck d = new Deck();
        Card c = new Card(Suit.SPADE, Value.TWO);
        Card c1 = new Card(Suit.SPADE, Value.KING);
        d.addCard(c);
        d.addCard(c1);

        int expected = 12;
        int actual = d.handValue();

        assertEquals(expected, actual);
    }

    @Test
    public void clearHand() {
        Deck d = new Deck();
        Deck discard = new Deck();
        Card c = new Card(Suit.SPADE, Value.TWO);
        Card c1 = new Card(Suit.SPADE, Value.KING);
        d.addCard(c);
        d.addCard(c1);

        int expected = d.getNumCards();
        d.clearHand(discard);
        int actual = discard.getNumCards();

        assertEquals(expected, actual);
    }

    @Test
    public void getNumCards() {
        Deck d = new Deck();
        Deck discard = new Deck();
        Card c = new Card(Suit.SPADE, Value.TWO);
        Card c1 = new Card(Suit.SPADE, Value.KING);
        d.addCard(c);
        d.addCard(c1);

        int expected = d.getNumCards();
        d.clearHand(discard);
        int actual = discard.getNumCards();

        assertEquals(expected, actual);
    }
}