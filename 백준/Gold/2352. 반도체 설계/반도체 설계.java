import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] data = new int[n+1];
        for(int i=1;i<=n;i++) {
        	data[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> lis = new ArrayList<>();
        
        for(int i=1;i<=n;i++) {
        	int idx = Collections.binarySearch(lis, data[i]);
        	
        	if(idx < 0) {
        		idx ++;
        		idx *= -1;
        	}
        	
        	if(idx == lis.size()) {
        		lis.add(data[i]);
        	}else {
        		lis.set(idx, data[i]);
        	}
        }
        
        System.out.println(lis.size());
    }
}
