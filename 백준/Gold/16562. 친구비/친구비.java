import java.io.*;
import java.util.*;

public class Main {
    static int n,m,k;
    static int[] cost;
    static int[] parent;
    static List<Integer>[] graph;

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        if(cost[aRoot] < cost[bRoot]){
            parent[bRoot] = aRoot;
        }else{
            parent[aRoot] = bRoot;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }

        cost = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i= 1; i<=n;i++){
            for(int friend : graph[i]){
                union(friend,i);
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=1;i<=n;i++){
            set.add(find(i));
        }

        int sum = 0;
        for(int p : set){
            sum += cost[p];
        }

        if(sum > k){
            System.out.println("Oh no");
        }else{
            System.out.println(sum);
        }


    }
}
