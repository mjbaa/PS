import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] data;
    static int[] parent;

    static int[] dx = {0, 0, 1, -1};   // R, L, D, U
    static int[] dy = {1, -1, 0, 0};

    static int getDir(char c) {
        if (c == 'R') return 0;
        else if (c == 'L') return 1;
        else if (c == 'D') return 2;
        else return 3;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;
        parent[bRoot] = aRoot;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new char[n][m];
        parent = new int[n * m];

        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                data[i][j] = line.charAt(j);
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                int dir = getDir(data[x][y]);
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                int curIdx = x * m + y;
                int nextIdx = nx * m + ny;

                union(curIdx, nextIdx);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n * m; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }
}