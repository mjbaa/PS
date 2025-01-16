import java.util.*;

class Solution {
    boolean solution(String s) {
        
        char[] charArr = s.toCharArray();
        Stack<Character> st = new Stack<>();
        
        for(int i=0;i<charArr.length;i++){
            if(charArr[i] == '('){
                st.push('(');
            }else{
                if(st.isEmpty()){
                    return false;
                }else{
                    st.pop();
                }
            }
        }
        
        if(st.isEmpty()){
            return true;
        }
        
        return false;
    }
}