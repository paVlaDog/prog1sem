package game;

public interface Board {

    Position getPosition();

    GameResult makeMove(Move move);

    boolean checkWin(int row, int col, Cell val);

    String outputField();
}
