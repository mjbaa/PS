import java.util.*;
import java.io.*;

public class Main {
    static int t, n, m;
    static int[] a;
    static int[] b;
    static List<Integer> aList = new ArrayList<>();
    static List<Integer> bList = new ArrayList<>();

    // lower bound: target 이상인 첫 위치
    static int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // upper bound: target 초과인 첫 위치
    static int upperBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

        int[] aSum = new int[n + 1];
        for (int i = 0; i < n; i++) aSum[i + 1] = aSum[i] + a[i];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                aList.add(aSum[j] - aSum[i]);
            }
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) b[i] = Integer.parseInt(st.nextToken());

        int[] bSum = new int[m + 1];
        for (int i = 0; i < m; i++) bSum[i + 1] = bSum[i] + b[i];

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                bList.add(bSum[j] - bSum[i]);
            }
        }

        Collections.sort(bList);

        long result = 0;
        for (int val : aList) {
            int target = t - val;
            result += upperBound(bList, target) - lowerBound(bList, target);
        }

        System.out.println(result);
    }
}
