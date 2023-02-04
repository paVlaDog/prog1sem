package game;

public interface Position {
    Cell getTurn();
    
    int getHeigth();

    int getWeigth();

    boolean isValid(Move move);
}
