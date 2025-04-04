import java.io.*;  
import java.util.*;  
  
public class Main {  
	

    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] data = new int[n][n];
        boolean[][] can = new boolean[n][n];
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken())-1;
        	int d = Integer.parseInt(st.nextToken())-1;
        	int w = Integer.parseInt(st.nextToken());
        	can[s][d] = true;
        	
        	if(data[s][d] != 0) data[s][d] = Math.min(data[s][d], w);
        	else data[s][d] = w;
        }
        
        for(int i=0;i<n;i++) {
        	data[i][i] = 0;
        	can[i][i] = true;
        }
        
        for(int k=0;k<n;k++) {
        	for(int i=0;i<n;i++) {
        		for(int j=0;j<n;j++) {
        			if(can[i][j]) {
        				if(can[i][k] && can[k][j]) {
        					data[i][j] = Math.min(data[i][j], data[i][k] + data[k][j]);
        				}//else : data[i][j] 그대로
        			}else {
        				if(can[i][k] && can[k][j]) {
        					data[i][j] = data[i][k] + data[k][j];
        					can[i][j] = true;
        				}
        			}
        		}
        	}
        }
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		sb.append(data[i][j]).append(" ");
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
    }  
}