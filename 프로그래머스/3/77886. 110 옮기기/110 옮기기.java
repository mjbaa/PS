import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] data) {
        String[] answer = new String[data.length];
        
        for(int i=0;i<data.length;i++){
            String s = data[i];
            StringBuilder sb = new StringBuilder();
            
            //110 다 뺌
            int cnt = 0;
            for(int j=0;j<s.length();j++){
                sb.append(s.charAt(j));
                
                int len = sb.length();
                if(len >= 3 
                   && sb.charAt(len-3) == '1'
                   && sb.charAt(len-2) == '1'
                   && sb.charAt(len-1) == '0'){
                    sb.delete(len-3, len);
                    cnt++;
                }
            }
            
            String rest = sb.toString();
            int idx = rest.lastIndexOf("0");
            
            StringBuilder result = new StringBuilder();
            
            if(idx == -1){
                for(int j=0;j<cnt;j++){
                    result.append("110");
                }
                result.append(rest);
            }else{
                result.append(rest.substring(0, idx+1));
                for(int j=0;j<cnt;j++){
                    result.append("110");
                }
                result.append(rest.substring(idx+1));
            }
            
            answer[i] = result.toString();
        }

        return answer;
    }
}