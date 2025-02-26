
import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int min = Integer.MAX_VALUE;
	
	static List<List<Integer>> graph = new ArrayList<>();

	
	static boolean selected[];
	static int[] population;
	static void divide(int cnt) {
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();
		int suma = 0;
		int sumb = 0;
		if(cnt == n) {
			for(int i=0;i<n;i++) {
				if(selected[i]) {
					a.add(i);
					suma += population[i];
				}
				else {
					b.add(i);
					sumb += population[i];
				}
			}
			 
			if (a.isEmpty() || b.isEmpty()) return; 
			
			if(isConnected(a) && isConnected(b)) {
				min = Math.min(Math.abs(suma-sumb), min);
			}
			
			return;
		}
		
		
		selected[cnt] = true;
		divide(cnt+1);
		selected[cnt] = false;
		divide(cnt +1);
	}
	
	static boolean isConnected(List<Integer> x) {
		//x : 2,5,7,9...
		int start = x.get(0);
		Deque<Integer> dq = new ArrayDeque<>();
		dq.push(start);
		boolean visited[] = new boolean[n];
		visited[start] = true;
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			for(int next : graph.get(cur)) {
				if(x.contains(next) && !visited[next]) {
					visited[next] = true;
					dq.push(next);
				}
			}
		}
		
		for(int node : x) {
			if(!visited[node]) return false;
		}
		
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		selected = new boolean[n];
		population = new int[n];
		
		for(int i=0;i<n;i++) {
			graph.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j=0;j<m;j++) {
				int val = Integer.parseInt(st.nextToken())-1;
				graph.get(i).add(val);
				graph.get(val).add(i);
			}
		}
		
		divide(0);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
