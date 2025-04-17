import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=t;tc++) {
        	int n = Integer.parseInt(br.readLine());
        	
        	List<Integer> lis = new ArrayList<>();

        	for(int i=0;i<n;i++) {
        		int val = Integer.parseInt(br.readLine());
        		
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
        	sb.append(lis.size()).append("\n");

        }
        System.out.print(sb);
        
    }
}
