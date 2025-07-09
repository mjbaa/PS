import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int[] parent;

    static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }

        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }


        for(int i=0;i<n-2;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=1;i<=n;i++){
            List<Integer> friends = graph[i];
            for(int friend : friends){
                union(i,friend);
            }
        }

        for(int i=2;i<=n;i++){
            if(union(1,i)){
                System.out.println(1 + " " + i);
                break;
            }
        }
    }
}
