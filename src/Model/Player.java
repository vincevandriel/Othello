package Model;

public class Player {

    private Rules rules;
    private int tile;
    private boolean human;
    private SimpleAI bot;
    private boolean playerTurn;
    private Player P1;

    public Player(boolean human, int tile, Player P1) { // if human
        if(P1 == null){
            this.P1 = this;
        }else{
            this.P1 = P1;
        }
        playerTurn = true;
        this.human = human;
        this.tile = tile;
        rules = new Rules();
    }

    public Player(int tile, Player P1){ // if bot
        if(P1 == null){
            this.P1 = this;
        }else{
            this.P1 = P1;
        }
        playerTurn = true;
        human = false;
        this.tile = tile;
        rules = new Rules();
        bot = new SimpleAI();
    }

    public void makeMove(double x, double y, State currentState) {

        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D = rules.checkMoves(board2D, tile);
        if (rules.moveStatus(board2D, (int) x, (int) y, P1) == 1) {
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            board2D[(int) x][(int) y] = tile;
            board2D = rules.flip(board2D, x, y, tile);
            currentState.setCurrentBoard(board2D); //update state after move
        } else {
            if (rules.moveStatus(board2D, (int) x, (int) y, P1) == 0) {
                System.out.println("NO MOVES AVAILABLE FOR YOU - switch turn to opponent.");
            } else {
                System.out.println("Illegal move, try again.");
            }
        }
    }


    public void makeMove(State currentState) {
        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D = rules.checkMoves(board2D, tile);
        board2D = bot.pickMove(board2D, tile, P1);
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

    public void switchTurn(){
        if(playerTurn == true){
            playerTurn = false;
        }else{
            playerTurn = true;
        }
    }

    public boolean turnStatus(){
        return playerTurn;
    }
}

