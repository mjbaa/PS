import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        int Tlen = text.length;
        
        char[] pA = br.readLine().toCharArray();
        char[] pB = br.readLine().toCharArray();
        
        int Alen = pA.length;
        int[] fA = new int[Alen];
        for(int i=1,j=0;i<Alen;i++) {
        	while(j > 0 && pA[i] != pA[j]) {
        		j = fA[j-1];
        	}
        	
        	if(pA[i] == pA[j]) {
        		fA[i] = j+1;
        		j++;
        	}
        }
        
        ArrayList<Integer> alist = new ArrayList<>();
        for(int i=0,j=0;i<Tlen;i++) {
        	while(j > 0 && text[i] != pA[j]) {
        		j = fA[j-1];
        	}
        	
        	if(text[i] == pA[j]) {
        		if(j == Alen-1) {
        			alist.add(i-j);
        			
        			j = fA[j];
        		}else {
        			j++;
        		}
        	}
        }
        
        int Blen = pB.length;
        int[] fB = new int[Blen];
        for(int i=1,j=0;i<Blen;i++) {
        	while(j>0 && pB[i] != pB[j]) {
        		j = fB[j-1];
        	}
        	
        	if(pB[i] == pB[j]) {
        		fB[i] = j+1;
        		j++;
        	}
        }
        
        ArrayList<Integer> blist = new ArrayList<>();
        for(int i=0,j=0;i<Tlen;i++) {
        	while(j > 0 && text[i] != pB[j]) {
        		j = fB[j-1];
        	}
        	
        	if(text[i] == pB[j]) {
        		if(j == Blen-1) {
        			blist.add(i-j);
        			
        			j = fB[j];
        		}else {
        			j++;
        		}
        	}
        }
        Set<String> set = new HashSet<>();
        
        
        String t = new String(text);
        int cnt = 0;
        for(int a : alist) {
        	for(int b : blist) {
        		if(a<=b) {
        			String sub = t.substring(a, b+Blen);
        			if (sub.startsWith(new String(pA)) && sub.endsWith(new String(pB))) {
        			    set.add(sub);
        			}
//        			set.add(t.substring(a,b+Blen));
        		}
        	}
        }
        
//        for(String str : set) {
//        	System.out.println(str);
//        }
        
        System.out.println(set.size());

        
        
    }
}
