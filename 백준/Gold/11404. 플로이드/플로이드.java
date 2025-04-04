import java.io.*;  
import java.util.*;  
  
public class Main {  
	

    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        final int INF = 1000000000;
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] data = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(data[i], INF);
            data[i][i] = 0;
        }
        
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken())-1;
        	int d = Integer.parseInt(st.nextToken())-1;
        	int w = Integer.parseInt(st.nextToken());
        	
        	data[s][d] = Math.min(data[s][d], w);

        }
        
        
        for(int k=0;k<n;k++) {
        	for(int i=0;i<n;i++) {
        		for(int j=0;j<n;j++) {
        			if(data[i][k] != INF && data[k][j] != INF) {
        				data[i][j] = Math.min(data[i][j], data[i][k] + data[k][j]);
        			}//else : data[i][j] 그대로
        			
        		}
        	}
        }
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		if (data[i][j] == INF) {
        		    sb.append("0");
        		} else {
        		    sb.append(data[i][j]);
        		}
        		sb.append(" ");
        		
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
    }  
}