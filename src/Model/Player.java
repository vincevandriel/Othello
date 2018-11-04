package Model;

public class Player {

    private Rules rules;
    private int tile;
    private boolean human;
    private SimpleAI bot;

    public Player(boolean human, int tile) { // if human
        this.human = human;
        this.tile = tile;
        rules = new Rules();
    }

    public Player(int tile){ // if bot
        human = false;
        this.tile = tile;
        rules = new Rules();
        bot = new SimpleAI();
    }

    public void makeMove(double x, double y, State currentState) {

        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D = rules.checkMoves(board2D, tile);
        if (rules.moveStatus(board2D, (int) x, (int) y) == 1) {
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            board2D[(int) x][(int) y] = tile;
            board2D = rules.flip(board2D, x, y, tile);
            currentState.setCurrentBoard(board2D); //update state after move
        } else {
            if (rules.moveStatus(board2D, (int) x, (int) y) == 0) {
                System.out.println("NO MOVES AVAILABLE FOR YOU - switch turn to opponent.");
            } else {
                System.out.println("Illegal move, try again.");
            }
        }
    }


    public void makeMove(State currentState) {
        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D = rules.checkMoves(board2D, tile);
        board2D = bot.pickMove(board2D, tile);
        if (board2D == null) {
            System.out.println("NO MOVES AVAILABLE FOR AI - switch turn to opponent.");
        } else {
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            board2D = rules.flip(board2D, bot.getX(), bot.getY(), tile);
            currentState.setCurrentBoard(board2D); //update state after move
        }
    }

    public boolean isBot(){
        if (human == false){
            return true;
        }
        return false;
    }
}

