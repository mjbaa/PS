import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> data = new HashMap<>();
    List<String> result = new LinkedList<>();

    public String[] solution(String[][] tickets) {

        for (String[] ticket : tickets) {
            if(!data.containsKey(ticket[0])){
                data.put(ticket[0], new PriorityQueue<>());
            }
            data.get(ticket[0]).offer(ticket[1]);
        }

        dfs("ICN");

        return result.toArray(new String[0]);
    }

    void dfs(String cur) {
        PriorityQueue<String> pq = data.get(cur);

        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }

        result.add(0, cur);
    }
}
