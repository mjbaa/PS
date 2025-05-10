import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] rUsed = new boolean[9][10];
    static boolean[][] cUsed = new boolean[9][10];
    static boolean[][] sUsed = new boolean[9][10];
    static List<int[]> blanks = new ArrayList<>();
    static int bSize = 0;
    static boolean solved = false;

    static void dfs(int idx){
        if(solved) return;

        if(idx >= bSize){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            solved = true;
            return;
        }

        int[] blank = blanks.get(idx);
        int x = blank[0];
        int y = blank[1];
        int s = (x/3) * 3 + y/3;
        for(int i=1;i<=9;i++){
            if(rUsed[x][i] || cUsed[y][i] || sUsed[s][i]) continue;

            rUsed[x][i] = cUsed[y][i] = sUsed[s][i] = true;
            board[x][y] = i;
            dfs(idx+1);
            board[x][y] = 0;
            rUsed[x][i] = cUsed[y][i] = sUsed[s][i] = false;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++) {
            String line = br.readLine();
            for(int j = 0; j < 9; j++) {
                int num = line.charAt(j) - '0';
                board[i][j] = num;
                if(num ==0) blanks.add(new int[]{i, j});
                else{
                    rUsed[i][num] = true;
                    cUsed[j][num] = true;
                    int s = (i/3) * 3 + j/3;
                    sUsed[s][num] = true;
                }
            }
        }
        bSize = blanks.size();

        dfs(0);
    }
}
