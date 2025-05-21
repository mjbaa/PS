import java.io.*;
import java.util.*;

public class Main {
    static int n;
	public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Deque<Integer> dq = new ArrayDeque<>();
        Deque<Integer> result = new ArrayDeque<>();
        
        int[] data = new int[n];
        for(int i=0;i<n;i++) {
        	data[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i= n-1;i>=0;i--) {
        	int cur = data[i];
        	
        	if(dq.isEmpty()) {
        		result.push(-1);
        	}else {
        		while(dq.peek() <= cur) {
        			dq.poll();
        			if(dq.isEmpty()) break;
        		}
        		
        		if(dq.isEmpty()) {
            		result.push(-1);
            	}else {
            		result.push(dq.peek());
            	}
        	}
        	
        	dq.push(cur);
        }
        
        while(!result.isEmpty()) {
        	sb.append(result.poll()).append(" ");
        }
        
        System.out.println(sb);
	
	}
}
