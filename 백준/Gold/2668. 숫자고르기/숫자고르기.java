import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] data;
    static List<Integer> result = new ArrayList<>();
    static boolean[] visited;
    static boolean[] finished;
    static List<Integer> path = new ArrayList<>();

    static void dfs(int idx){
        visited[idx] = true;
        path.add(idx);

        int next = data[idx];
        if (!visited[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                int startIdx = path.indexOf(next);
                for (int i = startIdx; i < path.size(); i++) {
                    result.add(path.get(i));
                }
            }
        }

        finished[idx] = true;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        data = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            path.clear();
            dfs(i);
        }
        Set<Integer> set = new HashSet<>(result);
        List<Integer> answer = new ArrayList<>(set);
        Collections.sort(answer);

        System.out.println(answer.size());
        for (int num : answer) {
            System.out.println(num);
        }
    }
}
