import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.io.IOException;

public class ReverseMin2 {
    public static void main(String args[]) {

        int indexRow = 0, indexCol = 0, numOfCol = 0;
        Scanner in = new Scanner(System.in);
        int[][] nums = new int[1][0];
        while (in.hasNextLine()) {
            Scanner inRow = new Scanner(in.nextLine());
            int[] row = new int[0];

            while (inRow.hasNextInt()) {
                if (row.length <= indexCol) {
                    row = Arrays.copyOf(row, row.length * 2 + 1);
                }
                row[indexCol++] = inRow.nextInt();
            }

            if (nums.length <= indexRow) {
                nums = Arrays.copyOf(nums, nums.length * 2 + 1);
            }
            nums[indexRow++] = Arrays.copyOf(row, indexCol);
            numOfCol = (int)Math.max(numOfCol, indexCol);
            indexCol = 0;
        }

        int[] minNumsInCol = new int[numOfCol];
        Arrays.fill(minNumsInCol, Integer.MAX_VALUE);
        
        for (int i = 0; i < indexRow; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                minNumsInCol[j] = (int)Math.min(minNumsInCol[j], nums[i][j]);
                nums[i][j] = minNumsInCol[j];
                if (j > 0){
                    nums[i][j] = (int)Math.min(nums[i][j], nums[i][j - 1]);
                }
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }

    }
}