import java.util.*;
import java.io.*;


public class Main {

    static class Edge{
        int u, v, weight;
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static int[] subRoot;
    static int[] rank;




    static int find(int x){ // subroot 찾기
        if(subRoot[x] != x){
            subRoot[x] = find(subRoot[x]);
        }

        return subRoot[x];
    }

    static void union(int x, int y){ // 트리 합침 : rank 작은 걸 큰 트리 아래에 붙임
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
            if(rank[rootX] > rank[rootY]){
                subRoot[rootY] = rootX;
            }else if(rank[rootX] < rank[rootY]){
                subRoot[rootX] = rootY;
            }else{
                subRoot[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    static int kruskal(int n, List<Edge> edges){
        Collections.sort(edges, (a,b) -> a.weight - b.weight);

        subRoot = new int[n+1];
        rank = new int[n+1];

        for (int i = 1; i <= n; i++) { // 초기 : 각각 트리. 자신이 자신의 root, 각각 rank = 0
            subRoot[i] = i;
            rank[i] = 0;
        }

        int totalWeight = 0;

        for(Edge edge : edges){
            if(find(edge.u) != find(edge.v)){ // subRoot가 다름 -> 합치고 가중치 더하기
                union(edge.u, edge.v);
                totalWeight += edge.weight;
            }
        }

        return totalWeight;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            Edge edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            edges.add(edge);
        }

        System.out.println(kruskal(V, edges));
    }
}