package Model;

import Controller.Root;

public class MonteCarloBot implements Player{
    Root root;
    boolean timebased;

    public MonteCarloBot(Root root) {
        this.root = root;

    }

    //This is just so that the bot implements Player... Should this return coordinates?
    @Override
    public void makeMove(State currentState) {


    }

    //plays the specified amount of games given by times and returns the winrate
    //colour is the colour playing next
    public float playRandom(int[][] board, int colour, int times) {

    }
}
