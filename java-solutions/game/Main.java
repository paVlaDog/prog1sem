package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Выберите тип соревнований(1 - обычный матч, 2 - круговой турнир):");
            int typeOfGame = Integer.parseInt(sc.next());

            System.out.println("Выберите доску (1 - квадратная, 2 - ромбовидная):");
            int typeOfBoard = Integer.parseInt(sc.next());

            System.out.println("Введите размеры поля (Сначала высота, потом длина) " +
                    "(для ромба числа должны быть равны и нечётны):");
            int height = Integer.parseInt(sc.next());
            int width = Integer.parseInt(sc.next());

            System.out.println("Введите длину последовательности, необходимую для победы:");
            int rowToWin = Integer.parseInt(sc.next());

            int countOfPlayers = 2;
            if (typeOfGame == 2) {
                System.out.println("Введите количество участников:");
                countOfPlayers = Integer.parseInt(sc.next());
            }

            if (height < 0 || width < 0
                    || rowToWin < 0 || rowToWin > height || rowToWin > width
                    || (typeOfBoard != 1 && typeOfBoard != 2)
                    || (typeOfGame != 1 && typeOfGame != 2)
                    || countOfPlayers < 0 || countOfPlayers > 1000
                    || (typeOfBoard == 2 && width != height && width % 2 != 0)) {
                throw new AssertionError("Неверные параметры игры. Запуск игры отменён.");
            }

            if (typeOfGame == 1) {
                new Match(typeOfBoard, height, width, rowToWin).play();
            } else if (typeOfGame == 2) {
                new Tournament(typeOfBoard, height, width, rowToWin, countOfPlayers).play();
            }

        }catch (NoSuchElementException e) {
            System.out.println("Данные не были получены. Запуск игры отменён.");
        }catch (NumberFormatException e) {
            System.out.println("Были введены недопустимые символы. Запуск игры отменён.");
        }catch (IllegalStateException e) {
            System.out.println("Поток ввода был закрыт преждевременно. Запуск игры отменён.");
        }catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }
}
