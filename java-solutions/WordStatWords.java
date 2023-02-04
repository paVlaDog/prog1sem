import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.lang.Character;
import java.lang.IndexOutOfBoundsException;
import java.lang.ClassCastException;
import java.lang.NullPointerException;
import java.lang.StringBuilder;
import java.io.*;


public class WordStatWords{
    public static void main(String args[]){

        TreeMap<String, Integer> map = new TreeMap<>();
        char symbol;
        int id = 0, symbolId;
        boolean wordExists = false;
        String s = "";
        StringBuilder s1 = new StringBuilder("");
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            );

            try{
                while (true){
                    symbolId = in.read();
                    if (symbolId == -1)break;
                    symbol = (char)symbolId;
                    if (Character.isLetter(symbol) || Character.getType(symbol) == Character.DASH_PUNCTUATION || symbol == '\''){
                        s1.append(symbol);
                    }else{
                        s = s1.toString();
                        if (s.toString() != ""){
                            s = s.toLowerCase();
                            if (map.containsKey(s)){
                                map.replace(s, map.get(s) + 1);
                            }else{
                                map.put(s, 1);
                            }
                            s1.setLength(0);
                        }
                    }
                }
            }finally{
                in.close();
            }

            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );
            try{
                for(Map.Entry<String, Integer> entry : map.entrySet()){
                    out.write(entry.getKey() + " " + entry.getValue() + '\n');
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