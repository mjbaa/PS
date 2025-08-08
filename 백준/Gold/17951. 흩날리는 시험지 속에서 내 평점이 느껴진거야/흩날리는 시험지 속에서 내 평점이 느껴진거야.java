import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] data;

    static boolean check(int mid) {
        int count = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += data[i];
            if (sum >= mid) {
                count++;
                sum = 0;
            }
        }

        return count >= k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        data = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 2000000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
