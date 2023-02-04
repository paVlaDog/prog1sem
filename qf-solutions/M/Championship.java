import java.util.Scanner;
import java.lang.Math;
import java.util.HashMap;

public class Championship {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        for (int k = 0; k < count; k++){
            HashMap<Integer, Integer> map = new HashMap<>();
            int n = in.nextInt();
            int value[] = new int[n];
            for (int i = 0; i < n; i++) {
                value[i] = in.nextInt();
            }
            int sum = 0;
            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    if (map.containsKey(2*value[j] - value[i])){
                        sum += map.get(2*value[j] - value[i]);
                    }
                }
                if (map.containsKey(value[j])){
                    map.replace(value[j], map.get(value[j]) + 1);
                }else{
                    map.put(value[j], 1);
                }
            }
            System.out.println(sum);
        }
    }
}