import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] parent;
    static int result = 0;

    static int find(int a){
        if(a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
    }

    static boolean isConnected(int a, int b){
        return find(a) == find(b);
    }

    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(isConnected(a,b)) {
                result = i;
                break;
            }else{
                union(a,b);
            }
        }

        System.out.println(result);
    }

}
