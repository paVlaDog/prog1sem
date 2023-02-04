import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Character;
import java.lang.IndexOutOfBoundsException;
import java.lang.ClassCastException;
import java.lang.NullPointerException;
import java.io.*;


public class WordStatInput{
    public static void main(String args[]){

        HashMap<String, Integer> map = new HashMap<>();
        ArrayList <String> arr = new ArrayList <String>();
        String s = "";

        try{
            Scanner in1 = new Scanner(args[0], "utf-8");
            try{
                while (in1.hasNextWord()){
                    s = in1.nextWord();
                    s = s.toLowerCase();
                    if (map.containsKey(s)){
                        map.replace(s, map.get(s) + 1);
                    }else{
                        map.put(s, 1);
                        arr.add(s);
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
                for (int i = 0; i < arr.size(); i++) {
                    out.write(arr.get(i) + " " + map.get(arr.get(i)) + '\n');
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