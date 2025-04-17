import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        List<Integer> lis = new ArrayList<>();
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
        
        System.out.println(n-lis.size());
    }
}
