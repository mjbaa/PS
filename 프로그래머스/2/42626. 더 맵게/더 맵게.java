import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> sc = new PriorityQueue<>();
        
        for (int level: scoville) {
            sc.add(level);
        }
        
        int mix = -1;
        int answer = 0;
        while (sc.peek() < K) {
            if (sc.size() < 2) {
                return -1;
            }
            int first = sc.poll();
            int second = sc.poll();
            mix = first + second * 2;
            sc.add(mix);
            answer++;
        }
        
        return answer;
    }
}