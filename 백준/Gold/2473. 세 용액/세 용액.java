import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        long[] data = new long[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);
        
        long min = Long.MAX_VALUE;
        long ans1 = 0l, ans2 = 0l, ans3 = 0l;
        
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                long sum = data[i] + data[left] + data[right];
                
                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    ans1 = data[i];
                    ans2 = data[left];
                    ans3 = data[right];
                }
                
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        long[] ans = {ans1, ans2, ans3};
        Arrays.sort(ans); // 오름차순 정렬해서 출력
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
