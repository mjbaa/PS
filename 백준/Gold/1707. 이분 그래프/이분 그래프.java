import java.util.*;
import java.io.*;

public class Main {
    static int v , e;
    static List<Integer>[] graph;
    static int[] visited;
    
    static boolean binaryG = true;
    
    static boolean bfs(int idx) {
    	Deque<Integer> dq = new ArrayDeque<>();
    	dq.offer(idx);
    	int val = 1;
    	visited[idx] = val;
    	
    	while(!dq.isEmpty()) {
    		int cur = dq.poll();
    		for(int node : graph[cur]) {
    			if(visited[node] == 0) {
        			visited[node] = 3 - visited[cur]; 
        			dq.offer(node);
    			}else if (visited[node] == visited[cur]) {
                    return false; // 충돌
                }
    			

    		}
    	}
    	return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc<t;tc++) {
        	binaryG = true;
        	
        	st = new StringTokenizer(br.readLine());
        	int v = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	graph = new List[v+1]; //1based
        	visited = new int[v+1];
        	
        	for(int i=1;i<=v;i++) {
        		graph[i] = new LinkedList<>();
        	}
        	
        	for(int i=0;i<e;i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken());
        		
        		graph[s].add(d);
        		graph[d].add(s);
        	}
        	
        	boolean flag = true;
        	
        	for(int i=1; i<=v; i++){
                if(visited[i] == 0){ 
                    if(!bfs(i)){ //충돌 발생
                        flag = false;
                        break;
                    }
                }
            }
            
            if(flag) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        	
        }
        System.out.println(sb);
    }
}
