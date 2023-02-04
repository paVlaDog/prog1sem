//import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.io.IOException;

public class Reverse {
    public static void main(String args[]) {
        
        try{
            int indexRow = 0, indexCol = 0, numOfCol = 0;
            Scanner in = new Scanner(System.in);
            String[][] nums = new String[1][0];
            while (in.hasNextLine()) {
                //Scanner inRow = new Scanner(in.nextLine());
                String[] row = new String[0];

                while (in.hasNextInLine()) {
                    if (row.length <= indexCol) {
                        row = Arrays.copyOf(row, row.length * 2 + 1);
                    }
                    row[indexCol++] = in.next();
                }
                in.toNextLine();

                if (nums.length <= indexRow) {
                    nums = Arrays.copyOf(nums, nums.length * 2 + 1);
                }
                nums[indexRow++] = Arrays.copyOf(row, indexCol);
                numOfCol = (int)Math.max(numOfCol, indexCol);
                indexCol = 0;
            }

            int[] minNumsInCol = new int[numOfCol];
            Arrays.fill(minNumsInCol, Integer.MAX_VALUE);
            
            for (int i = indexRow - 1; i >= 0; i--) {
                for (int j = nums[i].length - 1; j >= 0; j--) {
                    System.out.print(nums[i][j] +  " ");
                }
                System.out.println();
            }
        }catch(IOException e) {
            System.out.println("Input or output error" + e.getMessage());
        }

    }
}