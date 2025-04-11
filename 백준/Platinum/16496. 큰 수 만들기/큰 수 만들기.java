import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] data;
    
    static class Val implements Comparable<Val>{
    	String val;
    	
    	Val(String val){
    		this.val = val;
    	}
    	
    	public int compareTo(Val o) {
    	    String first = this.val + o.val;
    	    String second = o.val + this.val;
    	    return second.compareTo(first); 
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        PriorityQueue<Val> pq = new PriorityQueue<>();
        
        boolean flag = true;
        for(int i=0;i<n;i++) {
        	String s = st.nextToken();
        	pq.add(new Val(s));
        	
        	if(Integer.parseInt(s) != 0) {
        		flag = false;
        	}
        }
        
        while(!pq.isEmpty()) {
        	sb.append(pq.poll().val);
        }
        if(flag) {
        	System.out.println(0);
        }else {
        	System.out.println(sb);
        }
        
        
        
    }
}
