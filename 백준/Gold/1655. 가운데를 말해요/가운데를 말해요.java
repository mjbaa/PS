import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
        PriorityQueue<Integer> right = new PriorityQueue<>(); // 최소 힙

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());

            if (left.size() == right.size()) {
                left.offer(val);
            } else {
                right.offer(val);
            }

            if (!right.isEmpty() && left.peek() > right.peek()) {
                int l = left.poll();
                int r = right.poll();
                left.offer(r);
                right.offer(l);
            }

            sb.append(left.peek()).append("\n");
        }

        System.out.print(sb);
    }
}
