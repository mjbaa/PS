import java.io.*;
import java.util.*;

class Solution {
    class Node{
        String value;
        int cnt;
        Node(String value, int cnt){
            this.value = value;
            this.cnt = cnt;
        }
    }
    
    boolean canChange(String a, String b){
        int cnt = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) != b.charAt(i)) cnt++;
            
            if(cnt >= 2) return false;
        }
        return true;
    }
    
    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        boolean[] visited = new boolean[n];
        
        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(new Node(begin, 0));
        
        int answer = 0;
        
        while(!dq.isEmpty()){
            Node cur = dq.poll();
            if(cur.value.equals(target)) {
                answer = cur.cnt;
                break;
            }
            
            for(int i=0;i<n;i++){
                if(visited[i]) continue;
                if(canChange(cur.value, words[i])){
                    visited[i] = true;
                    dq.offer(new Node(words[i], cur.cnt+1));
                }
            }
            
        }
        return answer;
    }
}