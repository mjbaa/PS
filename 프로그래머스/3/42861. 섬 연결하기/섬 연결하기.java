import java.util.*;



class Solution {
    int n;
    int[] parent;
    int sum = 0;
    
    int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b, int w){
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return;
        
        parent[aRoot] = bRoot;
        sum += w;
        return;
    }

    
    public int solution(int N, int[][] costs) {
        n = N;
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
        
        Arrays.sort(costs, (a,b) -> a[2] - b[2]);
        
        for(int[] cost : costs){
            union(cost[0], cost[1], cost[2]);
        }
        return sum;
    }
}