// Einav Bar
// Lior Poterman

package Part2;

public abstract class Player {
    PlayerType player;
    Game game;
    String symbol;

    public Player(PlayerType player, Game game){
        this.player = player;
        this.game = game;
        this.symbol = player == PlayerType.X ? "X" : "O";
    }
}
