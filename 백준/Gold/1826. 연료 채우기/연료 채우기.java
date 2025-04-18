import java.io.*;
import java.util.*;

public class Main {
	public static class GasStation implements Comparable<GasStation> {
        int a;
        int b;

        public GasStation(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(GasStation o) {
            return this.a - o.a;
        }
    }
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<GasStation> stations = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            stations.add(new GasStation(a, b));
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;

        while (P < L) {
            while (!stations.isEmpty() && stations.peek().a <= P) {
                fuels.add(stations.poll().b);
            }

            if (fuels.isEmpty()) {
                System.out.println(-1);
                return;
            }

            answer++;
            P += fuels.poll();
        }

        System.out.println(answer);
    }

    
}