import java.io.*;
import java.util.*;

public class Main {
    static class Number implements Comparable<Number>{
    	String val;
    	Number(String val){
    		this.val = val;
    	}
    
    	public int compareTo(Number o) {
    	    String first = this.val + o.val;
    	    String second = o.val + this.val;
    	    return second.compareTo(first); 
    	}

    }
	
	
	static int k,n;
	
	
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        List<Number> list = new ArrayList<>();
        
        boolean allZero = true;
        
        String max = "0";
        for(int i=0;i<k;i++) {
        	String val = br.readLine();
        	list.add(new Number(val));
        	
        	
        	if(val.length() > max.length()) {
        		max = val;
        	}else if(val.length() == max.length() && (val + max).compareTo(max + val) > 0) {
        		max = val;
        	}
        	
        	if(!val.equals("0")) allZero = false;
        }
        
        
        for(int i=0;i<n-k;i++) {
        	list.add(new Number(max));
        }
        Collections.sort(list);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++) {
        	sb.append(list.get(i).val);
        }
        
        if(allZero) System.out.println(0);
        else System.out.println(sb);
        
        
    }
}
