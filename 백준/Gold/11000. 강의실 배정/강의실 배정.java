import java.io.*;
import java.util.*;

class Class implements Comparable<Class>{
	int start;
	int end;
	
	Class(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public int compareTo(Class o) {
		if(this.start != o.start) {
			return Integer.compare(this.start, o.start);
		}else {
			return Integer.compare(this.end, o.end);
		}
	}
}

public class Main {
	
	
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Class> classes = new PriorityQueue<>();
        
        StringTokenizer st;
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	
        	Class c = new Class(start,end);
        	classes.offer(c);
        }
        
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        
        while(!classes.isEmpty()) {
        	Class cur = classes.poll();
        	
        	if(rooms.isEmpty() || rooms.peek() <= cur.start) {
        		rooms.poll();
        	}
        	
        	rooms.offer(cur.end);
        }
        
        System.out.println(rooms.size());
        
        
        
    }
    

}
