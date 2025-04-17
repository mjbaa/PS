import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=t;tc++) {
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int k = Integer.parseInt(st.nextToken());
        	
        	List<Integer> lis = new ArrayList<>();
        	st = new StringTokenizer(br.readLine());
        	for(int i=0;i<n;i++) {
        		int val = Integer.parseInt(st.nextToken());
        		
        		int idx = Collections.binarySearch(lis, val);
        		if(idx < 0) {
        			idx++;
        			idx = -1 * idx;
        		}
        		
        		if(idx == lis.size()) {
        			lis.add(val);
        		}else {
        			lis.set(idx, val);
        		}
        	}
        	sb.append("Case #").append(tc).append("\n");
    		if(lis.size()>= k) {
    			sb.append(1);
    		}else {
    			sb.append(0);
    		}
    		sb.append("\n");
        }
        System.out.print(sb);
        
    }
}
