package io.zipcoder.casino.ceelo;

public class CeeloMenu{

    public CeeloMenu(){
    }

    public String welcome(){
        return "Welcome to Ceelo!\n"+
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
    }

    public String promptRoll(){
        return "Press 1 to roll the dice!";
    }

    public String youWon(){
        return "YOU WIN!";
    }

    public String youLose(){
        return "You lose!";
    }

    public String pointPrompt(){
        return "You got a Point! computer challenges you to a duel!";
    }

    public String triplePrompt(){
        return "You got a Triple! computer challenges you to a duel!";
    }

    public String gotNothing(){
        return "Nothing exciting here, roll again!";
    }

    public String compTriple(){
        return "Computer got a Triple!";
    }

    public String compPoint(){
        return "Computer got a Point!";
    }

    public String shootOut(){
        return "IT'S A TIE! WE GOT A SHOOTOUT! ROLL AGAIN!";
    }

    public String playAgain(){
        return "press 1 to play again!\n" +
                "press 2 to exit the game";
    }
}
