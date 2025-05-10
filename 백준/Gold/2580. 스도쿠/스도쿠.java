import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][][] visited = new boolean[9][9][10];
    static List<int[]> blanks = new ArrayList<>();
    static int bSize = 0;

    static StringBuilder sb = new StringBuilder();
    
    static boolean finished = false;
    static void dfs(int bidx){
        if(finished) return;
        
        if(bidx >= bSize) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            finished = true;
            return;
        }

        int[] blank = blanks.get(bidx);
        int bx = blank[0];
        int by = blank[1];

        Arrays.fill(visited[bx][by], false);

        //xy축
        for(int i=0;i<9;i++){
            visited[bx][by][board[i][by]] = true;
            visited[bx][by][board[bx][i]] = true;
        }

        //작은 정사각형
        int nx = (bx / 3) * 3;
        int ny = (by / 3) * 3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                visited[bx][by][board[nx+i][ny+j]] = true;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (visited[bx][by][i]) continue;

            board[bx][by] = i;
            dfs(bidx + 1);
            board[bx][by] = 0;
        }




    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) blanks.add(new int[]{i, j});
            }
        }

        bSize = blanks.size();

        dfs(0);


    }
}
