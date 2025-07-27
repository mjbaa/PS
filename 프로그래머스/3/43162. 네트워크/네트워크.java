class Solution {
    int n;
    boolean[] visited;
    int[][] graph;
    
    void dfs(int start){
        
        for(int i=0;i<n;i++){
            if(graph[start][i] == 1 && !visited[i]){
                visited[i] = true;
                dfs(i);
            }
        }
    }
    public int solution(int N, int[][] computers) {
        n = N;
        visited = new boolean[n];
        graph = computers;
        
        int cnt = 0;
        for(int i=0;i<n;i++){
            if(!visited[i]) {
                visited[i] = true;
                cnt++;
                dfs(i);
            }
        }
        
        
        return cnt;
    }
}