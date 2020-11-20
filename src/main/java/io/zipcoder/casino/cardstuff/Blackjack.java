package io.zipcoder.casino.cardstuff;

import io.zipcoder.casino.core.GamblingGame;
import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.Scanner;

public class Blackjack implements GamblingGame {

    //amount of money available to the player for making bets - also where winnings will go
    private double playerMoney;
    //amount of money player is going to bet on the next hand
    private double playerBet;
    //boolean to determine if round is over or not
    private boolean endRound = false;
    //deck from which dealer will draw cards for themselves and the player
    Deck playingDeck = new Deck();
    //deck object representing the player's hand
    Deck playerHand = new Deck();
    //deck object representing the dealer's hand
    Deck dealerHand = new Deck();
    //deck object for cards to be discarded into
    Deck discardPile = new Deck();
    //set up scanner for user input
    Scanner userInput = new Scanner(System.in);
    //a player object must be passed to Blackjack in order to play
    Player player;
    //console object
    Console console;

    public Blackjack(Player player, Console console) {
        this.console = console;
        this.player = player;
        playerMoney = player.getPlayerMoney();
    }

    public void playBlackJack() {
        //create and shuffle playing deck
        playingDeck.createFullDeck(3);
        playingDeck.shuffleDeck();

        gameplayLoop();
    }

    public void gameplayLoop() {
        boolean isPlaying = true;
        while(playerMoney > 0 && isPlaying == true) {

            //make sure that endRound is reset to 'false' at the beginning of each hand
            endRound = false;

            //take in the player's starting bet
            playerBet = getPlayerBet();

            //check to make sure player isn't betting more money than they have
            if(playerBet > playerMoney) {
                System.out.println("You can't bet what you don't have!");
                continue;
            } else if(playerBet <= 0) {
                console.println("You can't bet a negative amount");
                continue;
            }

            //deals two cards each to player and dealer
            dealCards();

            //method with an internal loop that allows the player to draw more cards until they bust or decide to stand
            hitOrStand();

            //once player is finished drawing cards, if the player has not gone bust it is the dealer's turn
            dealersTurn();

            //if a winner has not already been determined compare player and dealer hands to determine a winner
            if(endRound == false) {
                determineWinner();
            }

            //send player and dealer cards to discard pile
            clearHands();

            //if deck size is less than (number of decks in the chute * 10) cards at the end of a round,
            // shuffle discard pile back into playing deck
            if(playingDeck.getNumCards() < 30) {
                discardPile.clearHand(playingDeck);
                playingDeck.shuffleDeck();
            }

            if(playerMoney > 0) {
                int keepPlaying = console.getIntegerInput("Continue? (1)yes (2)no");
                if(keepPlaying == 1) {
                    continue;
                } else if (keepPlaying == 2) {
                    isPlaying = false;
                }
            }
        }
    }

    public void clearHands() {
        playerHand.clearHand(discardPile);
        dealerHand.clearHand(discardPile);
    }

    public boolean dealCards() {
        //player is dealt two cards
        playerHand.drawCard(playingDeck);
        playerHand.drawCard(playingDeck);

        //dealer is dealt two cards
        dealerHand.drawCard(playingDeck);
        dealerHand.drawCard(playingDeck);

        return true;
    }

    public void hitOrStand() {
        while(true) {
            //output of player's hand
            console.println("Your hand:");
            console.println(playerHand.toString());
            console.println("The value of your hand is " + playerHand.handValue());

            //output of dealer's hand
            console.println("Dealer's hand:");
            console.println(dealerHand.getCard(0).toString() + "[HIDDEN]");
            //System.out.println("The value of the known dealer's card is " + dealerHand.getCard(0).handValue());

            int response = console.getIntegerInput("Would you like to (1)hit or (2)stand?");
            if(response == 1) {
                //draw another card and tell the player what they drew
                playerHand.drawCard(playingDeck);
                console.println("You draw a: " + playerHand.getCard(playerHand.getNumCards() - 1).toString());
                console.println("The value of your new hand is " + playerHand.handValue());

                //determine if player hand is now worth more than 21 points with additional card
                if(playerHand.handValue() > 21) {
                    console.println("Bust! Better luck next time");
                    playerLoses();
                    endRound = true;
                    break;
                }
            } else if (response == 2) {
                break;
            }
        }
    }

    public String dealersTurn() {
        //reveal dealer cards
        console.println("Dealer's cards: ");
        console.println(dealerHand.toString());

        //check if dealer wins by default
        if(dealerHand.handValue() > playerHand.handValue() && endRound == false) {
            console.println("Dealer wins");
            playerLoses();
            endRound = true;
            return "Dealer wins";
        }

        //dealer draws at 16 and stands at 17
        while((dealerHand.handValue() < 17) && endRound == false) {
            dealerHand.drawCard(playingDeck);
            console.println("Dealer draws: " + dealerHand.getCard(dealerHand.getNumCards() - 1).toString());
        }

        //display total value for dealer
        console.println("Dealer's hand value: " + dealerHand.handValue());

        //determine if dealer busted
        if(dealerHand.handValue() > 21 && endRound == false) {
            console.println("Dealer busts. Congratulations, you win!");
            endRound = true;
            playerWins();
            return "Dealer busts. Congratulations, you win!";
        }
        return "not enough data -- must be a test!";
    }

    public String determineWinner() {
        //determine who won
        if(dealerHand.handValue() > playerHand.handValue() && endRound == false) {
            console.println("Dealer wins");
            playerLoses();
            endRound = true;
            return "Dealer wins";
        }

        //determine if push
        if(playerHand.handValue() == dealerHand.handValue() && endRound == false) {
            console.println("Push. Nobody wins");
            endRound = true;
            return "Push. Nobody wins";
        }

        //determine if player won
        if(playerHand.handValue() > dealerHand.handValue() && endRound == false) {
            console.println("Congratulations, you win!");
            playerWins();
            endRound = true;
            return "Congratulations, you win!";
        }
        return "not enough data -- must be a test run!";
    }

    public boolean playerLoses() {
        player.setPlayerMoney(playerMoney -= playerBet);
        return true;
    }

    public boolean playerWins() {
        player.setPlayerMoney(playerMoney += playerBet);
        return true;
    }

    public double getPlayerBet() {
        console.println("You currently have $" + playerMoney);
        double playerBet = console.getDoubleInput("How much would you like to bet on this hand?");
        return playerBet;
    }

    public double displayPlayerWallet() {
        return player.getPlayerMoney();
    }
}
