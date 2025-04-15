import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] aArr = new int[n];
        int[] bArr = new int[n];
        
        Map<Integer, Integer> priority = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
        	aArr[i] = Integer.parseInt(st.nextToken());
        	
        	priority.put(aArr[i], i); // 값 -> priority 추출
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
        	bArr[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> lis = new ArrayList<>();
        
        for(int i=0;i<n;i++) {
        	int val = bArr[i];
        	int idx = Collections.binarySearch(lis, val, (a,b) -> Integer.compare(priority.get(a), priority.get(b)));
        	
        	if(idx < 0) {
        		idx ++;
        		idx *= -1;
        	}
        	
        	if(idx == lis.size()) {
        		lis.add(val);
        		
        	}else {
        		lis.set(idx, val);
        	}
        }
        
        System.out.println(lis.size());
        
        
    }
}
