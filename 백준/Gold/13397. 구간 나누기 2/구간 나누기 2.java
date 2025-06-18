import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] data;

    //max - min <= mid 로 설정할 떄 구간의 개수가 <=m 인지?
    static boolean check(int mid) { 
        int cnt = 1;
        int max = data[0];
        int min = data[0];

        for (int i = 1; i < n; i++) {
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);

            if (max - min > mid) {
                cnt++;
                max = data[i];
                min = data[i];
            }
        }

        return cnt <= m;
    }

    static int bnSearch(int l, int r) {
        int left = l;
        int right = r;
        int answer = r; // 최솟값 찾기 -> 초기값 : max값인 'r'

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) { // mid에 대해 가능한지?
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n];
        st = new StringTokenizer(br.readLine());

        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            maxVal = Math.max(maxVal, data[i]);
            minVal = Math.min(minVal, data[i]);
        }

        int result = bnSearch(0, maxVal - minVal);

        System.out.println(result);
    }
}
