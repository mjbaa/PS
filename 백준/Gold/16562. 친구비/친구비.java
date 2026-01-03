import java.io.*;
import java.util.*;

public class Main {
    static int n,m,k;
    static int[] parent;
    static int[] cost;
    static int sum = 0;

    static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        if(cost[aRoot] < cost[bRoot]){ // 적은게 부모
            parent[bRoot] = aRoot;

        }else{
            parent[aRoot] = bRoot;
        }
    }

    static boolean isFriend(int a, int b){
        return find(a) == find(b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        cost = new int[n+1];
        for(int i=1;i<=n;i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        Set<Integer> friends = new HashSet<>();

        for(int i=1;i<=n;i++){
            if(!friends.contains(find(i))){
                friends.add(find(i));
                sum += cost[find(i)];
            }
        }

        if(sum <= k) System.out.println(sum);
        else System.out.println("Oh no");



    }
}
