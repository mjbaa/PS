import java.io.*;
import java.util.*;

public class Main {
    static int f;
    static Map<String,String> parent;
    static Map<String, Integer> size;

    static String find(String a){
        if(parent.get(a).equals(a)) return a;

        String root = find(parent.get(a));
        parent.put(a, root);
        return root;
    }

    static void union(String a, String b){
        String aRoot = find(a);
        String bRoot = find(b);

        if(aRoot.equals(bRoot)) return;

        parent.put(aRoot, bRoot);
        size.put(bRoot, size.get(aRoot) + size.get(bRoot));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++){
            parent = new HashMap<>();
            size = new HashMap<>();

            f = Integer.parseInt(br.readLine());
            for(int i=0; i<f; i++){
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if(!parent.containsKey(a)){
                    parent.put(a,a);
                }
                if(!size.containsKey(a)){
                    size.put(a,1);
                }

                if(!parent.containsKey(b)){
                    parent.put(b,b);
                }
                if(!size.containsKey(b)){
                    size.put(b,1);
                }

                union(a,b);
                sb.append(size.get(find(a))).append('\n');

            }

        }
        System.out.println(sb);
    }
}
