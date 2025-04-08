import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int n, m;
    static char[][] data;
    
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
    
    static int getNext(int idx) {
        int x = idx / m;
        int y = idx % m;
        char dir = data[x][y];
        
        switch(dir) {
            case 'U': return (x-1) * m + y;
            case 'D': return (x+1) * m + y;
            case 'L': return x * m + (y-1);
            case 'R': return x * m + (y+1);
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parent = new int[n * m];
        data = new char[n][m];
        
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                data[i][j] = line.charAt(j);
            }
        }
        
        for (int i = 0; i < n * m; i++) {
            int next = getNext(i);
            union(i, next);
        }
        
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n * m; i++) {
            roots.add(find(i));
        }
        
        System.out.println(roots.size());
    }
}
