class Solution {
    boolean[] visited;
    int n;
    int[][] data;
    void dfs(int start){
        for(int i=0;i<n;i++){
            if(data[start][i] == 1 && !visited[i]){
                visited[i] = true;
                dfs(i);
            }
        }
    }
    
    public int solution(int N, int[][] computers) {
        n = N;
        visited = new boolean[n];
        data = computers;
        
        int cnt = 0;
        for(int i=0;i<n;i++){
            if(!visited[i]) {
                cnt++;
                dfs(i);
            }
        }
        
        int answer = cnt;
        return answer;
    }
}