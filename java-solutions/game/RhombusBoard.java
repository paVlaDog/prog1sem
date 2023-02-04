package game;

public class RhombusBoard extends RhombysOrRectBoard {
    public RhombusBoard(int heigth, int weigth, int rowToWin) {
        super(heigth, weigth, rowToWin);
    }

    @Override
    public Position newPosition() {
        return new ProtectedPosition(this);
    }

    @Override
    public boolean additionalCheckWin(int row, int col, Cell val) {
        return false;
    }

    @Override
    public boolean isValid(Move move){
        boolean ans = true;
        if (move.getRow() < getHeigth() / 2) {
            if (move.getRow() - move.getCol() < 0) {
                ans = false;
            }
        } else {
            if (move.getCol() - move.getRow() + getHeigth() / 2 < 0) {
                ans = false;
            }
        }
        return super.isValid(move) && ans;
    }

    @Override
    public String outputField(){
        String ws = new String("            ".substring(0, (int)Math.ceil(Math.log10(getWeigth() + 1)) + 1));
        final StringBuilder sb = new StringBuilder("           ".substring(0, (int)Math.ceil(Math.log10(getWeigth() + 1)) + 1));
        for (int r = 1; r < getWeigth() + 1; r++){
            sb.append(r);
            sb.append(ws.substring(0, ws.length() - (int)Math.ceil(Math.log10(r + 1))));
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < getHeigth(); r++) {
            int col = 0;
            sb.append(r + 1);
            sb.append(ws.substring(0, ws.length() - (int)Math.ceil(Math.log10(r + 2))));
            for (Cell cell : field[r]) {
                if (r < getHeigth() / 2 && col > r) {
                    break;
                }
                if (r > getHeigth() / 2 && col < r - getHeigth() / 2) {
                    sb.append(ws.substring(0, ws.length()));
                }else {
                    sb.append(CELL_TO_STRING.get(cell));
                    sb.append(ws.substring(0, ws.length() - 1));
                }
                col++;
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
