import java.lang.Math;
import java.util.Scanner;

public class Database {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), maxTrans = 0;
        int[] trans = new int[n + 1];
        trans[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            trans[i] = trans[i - 1] + in.nextInt();
            maxTrans = Math.max(maxTrans, trans[i] - trans[i - 1]);
        }
        int countQue = trans[n];

        int[] queries = new int[countQue];
        for (int i = 0; i < n; i++) {
            for (int j = trans[i]; j < trans[i + 1]; j++) {
                queries[j] = i;
            }
        }

        int countBlock = 0;
        int[] ans = new int[countQue];
        for (int t = maxTrans; t < countQue; t++) {
            int curTrans = 0;
            for (countBlock = 1; trans[curTrans] + t < countQue; countBlock++) {
                curTrans = queries[trans[curTrans] + t];
            }
            ans[t] = countBlock;
        }

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            if (t >= countQue){
                System.out.println(1);
            }else if (t < maxTrans){
                System.out.println("Impossible");
            }else {
                System.out.println(ans[t]);
            }
        }
    }
}

