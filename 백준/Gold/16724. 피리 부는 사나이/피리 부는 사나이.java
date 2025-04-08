import java.io.*;
import java.util.*;

public class Main {
	static int[][][] parent;
	
	static int[] find(int[] a) {
		if(Arrays.equals(parent[a[0]][a[1]], a)) return a;
		
		return parent[a[0]][a[1]] = find(parent[a[0]][a[1]]);
	}
	
	static boolean union(int[] a, int[] b) {
		int[] aRoot = find(a);
		int[] bRoot = find(b);
		
		if(Arrays.equals(aRoot,bRoot)) return false;
		
		parent[bRoot[0]][bRoot[1]] = aRoot;
		return true;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        parent = new int[n][m][2]; // n,m에 대해 부모의 좌표 저장
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		parent[i][j] = new int[] {i,j};
        	}
        }
        
        char[][] data = new char[n][m];
        for(int i=0;i<n;i++) {
        	String line = br.readLine();
        	for(int j=0;j<m;j++) {
        		data[i][j] = line.charAt(j);
        	}
        }
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		int[] next = new int[] {i,j};
        		switch(data[i][j]){
        			case'U': 
        				next[0] = i-1;
        				break;
        			case'D':	
        				next[0] = i+1;
        				break;
        			case'L':	
        				next[1] = j-1;
        				break;
        			case'R':	
        				next[1] = j+1;
        				break;
        		}
        		union(new int[] {i,j}, next);
        	}
        }
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		find(new int[] {i,j});
        	}
        }
        
        Set<String> roots = new HashSet<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                int[] root = find(new int[] {i,j});
                roots.add(root[0] + "," + root[1]);
            }
        }
        System.out.println(roots.size());

    
    }
}
