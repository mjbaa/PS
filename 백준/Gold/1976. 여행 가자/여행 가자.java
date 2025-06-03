import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] parent;

    static int find(int a){
        if(a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    static boolean isConnected(int a, int b){
        return find(a) == find(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                int val = Integer.parseInt(st.nextToken());
                if(val == 1){
                    union(i,j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        boolean allConnected = true;
        int pre = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()){
            int cur = Integer.parseInt(st.nextToken());
            if(!isConnected(pre,cur)){
                allConnected = false;
                break;
            }
        }

        if(allConnected) System.out.println("YES");
        else System.out.println("NO");


    }
}
