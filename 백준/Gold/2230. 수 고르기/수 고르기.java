import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if(n == 0 || m==0){
            System.out.println(0);
            return;
        }

        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        while(end < n) {
            int diff = nums[end] - nums[start];
            if(diff < m) {
                end++;
            } else {
                min = Math.min(min, diff);
                start++;
            }
        }

        System.out.println(min);
    }
}
