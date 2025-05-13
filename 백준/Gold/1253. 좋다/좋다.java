import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        int count = 0;
        
        for(int k=0; k<n; k++) {
            int target = arr[k];
            int i = 0;
            int j = n - 1;
            
            while(i < j) {
                if(i == k) {
                    i++;
                    continue;
                }
                if(j == k) {
                    j--;
                    continue;
                }

                int sum = arr[i] + arr[j];

                if(sum == target) {
                    count++;
                    break;
                } else if(sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        
        System.out.println(count);
    }
}
