import java.util.Scanner;
import java.lang.Math;

public class ICPC {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int xl = 0, xr = 0, yl = 0, yr = 0, n;
        xl = Integer.MAX_VALUE;
        yl = Integer.MAX_VALUE;
        xr = Integer.MIN_VALUE; 
        yr = Integer.MIN_VALUE;
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int h = in.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        int ansX = (xl + xr)/2;
        int ansY = (yl + yr)/2;
        int ansH = xr - xl > yr - yl ? xr - xl : yr - yl;
        System.out.println(ansX + " " + ansY + " " + (ansH + 1)/2);
    }
}