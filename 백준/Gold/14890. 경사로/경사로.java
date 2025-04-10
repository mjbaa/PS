import java.io.*;
import java.util.*;

public class Main {
	static int n,x;
	static int[][] data;
	static boolean[][] visited;
	
	static boolean checkRow(int row) {
		visited = new boolean[n][n];
		boolean flag = true;
		int cur = data[row][0];
		
		for(int j=0;j<n;j++) {
			if(cur < data[row][j]) {
				if(j - x < 0 || cur != data[row][j] - 1) {
					flag = false;
					break;
				}
				
				for(int k=j-x;k<j;k++) {
					if(visited[row][k] || data[row][k] != cur ) {
						flag = false;
						break;
					}
					visited[row][k] = true;
				}				
				
			}else if (cur > data[row][j]) {
	            if (cur != data[row][j] + 1 || j + x > n) {
	                flag = false;
	                break;
	            }

	            for (int k = j; k < j + x; k++) {
	                if (visited[row][k] || data[row][k] != data[row][j]) {
	                    flag = false;
	                    break;
	                }
	                visited[row][k] = true;
	            }
	            j += x - 1;
	        }
			
			cur = data[row][j];
		}
		
		return flag;
		
	}
	
	static boolean checkCol(int col) {
		visited = new boolean[n][n];
	    boolean flag = true;
	    int cur = data[0][col];

	    for (int i = 0; i < n; i++) {
	        if (cur < data[i][col]) { 
	            if (i - x < 0 || cur != data[i][col] - 1) {
	                flag = false;
	                break;
	            }

	            for (int k = i - x; k < i; k++) {
	                if (visited[k][col] || data[k][col] != cur) {
	                    flag = false;
	                    break;
	                }
	                visited[k][col] = true;
	            }

	        } else if (cur > data[i][col]) {
	            if (cur != data[i][col] + 1 || i + x > n) {
	                flag = false;
	                break;
	            }

	            for (int k = i; k < i + x; k++) {
	                if (visited[k][col] || data[k][col] != data[i][col]) {
	                    flag = false;
	                    break;
	                }
	                visited[k][col] = true;
	            }
	            i += x - 1;
	        }

	        cur = data[i][col];
	    }

	    return flag;
	}

	
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	x = Integer.parseInt(st.nextToken());
    	
    	data = new int[n][n];
    	
    	
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0;j<n;j++) {
    			data[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int cnt = 0;
    	
    	for(int i = 0;i<n;i++) {
    		if(checkRow(i)) cnt++;
    		
    		if(checkCol(i)) cnt++;
    	}
    	
        System.out.print(cnt);
        
    }
}
