package io.zipcoder.casino.cardstuff;


import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GoFish {

    public Random random = new Random();
    private Integer aiPairs = 0;
    private Integer playerPairs = 0;
    private Deck deck = new Deck();
    private Deck playerHand = new Deck();
    private Deck aiHand = new Deck();
    private Card card;
    Console console = new Console(System.in, System.out);
    private ArrayList<Card> aiGuesses = new ArrayList<Card>();
    private Integer ageOfGuesses = 0;


    public GoFish(){

    }

    public String run(){
        createDeck();
        dealCards();


        console.println("Welcome to Go Fish. ");

        while(playerPairs + aiPairs < 26){

            turnPlayer();
            console.println("\n----------");
            playerPairs += checkPairs(playerHand);

            turnAi();
            console.println("\n----------");
            aiPairs += checkPairs(aiHand);
        }

        return winningMessage();
    }

    public void createDeck() {
        deck.createFullDeck(1);
        deck.shuffleDeck();
    }

    public Deck getDeck() {
        return deck;
    }

    public Deck getAiHand() {//for testing purposes
        return aiHand;
    }

    public Deck getPlayerHand() {//for testing purposes
        return playerHand;
    }

    public void dealCards(){//Deals 5 cards to each hand
        for(int i = 0; i < 5; i++){
            playerHand.drawCard(deck);
            aiHand.drawCard(deck);
        }

    }

    public void turnPlayer(){
        Scanner scanner = new Scanner(System.in);//I think I need scanner for test purposes
        Boolean playing = true;
        do{
            playerPairs += checkPairs(playerHand); //Checks if there are any pairs

            console.println("You have " + getPlayerPairs() + " pairs."); //Prints num of pairs

            if(playerHand.getNumCards() == 0){
                console.println("Looks like your hand is empty, draw five cards.");
                for(int x = 0; x < 5; x++){
                    fish(playerHand);
                }
            }
            System.out.println("Your hand is: ");
            for(int i = 0; i < playerHand.getNumCards(); i++){
                console.print(playerHand.getCard(i) + ", ");
            }

            console.println("\nWhich card would you like to ask for?");

            Card input;
            try{
                input = new Card(Suit.CLUB, Value.valueOf(scanner.next().toUpperCase()));
            }
            catch(IllegalArgumentException e){
                console.println("Card not valid or present, please try another. Try again: ");
                continue;
            }

            if(!checkCardAskedFor(playerHand, input)){
                console.println("You cannot ask for a card you do not have. Try again: ");
                continue;
            }

            console.println("You ask for a " + input.getValue());
            if(checkCards(aiHand, playerHand, input)){
                playerPairs++;
                playing = true;
            } else{
                playing = false;
                aiGuesses.add(input);
            }
            if(deck.getNumCards() == 0 && playerHand.getNumCards() == 0){//Had to disrupt the loop for the test, should not affect gameplay
                playing = false;
            }

        } while(playing);
        console.println("Go fish!");
        fish(playerHand);
    }

    public void turnAi(){//Similar to player turn but doesnt need input validation
        Boolean playing = true;
        do{
            aiPairs += checkPairs(aiHand);

            console.println("Your opponent has " + getAiPairs() + " pairs.");

            if(aiHand.getNumCards() == 0){
                console.println("Looks like your opponent's is empty, drawing five cards.");
                for(int x = 0; x < 5; x++){
                    fish(aiHand);
                }
            }

            Card input = aiThoughts();

            console.println("Your opponent asks for a " + input.getValue());
            if(checkCards(playerHand, aiHand, input)){
                aiPairs++;
                playing = true;
            } else{
                playing = false;
            }
            ageOfGuesses++;
            if(deck.getNumCards() == 0 && playerHand.getNumCards() == 0){//Had to disrupt the loop for the test, should not affect gameplay
                playing = false;
            }
        } while(playing);
        console.println("Go fish!");
        fish(aiHand);
    }

    public Card aiThoughts(){//AI thoughts gives the Ai the capabilities to not be "Stupid", remembers what player asked for and checks its hand accordingly
        if(ageOfGuesses > 2){
            aiGuesses.remove(aiGuesses.size()- 1);
            this.ageOfGuesses = 0;
        }
        for(int i = aiGuesses.size() - 1; i > -1; i--){
            if(checkCardAskedFor(aiHand, aiGuesses.get(i))){
                return aiGuesses.remove(i);
            }
        }
        return aiHand.getCard(random.nextInt(aiHand.getNumCards()));
    }

    public Integer checkPairs(Deck deckT){//checks for pairs and counts how many
        int numOfPairs = 0;
        for(int i = 0; i < deckT.getNumCards() - 1; i++){
                for(int x = i + 1; x < deckT.getNumCards(); x++){
                    if(deckT.getCard(i).getValue() == deckT.getCard(x).getValue()){
                        deckT.removeCard(x);
                        deckT.removeCard(i);
                        numOfPairs++;
                    }
                }
        }
        return numOfPairs;
    }

    public Boolean checkCards(Deck checking, Deck checker, Card input){//checks if the card is in the opponents hand and removes it
        for(int i = 0; i < checking.getNumCards(); i++){
            if(checking.getCard(i).getValue() == input.getValue()){
                for(int x = 0; x < checker.getNumCards(); x++){
                    if(checker.getCard(x).getValue() == input.getValue()){
                        checker.removeCard(x);
                        checking.removeCard(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean checkCardAskedFor(Deck check, Card input){//Checks if the card is actually in the players hand
        for(int i = 0; i < check.getNumCards(); i++){
            if(check.getCard(i).getValue() == input.getValue()){
                return true;
            }
        }
        return false;
    }

    public void addCard(Deck deck, Card card){//Purely for test purposes
        deck.addCard(card);
    }

    public Integer getAiPairs() {
        return aiPairs;
    }

    public Integer getPlayerPairs() {
        return playerPairs;
    }

    public void setAiPairs(Integer aiPairs) {
        this.aiPairs = aiPairs;
    }

    public void setPlayerPairs(Integer playerPairs) {
        this.playerPairs = playerPairs;
    }

    public void fish(Deck temp){
        if(deck.getNumCards() > 0){
            temp.drawCard(deck);
        } else {
            console.println("Looks like the Deck is empty!");
        }
    }

    public String winningMessage(){
        String winner = "";
        if(aiPairs < playerPairs){
            winner = "Player";
            console.println("Congrats, you beat the AI!");
        } else if(aiPairs.equals(playerPairs)){
            winner = "Nobody";
            console.println("Thats too bad, its a tie....");
        } else {
            winner = "AI";
            console.println("Wow you couldn't beat the AI....");
        }

        return winner;
    }

}
