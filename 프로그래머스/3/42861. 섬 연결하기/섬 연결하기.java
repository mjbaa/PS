import java.io.*;
import java.util.*;

class Solution {
    static class Edge implements Comparable<Edge>{
        int a , b, weight;
        Edge(int a, int b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    static int[] parent;
    static List<Edge> edges;
    
    public int find(int x){
        if(x == parent[x]) return x;
        
        return parent[x] = find(parent[x]);
    }
    
    public void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return;
        
        parent[bRoot] = aRoot;
        return;
    }
    
    public boolean isConnected(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return true;
        else return false;
    }
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
        edges = new ArrayList<>(n);
        for(int[] cost : costs){
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        
        Collections.sort(edges);
        
        int answer = 0;
        
        for(Edge e : edges){
            int a = e.a;
            int b = e.b;
            int weight = e.weight;
            
            if(isConnected(a,b)) continue;
            
            union(a,b);
            answer += weight;
        }
        
        
        return answer;
    }
} 