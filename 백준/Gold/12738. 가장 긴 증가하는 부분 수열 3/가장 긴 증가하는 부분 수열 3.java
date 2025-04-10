import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        
        int[] data= new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
        	data[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0;i<n;i++) {
        	int val = data[i];
        	int idx = Collections.binarySearch(list, val);
        	
        	if(idx < 0) {
        		idx++;
        		idx = -1 * idx;
        	}
        	
        	if(idx == list.size()) {
        		list.add(val);
        	}else {
        		list.set(idx, val);
        	}
        }
        
        System.out.print(list.size());
        
    }
}
