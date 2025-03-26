import java.util.*;
import java.io.*;
 


class job implements Comparable<job>{
	int start;
	int end;
	int w;
	
	job(int start, int end, int w){
		this.start = start;
		this.end = end;
		this.w = w;
	}
	
	public int compareTo(job o) {
		return Integer.compare(this.end, o.end);
	}
}
public class Main {
	static int[] dp;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        StringTokenizer st;
        
        List<job> jobs = new ArrayList<>();
        
        for(int i=1;i<=n;i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = i;
        	int end = start + Integer.parseInt(st.nextToken())-1;
        	int w = Integer.parseInt(st.nextToken());
        	 if (end <= n) { // 유효한 작업만 추가
                 jobs.add(new job(start, end, w));
             }

        }
        

        
        
        Collections.sort(jobs);
        

        
        int jobIdx = 0;
        for(int i=1;i<=n;i++) {
        	
        	dp[i] = dp[i-1];
        	
        	while (jobIdx < jobs.size() && jobs.get(jobIdx).end == i) {
                job j = jobs.get(jobIdx);
                dp[i] = Math.max(dp[i], dp[j.start - 1] + j.w);
                
                jobIdx++;
            }
        	
        }
        System.out.println(dp[n]);
    }
}