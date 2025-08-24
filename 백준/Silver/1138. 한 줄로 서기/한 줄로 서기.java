import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] left = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            left[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> line = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            line.add(left[i - 1], i);
        }

        for (int l : line) {
            sb.append(l).append(' ');
        }
        System.out.print(sb);
    }
}
