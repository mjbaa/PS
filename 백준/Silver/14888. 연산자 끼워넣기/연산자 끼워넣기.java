import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] data;
    static int[] op = new int[4]; // +, -, *, /
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    static void dfs(int cur, int idx) {
        if (idx == N) {
            min = Math.min(min, cur);
            max = Math.max(max, cur);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;

            op[i]--;
            int next = cur;

            if (i == 0) {
                next = cur + data[idx];
            } else if (i == 1) {
                next = cur - data[idx];
            } else if (i == 2) {
                next = cur * data[idx];
            } else {
                next = cur / data[idx];
            }

            dfs(next, idx + 1);
            op[i]++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        data = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(data[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

}
