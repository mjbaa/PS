import java.util.*;

class Main {
    static class Node implements Comparable<Node> {
        int x, y, cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static int dijkstra(int[][] cave, int N) {
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dist[0][0] = cave[0][0];
        pq.offer(new Node(0, 0, cave[0][0]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x, y = cur.y, cost = cur.cost;

            if (x == N - 1 && y == N - 1) return cost;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    int newCost = cost + cave[nx][ny];
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.offer(new Node(nx, ny, newCost));
                    }
                }
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = 1;

        while (true) {
            int N = sc.nextInt();
            if (N == 0) break;

            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    cave[i][j] = sc.nextInt();

            int result = dijkstra(cave, N);
            System.out.println("Problem " + testCase + ": " + result);
            testCase++;
        }
        sc.close();
    }
}
