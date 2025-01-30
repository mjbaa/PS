import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        int[] len = new int[100001];
        q.offer(n);
        boolean[] visited = new boolean[100001];

        while(!q.isEmpty()) {

            int cur = q.poll();
            if(cur == k){
                System.out.println(len[cur]);
                return;
            }

            int[] nextMoves = {cur - 1, cur + 1, cur * 2};

            for (int next : nextMoves) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    len[next] = len[cur] + 1;
                }
            }


        }
    }
}
