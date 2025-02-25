import java.util.*;
import java.io.*;


class Main {
	static int n;
	static int m;
	

	static int[] temp;
	
	static void comb(int idx, int start) {
		if(idx == m) {
			for(int i=0;i<m;i++) {
				System.out.print(temp[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=start;i<n;i++) {

			temp[idx] = i+1;
			comb(idx+1, i+1);

		}
		
	}
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	

    	temp = new int[m];
    	
    	comb(0,0);
    	
    	  		
    }
    
}