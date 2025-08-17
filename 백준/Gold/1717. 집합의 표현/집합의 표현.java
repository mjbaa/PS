import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] parent;

    static int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(flag == 0){
                union(a,b);
            }else{
                if(isSame(a,b)){
                    sb.append("YES\n");
                }else{
                    sb.append("NO\n");
                }
            }


        }
        System.out.println(sb);

    }
}
