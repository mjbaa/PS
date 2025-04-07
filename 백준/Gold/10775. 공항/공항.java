import java.io.*;
import java.util.*;

public class Main {
	static int g,p;
	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
		
	}
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        
        parent = new int[g+1];
        for(int i=0;i<=g;i++) {
        	parent[i] = i;
        }
        
        int[] plane = new int[p];
        for(int i=0;i<p;i++) {
        	plane[i] = Integer.parseInt(br.readLine());
        }
        
        int cnt = 0;
        for(int i=0;i<p;i++) {
        	int gate = find(plane[i]);
        	if(gate ==0) break;
        	
        	cnt++;
        	union(find(plane[i])-1,find(plane[i]));
        }
        
        System.out.println(cnt);
    }
    
    
}
