import java.util.*;
import java.io.*;


public class Main {	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[][] data = new int[n][2];
    	
    	StringTokenizer st;
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());    		
    		data[i][0] = Integer.parseInt(st.nextToken());
    		data[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(data,(a,b)->{
    		if(a[1] == b[1]) {
        		return a[0] - b[0];
        		
        	}else {
        		return a[1] - b[1];
        	}
    	});
    	
    	int cnt = 0;
    	int prevEnd =0;
    	for(int i=0;i<n;i++) {
    		if(data[i][0] >= prevEnd) {
    			cnt++;
    			prevEnd = data[i][1];
    		}
    	}
    	System.out.println(cnt);
    	
    
    	
    	
    }

}