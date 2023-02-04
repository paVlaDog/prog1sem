import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.lang.ClassCastException;
import java.lang.NullPointerException;
import java.io.*;


public class Wspp{
    public static void main(String args[]){

        HashMap<String, IntList> map = new LinkedHashMap<>();
        int numWord = 0;

        try{
            Scanner in1 = new Scanner(args[0], "utf-8");
            try{
                while (in1.hasNextWord()){
                    String s = in1.nextWord();
                    numWord++;
                    s = s.toLowerCase();
                    if (map.containsKey(s)){
                        map.get(s).append(numWord);
                    }else{
                        map.put(s, new IntList(new int[] {numWord}));
                    }
                }
            }finally{
                in1.close();
            }
            

            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );
            try{
                Set<Map.Entry<String, IntList>> outputSet = map.entrySet();
                for (Map.Entry<String, IntList> entry : outputSet) {
                    out.write(entry.getKey() + " " + entry.getValue().to_string() + '\n');
                }
            }finally{
                out.close();
            }

        }catch(IOException e){
            System.out.println("ERROR!!! Read or write error!" + e.getMessage());
        }catch(IndexOutOfBoundsException e){
            System.out.println("ERROR!!! Array or string out of bounds!" + e.getMessage());
        }catch(NullPointerException e){
            System.out.println("ERROR!!! Received word null!" + e.getMessage());
        }catch(ClassCastException e){
            System.out.println("ERROR!!! Received word is not String!" + e.getMessage());
        }

    }
            

}