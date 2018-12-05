package Model;

import java.util.ArrayList;
import java.util.Random;

public class MonteCarloBot implements Player{
    public static Root root;
    Rules rules;
    boolean timebased;
    int times;
    int tile;
    private Random random;

    public MonteCarloBot(Root root) {
        this.root = root;
        tile = root.getTile();
        random = new Random();
    }

    //This is just so that the bot implements Player... Should this return coordinates?
    @Override
    public void makeMove(State currentState) {

    }

    //plays the specified amount of games given by times and returns the winrate
    //colour is the colour playing next
    public float playRandom(int[][] board, int colour, int times) {
        int wins;
        int losses;
        int currentColour = colour;
        int[][] currentBoard = rules.checkMoves(board, currentColour);
        for(int i = 0; i < times; i++) {
            boolean finished = false;
            while(!finished) {

            }
        }
        return wins/losses;
    }

    public int[][] playRandom(int[][] board, int colour) {
        int[][] tempBoard = rules.checkMoves(board, colour);
        ArrayList<int[]> availMoves = new ArrayList<int[]>();

        for(int i = 0; i < tempBoard.length; i++) {
            for(int j = 0; j < tempBoard[0].length; j++) {
                if(tempBoard[i][j] == 3) {
                    int[] move = new int[2];
                    move[0] = i;
                    move[1] = j;
                    availMoves.add(move);
                }
            }
        }

        if(availMoves.size() == 0) {
            return null;
        } else {
            random.nextInt();
        }
    }

    public int swapcoulours(int tile) {
        if (tile == 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
