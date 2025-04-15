import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
        int from, to, weight;
        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
	static int n,m;
	static int[][] data;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int idx;

	static List<Node>[] graph;
	static int[] cost;
	
	static List<Node> edges = new ArrayList<>();
    static int[] parent;
	
	
	static boolean notRange(int x,int y) {
		return (x<0 || y<0 || x>=n || y>=m);
	}
	
	static void bfs(int idx,int x,int y) {
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {x,y});
		
		visited[x][y] = true;
		data[x][y] = idx;

		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for(int f=0;f<4;f++) {
				int nx = cx+dx[f];
				int ny = cy+dy[f];
				
				if(notRange(nx,ny)) continue;
				if(data[nx][ny] == 0) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				data[nx][ny] = idx;
				
				dq.offer(new int[] {nx, ny});
			}
		}
		

		
	}
	
	static void findBridges() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (data[i][j] == 0) continue;
                int fromIsland = data[i][j];

                for (int d = 0; d < 4; d++) {
                    int len = 0;
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    while (!notRange(nx, ny)) {
                        if (data[nx][ny] == fromIsland) break; // 같은 섬

                        if (data[nx][ny] != 0) {
                            if (len >= 2) {
                                int toIsland = data[nx][ny];
                                edges.add(new Node(fromIsland, toIsland, len));
                            }
                            break;
                        }

                        nx += dx[d];
                        ny += dy[d];
                        len++;
                    }
                }
            }
        }
    }
	
	static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    static int kruskal() {
        Collections.sort(edges);
        
        parent = new int[idx + 1];
        for (int i = 1; i <= idx; i++) parent[i] = i;


        int result = 0;
        int connected = 0;

        for (Node edge : edges) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                connected++;
            }
        }

        return (connected == idx -1 ) ? result : -1;
    }

	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        data = new int[n][m];
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<m;j++) {
        		data[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        visited = new boolean[n][m];
        
        idx = 1;
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		if(data[i][j] ==1  &&!visited[i][j]) {
        			bfs(idx,i,j);
        			idx++;
        		}
        	}
        }
        
        
        idx --;
        
        findBridges();
        int answer = kruskal();
        System.out.println(answer);
    }
}
