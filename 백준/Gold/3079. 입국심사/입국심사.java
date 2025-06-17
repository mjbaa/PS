import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static long[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        long max = 0;
        data = new long[n];
        for(int i = 0; i < n; i++) {
            long value = Long.parseLong(br.readLine());
            data[i] = value;
            max = Math.max(max, value);

        }

        long left = 1;
        long right = max * m;
        long answer = 0;

        while(left <= right){
            long mid = left+(right-left)/2;

            long cnt = 0;
            for(int i=0;i<n;i++){
                cnt += mid / data[i];
                if (cnt >= m) break;
            }

            if(cnt >= m){
                answer = mid;
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        System.out.println(answer);
    }
}
