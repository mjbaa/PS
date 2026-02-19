import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] parent;
    static boolean[] isCycle;
    static int[] rank;

    static int find(int a){
        if(a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        if(rank[aRoot] <  rank[bRoot]){
            parent[aRoot] = bRoot;
            isCycle[bRoot] |= isCycle[aRoot];
        }else if(rank[aRoot] > rank[bRoot]){
            parent[bRoot] = aRoot;
            isCycle[aRoot] |= isCycle[bRoot];
        }else{
            rank[bRoot] += 1;
            parent[aRoot] = bRoot;
            isCycle[bRoot] |= isCycle[aRoot];
        }

    }

    static boolean isConnected(int a, int b){
        return find(a) == find(b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            if((n = Integer.parseInt(st.nextToken())) == 0) break;
            m = Integer.parseInt(st.nextToken());

            parent = new int[n+1];
            for(int i=1;i<=n;i++) {
                parent[i] = i;
            }

            isCycle = new boolean[n+1];
            rank = new int[n+1];

            for(int i=1;i<=m;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(isConnected(a,b)) {
                    isCycle[find(a)] = true;
                }else{
                    union(a,b);
                }

            }

            Set<Integer> set = new HashSet<>();
            for(int i=1;i<=n;i++) {
                set.add(find(i));
            }
            int cnt = 0;
            for(int root : set){
                if(!isCycle[root]) cnt++;
            }

            if(cnt == 0){
                sb.append("Case ").append(tc).append(": No trees.").append("\n");
            }else if(cnt == 1){
                sb.append("Case ").append(tc).append(": There is one tree.").append("\n");
            }else{
                sb.append("Case ").append(tc).append(": A forest of ").append(cnt).append(" trees.").append("\n");
            }

            tc++;
        }
        System.out.println(sb);
    }
}
