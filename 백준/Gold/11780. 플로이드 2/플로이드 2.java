import java.io.*;  
import java.util.*;  
  
public class Main {  
	static StringBuilder sb = new StringBuilder();
	static int[][] next;
	
	static void dfs(int from, int to, List<Integer> result) {		
		if(from == to) {
			
			return ;
		}
		
		if(next[from][to] == -1) return;
		
		result.add(from);
		dfs(next[from][to], to, result);
		
	}
	
	
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        
        final int INF = 1000000000;
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        int[][] data = new int[n+1][n+1];
        next = new int[n+1][n+1];
        for(int i=0;i<=n;i++) {
        	Arrays.fill(data[i], INF);
        	data[i][i] = 0;
        	Arrays.fill(next[i], -1);
        }
        
        
        
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	if(w < data[s][d]) {
        		data[s][d] = w;
        		next[s][d] = d;
        	}
        	
        	
        }
        
        for(int k=1;k<=n;k++) {
        	for(int i=1;i<=n;i++) {
            	for(int j=1;j<=n;j++) {
            		if(data[i][k] != INF&& data[k][j] != INF) {
            			if(data[i][k] + data[k][j] < data[i][j]) {
            				data[i][j] = data[i][k] + data[k][j];
            				next[i][j] = next[i][k];
            			}
            		}
            	}
            }
        }
        
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		if(data[i][j] == INF) sb.append(0);
        		else sb.append(data[i][j]);
        		sb.append(" ");
        	}
        	sb.append("\n");
        }
        
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		if(data[i][j] == INF || next[i][j] == -1 || i == j) {
        	            sb.append("0\n");
        	            continue;
        	    }
        		
        		List<Integer> result = new ArrayList<>();
        		dfs(i, j, result);
        		result.add(j);


        		sb.append(result.size()).append(" ");
        		for(int node : result) {
        			sb.append(node).append(" ");
        		}
        		sb.append("\n");
        	}
        	
        }
        
        
        System.out.println(sb);
        
        
    }  
}