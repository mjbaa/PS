import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        
        int[] data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
        	data[i] = Integer.parseInt(st.nextToken());
        }
        
        
        
        int ptr1 = 0;
        int ptr2 = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        
        while (true) {
            if (sum >= s) { 
                minLen = Math.min(minLen, ptr2 - ptr1);
                sum -= data[ptr1];
                ptr1++;
            } else if (ptr2 == n) {
                break;
            } else {
                sum += data[ptr2];
                ptr2++;
            }
        }
        
        if (minLen == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLen);
        }
        
        
    }
}
