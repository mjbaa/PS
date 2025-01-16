import java.util.Arrays;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] finish = new int[101];
        int day = 0;
        for(int i = 0; i< progresses.length;i++){
            
            while(progresses[i] + (day*speeds[i]) < 100){
                day++;
            }
            finish[day]++;
        }
        
        return Arrays.stream(finish).filter(i -> i!=0).toArray();
        
    }
}