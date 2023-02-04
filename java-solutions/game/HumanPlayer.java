package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        return new Move(Integer.parseInt(in.next()) - 1, Integer.parseInt(in.next()) - 1, position.getTurn());
    }
}
