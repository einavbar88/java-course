// Einav Bar
// Lior Poterman

package Part2;

public class Main {
    public static void main(String[] args) {

        SelfGame selfGame = new SelfGame();

        SelfPlayer player1 = new SelfPlayer(PlayerType.X, selfGame);
        SelfPlayer player2 = new SelfPlayer(PlayerType.O, selfGame);

        Thread p1 = new Thread(player1);
        Thread p2 = new Thread(player2);

        selfGame.setPlayer1(p1);
        selfGame.setPlayer2(p2);

        selfGame.start();

    }
}

class SelfGame extends Game {
    private Thread player1;
    private Thread player2;
    public SelfGame(){
        super();
    }

    public void setPlayer1(Thread player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Thread player2) {
        this.player2 = player2;
    }

    @Override
    public void run() {
        this.printBoard();
        this.player1.start();
        this.player2.start();

    }
}