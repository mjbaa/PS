import java.io.*;
import java.util.*;

public class Main {
	static class State{
		int val;
		StringBuilder sBuilder;
		
		State(int val){
			this.val = val;
			this.sBuilder = new StringBuilder();
		}
	}
	
	static boolean[] visited;
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= t;tc++) {
        	visited = new boolean[10000];
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	
        	visited[n1] = true;
        	Deque<State> dq = new ArrayDeque<>();
        	dq.offer(new State(n1));
        	while(!dq.isEmpty()) {
        		State cur = dq.poll();
        		int now = cur.val;
        		if(now == n2) {
        			sb.append(cur.sBuilder.toString()).append("\n");
        			break;
        		}
        		
        		int D = (2 * now) % 10000; 
        		int S = now == 0 ? 9999 : now - 1; 
        		int L = (now % 1000) * 10 + now / 1000;
        		int R = (now % 10) * 1000 + now / 10; 
        		
        		int[] dslr = {D,S,L,R};
        		String[] cmd = {"D","S","L","R"};
        		
        		for(int i=0;i<4;i++) {
        			if(visited[dslr[i]]) continue;
        			visited[dslr[i]] = true;
        			
        			State next = new State(dslr[i]);
        			
        			next.sBuilder = new StringBuilder(cur.sBuilder);
        			next.sBuilder.append(cmd[i]);

        			
        			dq.offer(next);
        		}
        	}
        	
        }
        System.out.println(sb);
    
    }   
}