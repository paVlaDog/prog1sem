package game;

public class RectBoard extends RhombysOrRectBoard {

    public RectBoard(int height, int width, int rowToWin) {
        super(height, width, rowToWin);
    }

    @Override
    public Position newPosition() {
        return new ProtectedPosition(this);
    }

    @Override
    public boolean additionalCheckWin(int row, int col, Cell val) {
        int count = 1;
        for (int i = 1; (row + i < heigth && col - i >= 0
                && field[row + i][col - i] == val); i++) {
                count++;
        }
        for (int i = 1; (row - i >= 0 && col + i < weight
                && field[row - i][col + i] == val); i++) {
                count++;
        }
        if (count >= rowToWin)return true;
        return false;
    }

    @Override
    public String outputField() {
        String ws = new String("            ".substring(0, (int)Math.ceil(Math.log10(getWeigth() + 1)) + 1));
        final StringBuilder sb = new StringBuilder("           ".substring(0, (int)Math.ceil(Math.log10(getWeigth() + 1)) + 1));
        for (int r = 1; r < getWeigth() + 1; r++){
            sb.append(r);
            sb.append(ws.substring(0, ws.length() - (int)Math.ceil(Math.log10(r + 1))));
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < getHeigth(); r++) {
            sb.append(r + 1);
            sb.append(ws.substring(0, ws.length() - (int)Math.ceil(Math.log10(r + 2))));
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell));
                sb.append(ws.substring(0, ws.length() - 1));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
