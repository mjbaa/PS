import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int s : scoville){
            pq.offer(s);
        }
        
        int cnt = 0;
        while(pq.peek() < K){
            if(pq.size() < 2) return -1;
            int cur = pq.poll();
            int next = pq.poll();
            
            cnt++;
            pq.offer(cur + 2*next); 
        }
        
        return cnt;
    }
}