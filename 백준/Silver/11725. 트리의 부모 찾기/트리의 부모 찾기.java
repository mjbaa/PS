import java.io.*;  
import java.util.*;  
  
public class Main {  
  
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        int n = Integer.parseInt(br.readLine());  
 
        int[] parent = new int[n + 1];  

        ArrayList<Integer>[] graph = new ArrayList[n + 1];  
        for (int i = 1; i <= n; i++) {  
        	graph[i] = new ArrayList<>();  
        }  

        boolean[] visited = new boolean[n + 1];  
        StringTokenizer st;  
        for (int i = 1; i < n; i++) {  
            st = new StringTokenizer(br.readLine());  
            int a = Integer.parseInt(st.nextToken());  
            int b = Integer.parseInt(st.nextToken());  
            graph[a].add(b);  
            graph[b].add(a);  
        }  
  

        Deque<Integer> dq = new ArrayDeque();  

        dq.add(1);  
        visited[1] = true;  
 
        while (!dq.isEmpty()) {  
            int cur = dq.poll();  

            for (int next : graph[cur]) {  

                if (visited[next]) {  
                    continue;  
                }   
                visited[next] = true;  
                dq.add(next);   
                parent[next] = cur;  
            }  
        }  
        for (int i = 2; i <= n; i++) {  
            System.out.println(parent[i]);  
        }  
    }  
}