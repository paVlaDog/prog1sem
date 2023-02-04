package game;


import java.util.Scanner;

public class Match{
    private final int typeOfBoard;
    private final int height;
    private final int width;
    private final int rowToWin;

    public Match (int typeOfBoard, int height, int width, int rowToWin) {
        this.typeOfBoard = typeOfBoard;
        this.height = height;
        this.width = width;
        this.rowToWin = rowToWin;
    }

    public void play() throws AssertionError{
        Board board;
        if (typeOfBoard == 1) {
            board = new RectBoard(height, width, rowToWin);
        } else {
            board = new RhombusBoard(height * 2 - 1, width, rowToWin);
        }

        new TwoPlayerGame(
                board,
                new HumanPlayer(new Scanner(System.in)),
                new HumanPlayer(new Scanner(System.in)),
                1,
                2
        ).play(false );
    }
}
