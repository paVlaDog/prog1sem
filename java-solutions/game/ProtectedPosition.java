package game;

public class ProtectedPosition implements Position{
    private final Position position;

    public ProtectedPosition (Position position) {
        this.position = position;
    }

    @Override
    public Cell getTurn() {
        return position.getTurn();
    }

    @Override
    public int getHeigth() {
        return position.getHeigth();
    }

    @Override
    public int getWeigth() {
        return position.getWeigth();
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

}
