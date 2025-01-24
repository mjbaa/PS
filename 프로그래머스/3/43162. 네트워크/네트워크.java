import java.util.ArrayList;

class Solution {
    boolean[] visited;
    int answer = 0;
    
    public void dfs(int n, int start, int[][] computers) {
        visited[start] = true;
        for(int i = 0; i < n; i++) {
            if (i == start) continue;
            if (!visited[i] && computers[start][i] == 1) {
                dfs(n, i, computers);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(n, i, computers);
                answer++;
            }
        }
        
        return answer;
    }
}