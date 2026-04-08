import java.io.*;
import java.util.*;

class Solution {
    int n,t,m;
    String[] data;
    PriorityQueue<String> pq = new PriorityQueue<>();
    
    String addTime(String base, int diff){
        String[] a = base.split(":");
        int h = Integer.parseInt(a[0]);
        int min = Integer.parseInt(a[1]);

        int total = h * 60 + min + diff;

        int rh = total / 60;
        int rm = total % 60;

        return String.format("%02d:%02d", rh, rm);
    }
    
    
    public String solution(int N, int T, int M, String[] timetable) {
        String answer = "";
        n = N; t = T; m = M; data = timetable;
        
        for(String s : timetable){
            pq.offer(s);
        }
        
        String cur = "09:00";
        for(int i=1;i<=n;i++){
            if(i != 1){
            cur = addTime(cur,t); // 현재 버스 도착시간                
            }
            
            
            if(i != n){ // 마지막 버스 전
                for(int j = 0; j < m; j++){ //최대 m명 태우기
                    if(pq.isEmpty()) break;
                    if(pq.peek().compareTo(cur) > 0) break;
                    pq.poll();
                }
            }else{ // 마지막 버스
                int cnt = 0;
                String last = "";
                
                for(int j = 0; j < m; j++){
                    if(pq.isEmpty()) break;
                    if(pq.peek().compareTo(cur) > 0) break;
                    last = pq.poll();
                    cnt++;
                }

                if(cnt < m){
                    return cur;
                }else{
                    return addTime(last, -1);
                }
            }
        }
        
        return answer;
    }
}