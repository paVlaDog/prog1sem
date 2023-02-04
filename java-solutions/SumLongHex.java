public class SumLongHex {
    public static void main(String[] args) {
        long sum1 = 0;
        int beginNum = 0;
        boolean hexFl = false, find = false;

        for (int i = 0; i < args.length; i++){
            for (int j = 0; j <= args[i].length(); j++){
                if (!(j == args[i].length()) && !(Character.isWhitespace(args[i].charAt(j)))){
                    if (!find){
                        beginNum = j;
                        find = true;
                    }
                    if(args[i].charAt(j) == 'x' || args[i].charAt(j) == 'X'){
                        hexFl = true;
                        beginNum = j+1;
                    }
                }else{
                    if (hexFl){
                        sum1 += Long.parseUnsignedLong(args[i].substring(beginNum, j), 16);
                    }else if(find){
                        sum1 += Long.parseLong(args[i].substring(beginNum, j));
                    }
                    hexFl = false;
                    find = false;
                }
            }
        }
        System.out.println(sum1);
    }
}