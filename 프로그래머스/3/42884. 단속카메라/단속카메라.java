import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a,b) -> {
            return a[1] - b[1];
        });

        int max = routes[0][1];
        answer ++;
        for(int[] route : routes){
            if(max < route[0]) {
                max = route[1];
                answer++;
            }

        }

        return answer;
    }
}
