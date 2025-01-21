class Solution {
    int answer = 0;
    public void dfs(int idx, int sum, int[] numbers, int target){
        if(sum == target && idx == numbers.length){
            answer++;
            return;
        }
        
        if(idx < numbers.length){
            dfs(idx+1, sum + numbers[idx], numbers, target);
            dfs(idx+1,sum-numbers[idx],numbers,target);
        }
        
        return;
        
    }
    
    public int solution(int[] numbers, int target) {
        dfs(0,0,numbers,target);
        return answer;
    }
}
