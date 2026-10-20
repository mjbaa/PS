import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int no, weight;
    Node(int no, int weight){
        this.no = no;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Node o){
        return Integer.compare(this.weight, o.weight);
    }
}

class Solution {
    int[] dist;
    List<Node>[] graph;
    int n;
    
    
    public void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start,0));
        
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dist[cur.no] < cur.weight) continue;
            
            for(Node next : graph[cur.no]){
                int newDist = cur.weight + next.weight;
                if(newDist < dist[next.no]){
                    pq.offer(new Node(next.no, newDist));
                    dist[next.no] = newDist;
                }
            }
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        n = N;
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        graph = new List[n+1];
        for(int i=1;i<=n;i++) graph[i] = new ArrayList<>();
        
        for(int[] r : road){
            int a = r[0];
            int b = r[1];
            int w = r[2];
            graph[a].add(new Node(b,w));
            graph[b].add(new Node(a,w));
        }
        
        dijkstra(1);
        
        int cnt = 0;
        for(int i=1;i<=n;i++){
            if(dist[i] <= K) cnt++;
        }

        return cnt;
    }
}