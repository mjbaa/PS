import java.util.*;
import java.io.*;

class Main {
    static int[] parent;
    static int n,m,k;
    static int[] price;

    static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        if(price[aRoot] < price[bRoot]){
            parent[bRoot] = aRoot;
        }else{
            parent[aRoot] = bRoot;
        }
    }

    static boolean isConnected(int a, int b){
        return find(a) == find(b);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        price = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            price[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for(int i=1;i<=n;i++){
            int parent = find(i);
            if(!set.contains(parent)){
                set.add(parent);
                sum += price[parent];
            }
        }

        if(sum > k){
            System.out.println("Oh no");
        }else{
            System.out.println(sum);
        }



    }
}

