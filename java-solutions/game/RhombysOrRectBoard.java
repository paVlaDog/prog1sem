package game;

import java.util.Arrays;
import java.util.Map;

public abstract class RhombysOrRectBoard implements Board, Position{
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    protected final Cell[][] field;
    protected Cell turn;
    protected final int heigth;
    protected final int weight;
    protected final int rowToWin;
    protected int numOfTurn;
    protected final Position position;

    public RhombysOrRectBoard (int height, int width, int rowToWin) {
        this.heigth = height;
        this.weight = width;
        this.rowToWin = rowToWin;
        this.numOfTurn = 0;
        field = new Cell[height][width];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.position = newPosition();
    }

    public abstract Position newPosition();

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public int getHeigth() {
        return heigth;
    }

    @Override
    public int getWeigth() {
        return weight;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public GameResult makeMove(Move move) {
        numOfTurn++;
        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move.getRow(), move.getCol(), move.getValue())) {
            return GameResult.WIN;
        }
        if (numOfTurn == weight * heigth) {
            return GameResult.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    public boolean checkWin(int row, int col, Cell val) {
        for (int phase = 1; phase < 4; phase++) {
            int vert = phase % 2;
            int hor = phase / 2;
            int count = 1;
            for (int i = 1; (row + vert * i < heigth && col + hor * i < weight
                    && field[row + vert * i][col + hor * i] == val); i++) {
                count++;
            }
            for (int i = 1; (row - vert * i >= 0 && col - hor * i >= 0
                    && field[row - vert * i][col - hor * i] == val); i++) {
                count++;
            }
            if (count >= rowToWin) return true;
        }
        return additionalCheckWin(row, col, val);
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < getHeigth()
                && 0 <= move.getCol() && move.getCol() < getWeigth()
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    public abstract boolean additionalCheckWin(int row, int col, Cell val);
}
