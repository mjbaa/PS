import java.util.*;

class Solution {
    long limit;
    int[] diffs;
    int[] times;
    int min = Integer.MAX_VALUE;
    

    
    boolean check(int val){
        long sum = 0;
        for(int i=0;i<times.length;i++){
            int cur_diff = diffs[i];
            int cur_time = times[i];
            
            //int prev_time = times[i-1];
            if(val >= cur_diff) sum += cur_time;
            else sum += (long) (cur_diff - val) * (cur_time + times[i-1]) + cur_time;
            
            if(sum > limit) return false;
        }
        return true;
    }
    
    void bnSearch(int l, int r){
        int left = l;
        int right = r;
        
        while(left<=right){
            int mid = left +(right-left)/2;
            if(check(mid)){
                min = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
            
        }
    }
    
    public int solution(int[] d, int[] t, long l) {
        limit = l;
        diffs = d;
        times = t;
        
        bnSearch(1, 100000);
        
        return min;
    }
}