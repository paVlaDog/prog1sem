import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Integer;
import java.util.Arrays;
import java.io.*;


public class Eqi {

    public static void main(String args[]) {
        VeryFastScanner in = new VeryFastScanner(System.in);
        int numOfCities = in.nextInt();
        int numOfTeams = in.nextInt();
        IntListNew edge = new IntListNew(numOfCities);
        HashSet<Integer> teams = new HashSet<Integer>();
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < numOfCities - 1; i++) {
            int ver1 = in.nextInt();
            int ver2 = in.nextInt();
            edge.append(ver1 - 1, ver2 - 1);
            edge.append(ver2 - 1, ver1 - 1);
        }

        q.offer(in.nextInt() - 1);
        teams.add(q.element());
        int ans = q.element();

        for (int i = 0; i < numOfTeams - 1; i++) {
            teams.add(in.nextInt() - 1);
        }

        int[] way = new int[numOfCities];
        way[q.element()] = -1;
        int[] deep = new int[numOfCities];
        deep[q.element()] = 1;
        int lastTeam = -1, usedTeams = 0;

        while(q.size() != 0) {
            int now = q.remove();
            if (teams.contains(now)){
                usedTeams++;
                lastTeam = now;
                if (usedTeams == numOfTeams){
                    break;
                }
            }
            for (int i = 0; i < edge.size(now); i++) {
                if (deep[edge.get(now, i)] == 0) {
                    way[edge.get(now, i)] = now;
                    deep[edge.get(now, i)] = deep[now] + 1;
                    q.offer(edge.get(now, i));
                }
            }
        }

        int temp = lastTeam;
        for (int i = 0; i < deep[lastTeam]/2; i++) {
            temp = way[temp];
        }
        ans = temp;
        

        boolean hasAns = true;
        q = new LinkedList<>();
        q.offer(ans);
        deep = new int[numOfCities];
        deep[ans] = 1;
        int deepTeams = 0;

        while(q.size() != 0) {
            int now = q.remove();
            if (teams.contains(now)){
                if (deepTeams == 0) {
                    deepTeams = deep[now];
                }
                if (deepTeams != deep[now]) {
                    hasAns = false;
                }
            }
            for (int i = 0; i < edge.size(now); i++) {
                if (deep[edge.get(now, i)] == 0) {
                    q.offer(edge.get(now, i));
                    deep[edge.get(now, i)] = deep[now] + 1;
                }
            }
        }

        if (hasAns){
            System.out.println("YES");
            System.out.println(ans + 1);
        }
        else {
            System.out.println("NO");
        }
    }
}

class VeryFastScanner{
    private Reader in = null;
    private int bufferSize = 0, index;
    private char[] buffer = new char[1024*8];

    public VeryFastScanner (InputStream in) {
        try {
            this.in = new BufferedReader(new InputStreamReader(in));
            index = 0;
            bufferSize = this.in.read(buffer);    
        } catch (IOException e) {
            System.out.println("Scanner initialization error");   
        }
        
    }

    private String nextToken (String oldToken) throws IOException {
        StringBuilder token = new StringBuilder(oldToken);
        for (; index < bufferSize; index++) {
            if (!Character.isWhitespace(buffer[index]) || !"".equals(oldToken)) {
                break;
            }
        }
        for (; index < bufferSize; index++) {
            if (Character.isWhitespace(buffer[index]) || buffer[index] == '\n') {
                index++;
                return token.toString();
            }
            token.append(buffer[index]);
        }
        if (bufferSize != -1) {
            index = 0;
            bufferSize = in.read(buffer);
            return nextToken(token.toString());
        }else{
            return token.toString();
        }
    }

    public String next() {
        try {
            return nextToken("");
        } catch (IOException e) {
            System.out.println("Read Error");
            return "Error";
        }
    }

    public int nextInt() {
        try {
            return Integer.parseInt(nextToken(""));
        } catch (IOException e) {
            System.out.println("Read Error");
            return -1;
        }
    }
}

class IntListNew {
    private int[][] list;
    private int[] realLenght;

    public IntListNew (int n) {
        this.list = new int[n][0];
        this.realLenght = new int[n];
    }

    public void append (int index1, int num) {
        if (this.list[index1].length > this.realLenght[index1]) {
            this.list[index1][this.realLenght[index1]] = num;
            this.realLenght[index1]++;
        }
        else {
            this.list[index1] = Arrays.copyOf(this.list[index1], this.list[index1].length*2 + 1);
            this.list[index1][this.realLenght[index1]] = num;
            this.realLenght[index1]++;
        }
    }

    public int size (int index1) {
        return this.realLenght[index1];
    }

    public int get (int index1, int index2) throws IndexOutOfBoundsException {
        return this.list[index1][index2];
    }
}

