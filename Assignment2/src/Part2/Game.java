// Einav Bar
// Lior Poterman

package Part2;

import java.util.ArrayList;

public abstract class Game extends Thread {
    private String[][] gameBoard;
    private PlayerType turn;
    private boolean isGameOver;

    public Game(){
        setGameBoard();
        setTurn(PlayerType.X);
        setIsGameOver(false);
    }

    private void setIsGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    private void setGameBoard(){
        gameBoard = new String[3][3];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = "E";
            }
        }
    }

    public void setTurn(PlayerType turn) {
        this.turn = turn;
    }
    public PlayerType getTurn(){
        return turn;
    }
    public void printBoard(){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
                if(j != gameBoard[i].length - 1){
                    System.out.print(" | ");
                }
            }
            if(i != gameBoard.length - 1){
                System.out.println("\n----------");
            }
        }
        System.out.println("\n");
    };
    public int[][] getFreeCells() throws InterruptedException {
        try {
            wait();
            ArrayList arrList = new ArrayList<int[]>();
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if(gameBoard[i][j] == "E"){
                        int[] cell = {i , j};
                        arrList.add(cell);
                    }
                }
            }
            int[][] ret = new int[arrList.size()][2];
            Object[] arr = arrList.toArray();
            for (int i = 0; i < arr.length; i++) {
                ret[i] = (int[])arr[i];
            }
            notify();
            return ret;
        }catch (Exception e){
            throw e;
        }
    }
    public void playTurn(String symbol, int[] cell){
        gameBoard[cell[0]][cell[1]] = symbol;
    }

    public void finishGame(boolean isTie){
        setIsGameOver(true);
        System.out.println("The game ended in a tie!");
    }
    public void finishGame(Player player){
        setIsGameOver(true);
        System.out.println("The winner is " + player.symbol + "!!");
    }

    private PlayerType checkWhoWon(){
        boolean flagDiagonal1 = true;
        boolean flagDiagonal2 = true;
        String diagonal1 = gameBoard[0][0];
        String diagonal2 = gameBoard[0][2];
        String horizontal = gameBoard[0][0];
        String vertical = gameBoard[0][0];;
        boolean flagVertical = true;
        for (int i = 0; i < gameBoard.length; i++) {
            boolean flagHorizontal = true;
            for (int j = 0; j < gameBoard[i].length; j++) {
                horizontal = gameBoard[0][j];
                if(horizontal != gameBoard[i][j]){
                    flagHorizontal = false;
                }
                if(i == j && diagonal1 != gameBoard[i][j]){
                    flagDiagonal1 = false;
                }
                if(i + j == 2 && diagonal2 != gameBoard[i][j]){
                    flagDiagonal2 = false;
                }
                if(i == 0){
                    vertical = gameBoard[0][j];
                    for (int k = 0; k < gameBoard[i].length; k++) {
                        if(gameBoard[k][j] != vertical){
                            flagVertical = false;
                        }
                    }
                }
            }
            if(flagHorizontal){
                return horizontal == "X" ? PlayerType.X : PlayerType.O;
            }
            if(flagVertical){
                return vertical == "X" ? PlayerType.X : PlayerType.O;
            }
        }

        if(flagDiagonal1){
            return diagonal1 == "X" ? PlayerType.X : PlayerType.O;
        }
        if(flagDiagonal2){
            return diagonal2 == "X" ? PlayerType.X : PlayerType.O;
        }
        return PlayerType.NONE;
    }
}

enum PlayerType{
    X,
    O,
    NONE
}
