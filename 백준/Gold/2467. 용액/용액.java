import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int idx1 = 0;
        int idx2 = n - 1;
        int minSum = Integer.MAX_VALUE;
        int ans1 = 0, ans2 = 0;

        while (idx1 < idx2) { 
            int sum = data[idx1] + data[idx2];

            if (Math.abs(sum) < minSum) { 
                minSum = Math.abs(sum);
                ans1 = data[idx1];
                ans2 = data[idx2];
            }
            

            if (sum > 0) {
                idx2--;
            } else {
                idx1++;
            }

        }

        System.out.println(ans1 + " " + ans2);

    }
}
