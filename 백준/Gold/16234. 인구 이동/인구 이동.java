import java.util.*;
import java.io.*;


class Main {
	static int n, L,R;
	static int[][] data;
	static boolean[][] visited;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static List<List<int[]>> list = new ArrayList<>();
	
	static int days=0;
	
	static void findUnion() {
		visited = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) {//방문x 국가 발견 -> 새로운 연합
					
					bfs(i,j);//연합에 국가 더하기
				}
			}
		}
	}
	
	static void bfs(int i, int j) {
		List<int[]> union = new ArrayList<>();
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {i,j});
		visited[i][j] = true;
		
		union.add(new int[] {i,j});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			
			
			
			for(int k=0;k<4;k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(nx<0 || ny < 0|| n <= nx || n <= ny) continue;
				if(visited[nx][ny]) continue;
						
				
				int diff = Math.abs(data[x][y] - data[nx][ny]); 
				if(L <= diff && diff <= R) {
					visited[nx][ny] = true; // v 위치!!!
					union.add(new int[] {nx,ny});
					queue.add(new int[] {nx,ny});
				}
			}
		}
		
		list.add(union);
	}
	
	static boolean move() {
		// 이동이 있으면 true : 연합이어도 이동X일수도
		boolean flag = false;
		
		for(int i=0;i<list.size();i++) {// 연합 내에 하나라도 다르면 이동O
			Set<Integer> set = new HashSet<>();
			
			List<int[]> union = list.get(i);
			int sum = 0;
			for(int[] country : union) {
				sum += data[country[0]][country[1]];
				set.add(data[country[0]][country[1]]);
			}
			
			if(set.size() != 1) flag = true;; 
			
			//연합 내 모든 국가 인구수 동일 -> 이동 X
			//한 연합의 한 국가라도 움직이면 이동 O
			
			int val = sum / union.size();
			
			
			for(int[] country : union) {
				data[country[0]][country[1]] = val;
			}
			
		}

		list = new ArrayList<>(); // 해당 일 이동 끝 -> list 초기화
		return flag;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	L = Integer.parseInt(st.nextToken());
    	R = Integer.parseInt(st.nextToken());
    	data = new int[n][n];
    	
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0;j<n;j++) {
    			data[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	while(true) {
    		findUnion();
    		boolean flag = move();
    		if(flag) {//이동 O
    			days++;
    		}else {
    			break;
    		}
    	}
    	System.out.println(days);
    	
    	
//    	for(int i=0;i<n;i++) {
//    		for(int j=0;j<n;j++) {
//    			System.out.print(data[i][j] +" ");
//    		}
//    		System.out.println();
//    	}
//    	System.out.println();
//    	
//    	findUnion();
//    	//	static List<List<int[]>> list = new ArrayList<>();
//    	for(int i=0;i<list.size();i++) {
//    		System.out.println("list : "+i);
//    		for(int[] d : list.get(i)) {
//    			System.out.print(" x : "+d[0]+" y : "+d[1]+ "|");
//    		}
//    		System.out.println();
//    	}
//    	boolean flag = move();
//    	if(flag) days++;
//    	
//    	for(int i=0;i<n;i++) {
//    		for(int j=0;j<n;j++) {
//    			System.out.print(data[i][j] +" ");
//    		}
//    		System.out.println();
//    	}
//    	
//    	System.out.println(days);
    	
    	
    	
    	
    }
    
}