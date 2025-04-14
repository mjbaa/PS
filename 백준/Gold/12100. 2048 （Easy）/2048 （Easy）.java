
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;

    static int max;
    static void moveUp() {
        for (int j = 0; j < n; j++) { // 열 고정
            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                if (board[i][j] != 0) {
                    list.add(board[i][j]);
                    board[i][j] = 0;
                }
            }

            int idx = 0;
            while (!list.isEmpty()) {
                int now = list.poll();
                if (!list.isEmpty() && now == list.peek()) {
                    board[idx++][j] = now * 2;
                    max = Math.max(max, now*2);
                    list.poll();
                } else {
                    board[idx++][j] = now;
                }
            }
        }
    }

    static void moveDown() {
        for (int j = 0; j < n; j++) { // 열 고정
            LinkedList<Integer> list = new LinkedList<>();

            for (int i = n - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    list.add(board[i][j]);
                    board[i][j] = 0;
                }
            }

            int idx = n - 1;
            while (!list.isEmpty()) {
                int now = list.poll();
                if (!list.isEmpty() && now == list.peek()) {
                    board[idx--][j] = now * 2;
                    max = Math.max(max, now*2);
                    list.poll();
                } else {
                    board[idx--][j] = now;
                }
            }
        }
    }

    static void moveLeft() {
        for (int i = 0; i < n; i++) { // 행 고정
            LinkedList<Integer> list = new LinkedList<>();

            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    list.add(board[i][j]);
                    board[i][j] = 0;
                }
            }

            int idx = 0;
            while (!list.isEmpty()) {
                int now = list.poll();
                if (!list.isEmpty() && now == list.peek()) {
                    board[i][idx++] = now * 2;
                    max = Math.max(max, now*2);
                    list.poll();
                } else {
                    board[i][idx++] = now;
                }
            }
        }
    }

    static void moveRight() {
        for (int i = 0; i < n; i++) { // 행 고정
            LinkedList<Integer> list = new LinkedList<>();

            for (int j = n - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    list.add(board[i][j]);
                    board[i][j] = 0;
                }
            }

            int idx = n - 1;
            while (!list.isEmpty()) {
                int now = list.poll();
                if (!list.isEmpty() && now == list.peek()) {
                    board[i][idx--] = now * 2;
                    max = Math.max(max, now*2);
                    list.poll();
                } else {
                    board[i][idx--] = now;
                }
            }
        }
    }

    static void dfs(int depth) {
        if (depth == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, board[i][j]);
                }
            }
            return;
        }

        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = board[i][j];
            }
        }


        for (int dir = 0; dir < 4; dir++) {
            switch (dir) {
                case 0: moveUp(); break;
                case 1: moveDown(); break;
                case 2: moveLeft(); break;
                case 3: moveRight(); break;
            }
            dfs(depth + 1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = temp[i][j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = Integer.MIN_VALUE;
        dfs(0);

        System.out.println(max);

    }
}



