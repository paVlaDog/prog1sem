package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
    private final int typeOfBoard;
    private final int height;
    private final int width;
    private final int rowToWin;
    private final int countOfPlayers;
    private final ArrayList<Player> players;

    public Tournament (int typeOfBoard, int height, int width, int rowToWin, int countOfPlayers) {
        this.typeOfBoard = typeOfBoard;
        this.height = height;
        this.width = width;
        this.rowToWin = rowToWin;
        this.countOfPlayers = countOfPlayers;
        this.players = new ArrayList<>();

        for (int i = 0; i < countOfPlayers; i++) {
            players.add(new HumanPlayer(new Scanner(System.in)));
        }
    }

    public void play () {
        int[] scoreboard = new int[countOfPlayers + 1];
        for (int shift = 1; shift < countOfPlayers; shift++) {
            for (int i = 0; i < countOfPlayers ; i++) {
                int firstPlayer = i + 1;
                int secondPlayer = (i + shift) % countOfPlayers + 1;

                System.out.println("Играет игрок " + firstPlayer +
                        " играющий за крестики, против игрока " + secondPlayer +
                        " играющего за нолики");

                Board board;
                if (typeOfBoard == 1) {
                    board = new RectBoard(height, width, rowToWin);
                } else {
                    board = new RhombusBoard(height * 2 - 1, width, rowToWin);
                }

                int result = new TwoPlayerGame(
                        board,
                        players.get(firstPlayer - 1),
                        players.get(secondPlayer - 1),
                        firstPlayer,
                        secondPlayer
                ).play(false);

                switch (result) {
                    case 1:
                        scoreboard[firstPlayer]+=3;
                        break;
                    case 2:
                        scoreboard[secondPlayer]+=3;
                        break;
                    case 0:
                        scoreboard[firstPlayer]++;
                        scoreboard[secondPlayer]++;
                        break;
                    default:
                        throw new AssertionError("Неизвестный результат " + result);
                }
            }
        }
        System.out.println(System.lineSeparator());
        System.out.println("Таблица результатов:");
        for (int i = 1; i < scoreboard.length; i++) {
            System.out.println("Игрок " + i + " набрал " + scoreboard[i] +  " очков");
        }
        System.out.println("Игра завершена");
    }
}
