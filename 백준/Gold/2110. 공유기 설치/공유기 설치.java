import java.io.*;
import java.util.*;

public class Main {
	static int n,c;
    
	static int[] houses;
	
	static int bns(int min, int max) {
		int result = 0;
		int left = min;
		int right = max;
		
		while(left <= right) {
			int mid = (left + right)/2;
			
			int count = count(mid);
			if(count >= c ) {
				result = mid;
				left = mid+1;
			}else {
				right = mid-1;
			}
			
		}
		
		return result;
	}
	
	static int count(int diff) {
		int cnt = 1;
		
		int pre = houses[0];
		int idx = 1;
		while(idx < n) {
			if(houses[idx] - pre >= diff) {
				cnt++;
				pre = houses[idx];
			}
			idx++;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        houses = new int[n];
        for(int i=0;i<n;i++) {
        	houses[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(houses);
        int max = houses[n-1] - houses[0];
        int min = 1;
        
        int result = bns(min,max);
        
        System.out.print(result);
    }
}
