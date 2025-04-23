import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] tree;
    static int target;
    static int root;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree[parent].add(i);
            }
        }

        target = Integer.parseInt(br.readLine());

        if (target == root) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(cnt);
        }
    }

    static void dfs(int current) {
        if (current == target) return;

        boolean hasChild = false;
        for (int child : tree[current]) {
            if (child != target) {
                hasChild = true;
                dfs(child);
            }
        }

        if (!hasChild) {
            cnt++;
        }
    }
}
