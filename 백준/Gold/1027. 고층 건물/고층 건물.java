import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] data;
    static int result = Integer.MIN_VALUE;
    
    static int getLeft(int idx){
        if (idx == 0) return 0;
        int cnt = 0;
        double minSlope = Double.POSITIVE_INFINITY; 
        int base = data[idx];

        for (int j = idx - 1; j >= 0; j--) {
            double slope = (double)(base - data[j]) / (idx - j);
            if (slope < minSlope) {
                cnt++;
                minSlope = slope;
            }
        }
        return cnt;
    }


    static int getRight(int idx){
        if (idx == n - 1) return 0;
        int cnt = 0;
        double maxSlope = Double.NEGATIVE_INFINITY;
        int base = data[idx];

        for (int j = idx + 1; j < n; j++) {
            double slope = (double)(data[j] - base) / (j - idx);
            if (slope > maxSlope) {
                cnt++;
                maxSlope = slope;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<n;i++){
            int cur = getLeft(i) + getRight(i);
            result = Math.max(cur,result);
        }

        System.out.println(result);
    }
}
