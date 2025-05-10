import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static List<Integer>[] friends;

    static boolean[] visited;
    static boolean isFive = false;

    static void dfs(int pa,int cnt){
        if(cnt == 4){
            isFive = true;
            return;
        }

        for(int friend : friends[pa]){
                if(!visited[friend]){
                    visited[friend] = true;
                    dfs(friend,cnt+1);
                    visited[friend] = false;
                }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];

        friends = new List[n];
        for (int i = 0; i < n; i++) {
            friends[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<m;i++)   {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        for(int i=0;i<n;i++){
            visited[i] = true;
            dfs(i,0);
            visited[i] = false;

            if(isFive){ break;}
        }

        if(isFive){System.out.println(1);}
        else System.out.println(0);
    }
}
