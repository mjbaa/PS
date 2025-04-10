import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Tline = br.readLine();
        
        String line = br.readLine();

        char[] pattern = line.toCharArray();
        int pLen = pattern.length;
        int[] pf = new int[pLen];
        for(int i=1,j=0;i<pLen;i++) {
        	while(j>0 && pattern[i] != pattern[j]) {
        		j = pf[j-1];
        	}
        	
        	if(pattern[i] == pattern[j]) {
        		pf[i] = j+1;
        		j++;
        	}else {
        		pf[i] = 0;
        	}
        }
        

        int cnt = 0; // 총 등장 개수
        List<Integer> list = new ArrayList<>();
        
        char[] text = Tline.toCharArray();
    	int tLen = text.length;
    	
    	
    	for(int i=0,j=0;i<tLen;i++) {
    		while(j>0 && text[i] != pattern[j]) {
    			j = pf[j-1];
    		}
    		
    		if(text[i] == pattern[j]) {
    			if(j == pLen-1) {
    				cnt++;
    				list.add(i-j+1);
    				j = pf[j];
    			}else {
    				j++;
    			}
    		}
    	}
        
        System.out.println(cnt);
        for(int i=0;i<list.size();i++) {
        	if(i != list.size()-1) {
        		System.out.print(list.get(i)+" ");
        	}else {
        		System.out.print(list.get(i));
        	}
        }

        
    }
}
