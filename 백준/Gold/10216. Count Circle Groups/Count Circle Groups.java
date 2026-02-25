import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> ptrs;
    static int n;
    static int[] parent;

    static int find(int x){
        if(x == parent[x]) return x;

        else return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        // a,b 자체 원으로 닿는지 먼저 체크
        int[] A = ptrs.get(a);
        int[] B = ptrs.get(b);

        long dx = (long)A[0] - B[0];
        long dy = (long)A[1] - B[1];
        long dist2 = dx*dx + dy*dy;

        long r = (long)A[2] + B[2];
        long r2 = r*r;

        if(dist2 > r2) return; // 안 닿으면 합치지 않음

        // 닿으면 루트끼리 합치기
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return;
        parent[ra] = rb;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc=0;tc<t;tc++){
            n = Integer.parseInt(br.readLine());
            ptrs = new ArrayList<>();

            parent = new int[n];
            for(int i=0;i<n;i++){
                parent[i] = i;
            }

            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                ptrs.add(new int[]{x,y,r});
            }

            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){
                    union(i,j);                }
            }

            Set<Integer> set = new HashSet<>();
            for(int i=0;i<n;i++){
                set.add(find(i));
            }
            sb.append(set.size()).append("\n");
        }
        System.out.println(sb);

    }
}
