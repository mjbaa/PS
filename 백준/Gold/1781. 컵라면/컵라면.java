import java.io.*;
import java.util.*;

public class Main {
    static class Problem implements Comparable<Problem> {
        int deadLine;
        int cost;

        Problem(int deadLine, int cost) {
            this.deadLine = deadLine;
            this.cost = cost;
        }

        public int compareTo(Problem o) {
            return Integer.compare(this.deadLine, o.deadLine); // 마감일 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        List<Problem> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            list.add(new Problem(deadLine, ramen));
        }

        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Problem p : list) {
            pq.add(p.cost); // 현재 문제를 선택
            if (pq.size() > p.deadLine) {
                pq.poll(); // 가장 적은 라면 수 버림
            }
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
