import java.io.*;
import java.util.*;

class line implements Comparable<line>{
	long start;
	long end;
	line(long start, long end){
		this.start = start;
		this.end = end;
	}
	
	public int compareTo(line o) {
		if(this.start != o.start) {
			return Long.compare(this.start, o.start);
		}else {
			return Long.compare(this.end, o.end);
		}
	}
}


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<line> data = new PriorityQueue<>();
        
        StringTokenizer st;
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	data.offer(new line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        
        long preEnd = Long.MIN_VALUE;
        long sum = 0;
        while(!data.isEmpty()) {
        	line cur = data.poll();
        	
        	if(cur.start > preEnd) {
        		sum += cur.end - cur.start;
        	}else if (cur.end > preEnd) {
                sum += cur.end - preEnd;
            }
            
            preEnd = Math.max(preEnd, cur.end);
        	
        }
        
        
        System.out.println(sum);
        
    }
    

}
