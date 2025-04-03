import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t =0;t<T;t++ ) {
			String cmd = br.readLine();
			
			int n = Integer.parseInt(br.readLine());
			String l = br.readLine();
			String line = l.substring(1, l.length() - 1);
			st = new StringTokenizer(line,",");
			
			List<Integer> list = new ArrayList<>(line.length()/2+1);
			
			for(int i=0;i<n;i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			boolean flag = true;
			
			boolean reverse = false;
			for(int i=0;i<cmd.length();i++) {
				char c = cmd.charAt(i);
				if(c=='R') {
					reverse = !reverse;
				}else {
					if(list.size()==0) {
						sb.append("error\n");
						flag = false;
						break;
					}
					
					if(!reverse) {
						list.remove(0);
					}else {
						list.remove(list.size()-1);
					}
				}
			}
			
			if(flag) {
				sb.append("[");
				if(!reverse) {
					for(int i=0;i<list.size();i++) {
						if(i != list.size()-1) {
							sb.append(list.get(i)+",");
						}else {
							sb.append(list.get(i));
						}
					}
				}else {
					for(int i=list.size()-1;i>=0;i--) {
						if(i != 0) {
							sb.append(list.get(i)+",");
						}else {
							sb.append(list.get(i));
						}
					}
				}
				sb.append("]\n");
			}
			
		}
		System.out.println(sb);
    }
	
	
		
	
}
