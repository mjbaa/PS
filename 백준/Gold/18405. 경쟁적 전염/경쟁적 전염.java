import java.io.*;
import java.util.*;

public class Main {
    static int n, k, s, x, y;
    static int[][] data;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Virus implements Comparable<Virus> {
        int x, y, type;

        public Virus(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Virus o) {
            return Integer.compare(this.type, o.type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        data = new int[n + 1][n + 1];

        List<Virus> virusList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if (data[i][j] != 0) {
                    virusList.add(new Virus(i, j, data[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        // 바이러스 번호순으로 우선 정렬
        Collections.sort(virusList);

        Queue<Virus> q = new LinkedList<>();
        for (Virus v : virusList) {
            q.offer(v);
        }

        int time = 0;
        while (time < s) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Virus virus = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = virus.x + dx[d];
                    int ny = virus.y + dy[d];
                    if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
                    if (data[nx][ny] == 0) {
                        data[nx][ny] = virus.type;
                        q.offer(new Virus(nx, ny, virus.type));
                    }
                }
            }
            time++;
        }

        System.out.println(data[x][y]);
    }
}
