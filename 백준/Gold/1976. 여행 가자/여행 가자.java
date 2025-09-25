import java.io.*;
import java.util.*;

public class Main {

    static int n,m;
    static List<Integer>[] graph;

    static int[] parent;

    static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        parent[aRoot] = bRoot;
    }

    static boolean isConnected(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                int val = Integer.parseInt(st.nextToken());
                if(val == 1) union(i,j);
            }
        }


        st = new StringTokenizer(br.readLine());
        boolean flag = true;
        int first = Integer.parseInt(st.nextToken());
        for(int i=1;i<m;i++){
            int val = Integer.parseInt(st.nextToken());
            if(!isConnected(first,val)){
                flag = false;
                break;
            }
        }

        if(flag) System.out.println("YES");
        else System.out.println("NO");



    }
}