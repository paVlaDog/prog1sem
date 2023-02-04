import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.lang.ClassCastException;
import java.lang.NullPointerException;
import java.io.*;


public class WsppSecondG{
    public static void main(String args[]){

        HashMap<String, IntListNew> map = new LinkedHashMap<>();
        int numLine = 0;
        int numWord = 0;

        try{
            Scanner inLine = new Scanner(args[0], "utf-8");
            try{
                while (inLine.hasNextLine()){
                    numLine++;
                    while (inLine.hasNextWordInLine()) {
                        String s = inLine.nextWord();
                        numWord++;
                        s = s.toLowerCase();
                        if (map.containsKey(s)){
                            map.get(s).append(numLine);
                            map.get(s).append(numWord);
                        }else{
                            map.put(s, new IntListNew(new int[] {numLine, numWord}));
                        }
                    }
                    inLine.toNextLine();
                }
            }finally{
                inLine.close();
            }
            

            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );
            try{
                Set<Map.Entry<String, IntListNew>> outputSet = map.entrySet();
                for (Map.Entry<String, IntListNew> entry : outputSet) {
                    out.write(entry.getKey() + " " + entry.getValue().to_string_mod() + '\n');
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