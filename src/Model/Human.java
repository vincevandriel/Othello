package Model;

public class Human implements Player {

    private Rules rules;
    private int tile;

    public Human() { // if human
        rules = new Rules();
    }

    @Override
    public void makeMove(State currentState) {

        this.tile = currentState.getTile();

        int x = currentState.getX();
        int y = currentState.getY();

        int[][] board2D = currentState.getCurrentBoard();
        board2D = rules.checkMoves(board2D, tile);
        if (rules.moveStatus(board2D, x, y) == 1) {
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            board2D[x][y] = tile;
            board2D = rules.flip(board2D, x, y, tile);
            currentState.setCurrentBoard(board2D); //update state after move
            currentState.switchTile();
        } else {
            if (rules.moveStatus(board2D, x, y) == 0) {
                System.out.println("NO MOVES AVAILABLE FOR YOU - switch turn to opponent.");
                currentState.switchTile();
            } else {
                System.out.println("Illegal move, try again.");
            }
        }
    }
}

