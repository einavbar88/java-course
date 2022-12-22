package Part2;

public class SelfPlayer extends Player implements Runnable {

    public SelfPlayer(PlayerType player, Game game) {
        super(player, game);
    }

    private void setTurn(){
        if(this.player == PlayerType.X){
            game.setTurn(PlayerType.O);
        }else{
            game.setTurn(PlayerType.X);
        }
    }
    @Override
    public void run() {
        synchronized (this) {
            boolean isGameOn = !game.getIsGameOver();
            while (isGameOn) {
                try {
                    Thread.sleep(2000);
                    if (game.getTurn() == this.player) {
                        int[][] freeCells = game.getFreeCells();
                        int random = (int) Math.ceil(Math.random() * freeCells.length) - 1;
                        int[] randomCell = freeCells[random];
                        game.playTurn(this.symbol, randomCell);
                        if(freeCells.length == 0){
                            System.out.println("Board is full");
                            game.finishGame(true);
                        };
                        game.printBoard();
                        this.setTurn();
                    }
                    isGameOn = !game.getIsGameOver();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
