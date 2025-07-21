import java.io.*;
import java.util.*;

public class Main {
    static int n,k;

    static int[] dist = new int[100001];
    static int[] count = new int[100001];

    static void bfs() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(n);
        dist[n] = 0;
        count[n] = 1;

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (next < 0 || next > 100000) continue;


                if (dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1;
                    count[next] = count[cur];
                    dq.offer(next);
                }
                else if (dist[next] == dist[cur] + 1) {
                    count[next] += count[cur];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[n] = 0;
        bfs();

        System.out.println(dist[k]);
        System.out.println(count[k]);

    }
}
