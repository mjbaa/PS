import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] parent;
    static List<int[]> edges = new ArrayList<>();

    static int find(int x){
        if(x==parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[aRoot] = bRoot;

        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }


        for(int i=0;i<n-2;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.add(new int[]{a,b});
        }

        for(int[] edge : edges){
            union(edge[0],edge[1]);
        }

        int result = -1;
        for(int i=2;i<=n;i++){
            if(union(1,i)) {
                result = i;
                break;
            }
        }


        System.out.println(1 + " " + result);
    }




}
