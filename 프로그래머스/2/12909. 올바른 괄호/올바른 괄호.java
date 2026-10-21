import java.util.*;


class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack();
        
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if(cur == '('){
                stack.push('(');
            }else{
                if(stack.empty()) return false;
                else stack.pop();
            }
        }

        if(!stack.isEmpty()) return false;
        return true;
    }
}