import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer, Integer> data = new HashMap<>();
        for(int num : nums){
            if(data.containsKey(num)){
                data.put(num, data.get(num) + 1);
            }else{
                data.put(num, 1);
            }
        }
        if(data.size() > nums.length/2){
            return nums.length/2;
        }else{
            return data.size();
        }
        
    }
}