public class Sum {
    public static void main(String[] args) {
        int sum1 = 0;
        int beginNum = 0;
        boolean find = false;
        for (int i = 0; i < args.length; i++){
            for (int j = 0; j <= args[i].length(); j++){
                //System.err.println(args[i].charAt(j));
                if (!(j == args[i].length()) && !(Character.isWhitespace(args[i].charAt(j)))){
                    if (!find){
                        beginNum = j;
                        find = true;
                    }
                }else if(find){
                    sum1 += Integer.parseInt(args[i].substring(beginNum, j));
                    find = false;
                }
            }
        }
        System.out.println(sum1);
    }
}