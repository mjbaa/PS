import java.util.*;
import java.io.*;
 
class State{
	int loc;
	int cnt;
	State(int loc, int cnt){
		this.loc = loc;
		this.cnt = cnt;
	}
}

public class Main {
	static int n,k;
	static int min = Integer.MAX_VALUE;
	static int cnt=0;
	static boolean visited[] = new boolean[100001];
	
	static void bfs() {
		Deque<State> deq = new ArrayDeque<>();
		deq.add(new State(n,0));
		visited[n] = true;
		
		while(!deq.isEmpty()) {
			State cur = deq.poll();
			if(cur.loc == k) min = Math.min(min, cur.cnt);
			
			if(0<=cur.loc*2&& cur.loc*2 <=100000 && !visited[cur.loc*2]){
				visited[cur.loc*2] = true;
				deq.offer(new State(cur.loc*2,cur.cnt));
			}
			
			if(0<=cur.loc-1&& cur.loc-1 <=100000 && !visited[cur.loc - 1]) {
				visited[cur.loc-1] = true;
				deq.offer(new State(cur.loc-1,cur.cnt+1));
			}
			
			if(0<=cur.loc+1&& cur.loc+1 <=100000 && !visited[cur.loc+1] ) {
				visited[cur.loc+1] = true;
				deq.offer(new State(cur.loc+1,cur.cnt+1));
			}
			
			

		}
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        
        bfs();
        
        System.out.println(min);
    }
}