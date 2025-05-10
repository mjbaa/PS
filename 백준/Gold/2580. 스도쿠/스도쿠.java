import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] row = new boolean[9][10];       // row[i][num]
    static boolean[][] col = new boolean[9][10];       // col[j][num]
    static boolean[][] square = new boolean[9][10];    // square[k][num]
    static List<int[]> blanks = new ArrayList<>();
    static boolean solved = false;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int idx) {
        if (solved) return;

        if (idx == blanks.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            solved = true;
            return;
        }

        int[] blank = blanks.get(idx);
        int x = blank[0], y = blank[1];
        int s = (x / 3) * 3 + (y / 3);

        for (int num = 1; num <= 9; num++) {
            if (row[x][num] || col[y][num] || square[s][num]) continue;

            board[x][y] = num;
            row[x][num] = col[y][num] = square[s][num] = true;

            dfs(idx + 1);

            board[x][y] = 0;
            row[x][num] = col[y][num] = square[s][num] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                } else {
                    int num = board[i][j];
                    row[i][num] = true;
                    col[j][num] = true;
                    square[(i / 3) * 3 + (j / 3)][num] = true;
                }
            }
        }

        dfs(0);
    }
}
