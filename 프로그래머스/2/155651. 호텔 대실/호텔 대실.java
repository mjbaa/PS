import java.util.*;

class Solution {
    public int solution(String[][] book_time) {

        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        
        for (String[] time : book_time) {
            int start = convert(time[0]);
            int end = convert(time[1]) + 10; 
            if (!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll();
            }
            
            rooms.offer(end);
        }
        
        return rooms.size();
    }
    

    private int convert(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
