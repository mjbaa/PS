import java.io.*;
import java.util.*;

class Solution {
    public int toSeconds(String data){
        StringTokenizer st = new StringTokenizer(data,":");
        String m = st.nextToken();
        String s = st.nextToken();
        
        return 60 * Integer.parseInt(m) + Integer.parseInt(s);
    }
    
    public String toMiniute(int data){
        StringBuilder sb = new StringBuilder();
        
        int m = data/60;
        int s = data % 60;
        
        if(m < 10){
            sb.append(0);
        }
        sb.append(m).append(":");
        if(s < 10){
            sb.append(0);
        }
        sb.append(s);
        
        return sb.toString();
    }
    
    int Vlen, Pos, Opstart,Opend;
    
    
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        Vlen = toSeconds(video_len);
        Pos = toSeconds(pos);
        Opstart = toSeconds(op_start);
        Opend = toSeconds(op_end);
        
        if(Opstart <= Pos && Pos <= Opend){
                Pos = Opend;
        }
        
        for(String cmd : commands){

            
            if(cmd.equals("prev")) Pos -= 10;
            else{
                Pos += 10;
            }
            if(Pos < 0) Pos = 0;
            if(Pos > Vlen) Pos = Vlen;
            
            if(Opstart <= Pos && Pos <= Opend){
                Pos = Opend;
            }
        }

        
        String answer = toMiniute(Pos);
        return answer;
    }
}