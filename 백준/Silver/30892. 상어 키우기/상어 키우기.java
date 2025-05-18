import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        long[] sharks = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sharks[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(sharks);
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        int idx = 0;
        while (K > 0) {
            while (idx < N && sharks[idx] < T) {
                pq.offer(sharks[idx]);
                idx++;
            }

            if (pq.isEmpty()) {
                break;
            }

            T += pq.poll();
            K--;
        }

        System.out.println(T);
    }
}
