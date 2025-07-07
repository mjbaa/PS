import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static boolean[] dangers;
    static List<List<Integer>> parties = new ArrayList<>();

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;

        if (dangers[aRoot]) {
            parent[bRoot] = aRoot;
        } else if (dangers[bRoot]) {
            parent[aRoot] = bRoot;
        } else {
            parent[bRoot] = aRoot;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        dangers = new boolean[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            int t = Integer.parseInt(st.nextToken());
            dangers[t] = true;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());

            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < partySize; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }

            for (int j = 1; j < party.size(); j++) {
                union(party.get(0), party.get(j));
            }

            parties.add(party);
        }

        for (int i = 1; i <= n; i++) {
            if (dangers[i]) {
                dangers[find(i)] = true;
            }
        }

        int answer = 0;
        for (List<Integer> party : parties) {
            int root = find(party.get(0));
            if (!dangers[root]) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
