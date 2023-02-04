package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(Position position) throws AssertionError{
        while (true) {
            final Move move = new Move(
                    random.nextInt(position.getHeigth()),
                    random.nextInt(position.getWeigth()),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
