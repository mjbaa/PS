import java.io.*;
import java.util.*;

class Solution {
    int[] indegree;
    List<Integer>[] graph;
    Set<Integer>[] set1; 
    Set<Integer>[] set2;
    
    public int solution(int n, int[][] results) {
        indegree = new int[n+1];
        graph = new List[n+1];
        set1 = new HashSet[n+1];
        set2 = new HashSet[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
            set1[i] = new HashSet<>();
            set2[i] = new HashSet<>();
        }
        
        for(int[] result : results){
            int win = result[0];
            int lose = result[1];
            graph[win].add(lose);
            indegree[lose]++;
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(indegree[i] == 0){
                dq.offer(i);
            }
        }
        
        while(!dq.isEmpty()){
            int cur = dq.poll();
            
            for(int next : graph[cur]){
                indegree[next]--;
                
                for(int loser : set1[cur]){
                    set1[next].add(loser);
                }
                set1[next].add(cur);
                
                if(indegree[next] == 0){
                    dq.offer(next);
                }
            }
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j) continue;
                if(set1[j].contains(i)){
                    set2[i].add(j);
                }
            }
        }
        
        
        int answer = 0;
        for(int i=1;i<=n;i++){
            if(set1[i].size() + set2[i].size() == n-1) answer++;
        }
        return answer;
    }
}