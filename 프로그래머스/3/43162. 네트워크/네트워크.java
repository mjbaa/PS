class Solution {
    int n;
    boolean[] visited;
    int cnt = 0;
    int[][] data;
    
    void dfs(int start){
        for(int i=0;i<n;i++){
            if(visited[i] || i == start) continue;
            if(data[start][i] == 1){
                visited[i] = true;
                dfs(i);
            }
        }
    }
    
    public int solution(int N, int[][] computers) {
        n = N;
        visited = new boolean[n];
        data = computers;
        
        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            visited[i] = true;
            cnt++;
            dfs(i);
        }

        return cnt;
    }
}