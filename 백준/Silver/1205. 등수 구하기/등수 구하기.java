import java.io.*;
import java.util.*;

public class Main {
    static int n, score, p;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        score = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        list.sort((a, b) -> b - a);

        int rank = 1;
        for (int s : list) {
            if (score < s) {
                rank++;
            } else {
                break;
            }
        }

        if (n == p) {
            if (rank > p || list.get(p - 1) >= score) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(rank);
    }


}
