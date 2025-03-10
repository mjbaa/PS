import java.util.*;
import java.io.*;



public class Main {
    static int[][] eggs;//내구도, 무게
    static int n;
    static int max;
    
    static void dfs(int idx, int cnt) {
    	
    	if(idx >= n) {
    		max = Math.max(cnt, max);
    		return;
    	}
    	 if (eggs[idx][0] <= 0) {
             dfs(idx + 1, cnt);
             return;
         }
    	boolean hit = false;
    	for(int i=0;i<n;i++) {
    		if(i == idx) continue;
    		if(eggs[i][0] <= 0) continue;
    		
    		hit = true;
    		eggs[idx][0] -= eggs[i][1];
    		eggs[i][0] -= eggs[idx][1];
    		
    		int d = 0;
            if (eggs[idx][0] <= 0) d++;
            if (eggs[i][0] <= 0) d++;
    		
    		
    		
    		dfs(idx+1, cnt+d);
    		eggs[idx][0] += eggs[i][1];
    		eggs[i][0] += eggs[idx][1];
    	}
    	
    	if(!hit) {
    		dfs(idx + 1, cnt);
    	}
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	eggs = new int[n][2];
    	StringTokenizer st;
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());
    		eggs[i][0] = Integer.parseInt(st.nextToken());
    		eggs[i][1] = Integer.parseInt(st.nextToken());
    	}
    	max = 0;
    	dfs(0,0);
    	System.out.println(max);
    	
    	
    }
}
