import java.util.*;
import java.io.*;


public class Main {
	
	
    public static void main(String[] args) throws IOException {
    	PriorityQueue<Long> q = new PriorityQueue<>();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	
    	
    	st = new StringTokenizer(br.readLine());
//    	while(st.hasMoreTokens()) {
//    		q.add(Integer.parseInt(st.nextToken()));
//    	}
    	while(n-- > 0) {
    		q.add(Long.parseLong(st.nextToken()));
    	}
    	
    	
    	while(k-- > 0) {
    		Long a = q.poll();
    		Long b = q.poll();
    		
    		q.add(a+b);
    		q.add(a+b);
    	}
    	
    	Long sum = 0l;
    	while(!q.isEmpty()) {
    		sum += q.poll();
    	}
    	System.out.println(sum);
    }

}