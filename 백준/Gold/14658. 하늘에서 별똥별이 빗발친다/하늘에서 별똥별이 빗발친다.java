import java.io.*;
import java.util.*;

public class Main {
    static class Star implements Comparable<Star> {
        int x,y;
        Star(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Star o) {
            if(this.x != o.x){
                return Integer.compare(this.x, o.x);
            }else{
                return Integer.compare(this.y, o.y);
            }

        }
    }
    static int n,m,l,k;

    static long max = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        List<Star> stars = new ArrayList<>(k);
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Star(x,y));
        }

        Collections.sort(stars);

        for(int i=0;i<k;i++){
            int cnt = 1;
            Star cur = stars.get(i);

            for(int j=i+1;j<k;j++){
                Star near = stars.get(j);
                if(near.x > cur.x + l) break;
                if(cur.y <= near.y && near.y <= cur.y+l) cnt++;

            }
            max = Math.max(max,cnt);
        }

        for (int i = 0; i < k; i++) {
            Star cur = stars.get(i);

            for (int t = 0; t < k; t++) {
                int y0 = stars.get(t).y;

                int cnt = 0;
                for (int j = i; j < k; j++) {
                    Star near = stars.get(j);
                    if (near.x > cur.x + l) break;
                    if (y0 <= near.y && near.y <= y0 + l) cnt++;
                }
                max = Math.max(max, cnt);
            }
        }



        System.out.println(k - max);
    }


}
