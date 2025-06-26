import java.io.*;
import java.util.*;
class Solution {
    
    public boolean check(int level, int[] diffs, int[] times, long limit){
        long sum = 0;
        
        int time_prev = 0;
        
        for(int i=0;i<times.length;i++){
            if(diffs[i] <= level) sum += times[i];
            else{
                sum += (diffs[i] - level) * (times[i] + time_prev) + times[i];
            }
            
            if(sum > limit) return false;
            
            time_prev = times[i];
        }
        
        return true;
    }
    
    public int bnSearch(int left, int right, int[] diffs, int[] times, long limit){
        int result = 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            
            
            if(check(mid,diffs,times,limit)){
                result = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        
        
        int answer = bnSearch(1,100000, diffs, times, limit);
        return answer;
    }
}