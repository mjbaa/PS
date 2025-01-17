import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
       Queue<Integer> bridge = new LinkedList<>();
        int answer = 0; 
        int weightSum = 0; 
        int index = 0; 

        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        while (index < truck_weights.length) {
            answer++; 
            weightSum -= bridge.poll();

            if (weightSum + truck_weights[index] <= weight) {
                bridge.add(truck_weights[index]);
                weightSum += truck_weights[index];
                index++;
            } else {
                bridge.add(0);
            }
        }
        answer += bridge_length;

        return answer;
    }
}