package game;

import java.util.NoSuchElementException;

public class TwoPlayerGame {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final int playerName1;
    private final int playerName2;


    public TwoPlayerGame(Board board, Player player1, Player player2, int playerName1, int playerName2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = makeMove(player1, playerName1, log);
            if (result1 != -1)  {
                return result1;
            }
            final int result2 = makeMove(player2, playerName2, log);
            if (result2 != -1)  {
                return result2;
            }
        }
    }

    private int makeMove(Player player, int playerName, boolean log) {
        GameResult result = GameResult.LOOSE;
        try {
            final Move move;
            if (player.getClass().equals(HumanPlayer.class)) {
                move = humanPlayerInput(player);
            } else {
                move = player.makeMove(board.getPosition());
            }
            if (!board.getPosition().isValid(move)) {
                throw new IndexOutOfBoundsException("Игрок " + playerName + " сделал ход за пределы поля");
            }
            result = board.makeMove(move);

            if (log) {
                System.out.println();
                System.out.println("Игрок: " + playerName);
                System.out.println(move);
                System.out.println(board.outputField());
                System.out.println("Результат: " + result);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Игрок " + playerName + " не сделал ход.");
        } catch (NumberFormatException e) {
            System.out.println("Игрок " + playerName + "Ввёл недопустимые символы.");
        } catch (IllegalStateException e) {
            System.out.println("Игрок " + playerName + "Преждевременно закрыл поток ввода.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(System.lineSeparator());
        switch (result) {
            case WIN:
                board.outputField();
                System.out.println("Победил игрок " + playerName1);
                System.out.println("Игрок " + playerName1 + " - вы великолепны. Легчайшая для величайшего");
                System.out.println("Игрок " + playerName2 + ", извините, но вы проиграли. Очень жаль.");
                return playerName;
            case LOOSE:
                board.outputField();
                System.out.println("Победил игрок " + playerName2);
                System.out.println("Игрок " + playerName2 + " - we are champions!");
                System.out.println("Игрок " + playerName1 + ", порхай как бабочка, жаль вы проиграли.");
                return playerName1 + playerName2 - playerName;
            case DRAW:
                board.outputField();
                System.out.println("Игроки " + playerName1 + " и " + playerName2 + " сыграли вничью");
                return 0;
            case UNKNOWN:
                return -1;
            default:
                throw new AssertionError("Неизвестный результат: " + result);
        }
    }

    private Move humanPlayerInput(Player player) throws IllegalStateException {
        System.out.println();
        System.out.println("Текущая позиция");
        System.out.println(board.outputField());
        System.out.println("Введите ваш ход: " + board.getPosition().getTurn());
        while (true) {
            try {
                Move humanMove = player.makeMove(board.getPosition());
                if (board.getPosition().isValid(humanMove)) {
                    return humanMove;
                } else {
                    throw new IndexOutOfBoundsException("Данная клетка находится за пределами поля");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Данные не были получены.");
            } catch (NumberFormatException e) {
                System.out.println("Были введены недопустимые символы.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (AssertionError e) {
                System.out.println("Был получен неизвестный результат");
            }
            System.out.println("Неверный ввод. Попробуйте снова");
        }
    }
}
