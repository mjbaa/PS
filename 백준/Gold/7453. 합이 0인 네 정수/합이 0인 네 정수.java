import java.io.*;
import java.util.*;

public class Main {


	
	static int count(int[] list, int val) {
	    int lower = lowerBound(list, val);
	    int upper = upperBound(list, val);
	    return upper - lower;
	}

	static int lowerBound(int[] list, int val) {
	    int left = 0, right = list.length;
	    while (left < right) {
	        int mid = (left + right) / 2;
	        if (list[mid] < val) {
	            left = mid + 1;
	        } else {
	            right = mid;
	        }
	    }
	    return left;
	}

	static int upperBound(int[] list, int val) {
	    int left = 0, right = list.length;
	    while (left < right) {
	        int mid = (left + right) / 2;
	        if (list[mid] <= val) {
	            left = mid + 1;
	        } else {
	            right = mid;
	        }
	    }
	    return left;
	}

	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	A[i] = Integer.parseInt(st.nextToken());
        	B[i] = Integer.parseInt(st.nextToken());
        	C[i] = Integer.parseInt(st.nextToken());
        	D[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] AB = new int[n*n];
        int[] CD = new int[n*n];
        
        int idx = 0;
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		AB[idx] = A[i]+B[j];
        		CD[idx] = C[i]+D[j];
        		idx++;
        	}
        }
        
        Arrays.sort(CD);
        
        
        long cnt = 0;
        for(int i=0;i<n*n;i++) {
        	cnt += count(CD, -1*AB[i]);
        	
        }
        
        System.out.println(cnt);
        
    }
    
    
}
