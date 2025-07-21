import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    static int[] dist = new int[100001];
    static int[] parent = new int[100001];

    static int min = Integer.MAX_VALUE;


    static void bfs(int now, int time){
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerLast(new int[]{now, time});
        while (!dq.isEmpty()){
            int[] cur = dq.poll();
            int nowLoc = cur[0];
            int nowTime = cur[1];

            int newTime = nowTime+1;
            if(newTime > min) continue;

            int newLoc = 2 * nowLoc;
            if(0 <= newLoc && newLoc <= 100000){
                if(newTime < dist[newLoc]){
                    dist[newLoc] = newTime;
                    parent[newLoc] = nowLoc;
                    dq.offer(new int[]{newLoc, newTime});
                }
            }

            newLoc = nowLoc + 1;
            if(0 <= newLoc && newLoc <= 100000){
                if(newTime < dist[newLoc]){
                    dist[newLoc] = newTime;
                    parent[newLoc] = nowLoc;
                    dq.offer(new int[]{newLoc, newTime});
                }
            }

            newLoc = nowLoc - 1;
            if(0 <= newLoc && newLoc <= 100000){
                if(newTime < dist[newLoc]){
                    dist[newLoc] = newTime;
                    parent[newLoc] = nowLoc;
                    dq.offer(new int[]{newLoc, newTime});
                }
            }


        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(parent, -1);
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[n] = 0;

        bfs(n, 0);

        List<Integer> list = new ArrayList<>();

        int c = k;
        while(c != -1){
            list.add(c);
            c = parent[c];
        }

        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for(int nc : list){
            sb.append(nc).append(" ");
        }

        System.out.println(dist[k]);
        System.out.println(sb);


    }
}
