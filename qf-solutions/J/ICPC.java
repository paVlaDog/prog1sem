import java.util.Scanner;
import java.lang.Math;

public class ICPC {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] ways = new int[n][n];
        for (int i = 0; i < n; i++) {
            String temp = in.next();
            for (int j = 0; j < n; j++) {
                ways[i][j] = (int)temp.charAt(j) - (int)'0';
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ways[i][j] != 0) {
                    for (int k = j + 1; k < n; k++) {
                        ways[i][k] = (ways[i][k] - ways[j][k] + 10) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ways[i][j]);
            }
            System.out.println();
        }
    }
}