import java.io.*;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair>{
        int value, cnt;
        Pair(int value, int cnt){
            this.value = value;
            this.cnt = cnt;
        }

        public int compareTo(Pair o){
            if(this.cnt != o.cnt){
                return Integer.compare(this.cnt, o.cnt);
            }else{
                return Integer.compare(this.value, o.value);
            }
        }
    }

    static int r,c,k;
    static int[][] data = new int[101][101];
    static int rLen = 3, cLen = 3;


    static void sortRow(){
        for(int i=1;i<=rLen;i++){
            Map<Integer, Integer> map = new HashMap<>();
            for(int j=1;j<=100;j++){
                int value = data[i][j];
                if(value ==0) continue;

                if(map.containsKey(value)){
                    map.put(value, map.get(value)+1);
                }else{
                    map.put(value, 1);
                }
            }

            List<Pair> list = new ArrayList<>(map.size());
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                int key = entry.getKey();
                int value = entry.getValue();
                list.add(new Pair(key,value));
            }

            Collections.sort(list);

            int last = 0;
            for(int j=1;j<=list.size();j++){
                int keyIdx = j*2-1;
                if(keyIdx > 100) break;
                cLen = Math.max(cLen,keyIdx);
                data[i][keyIdx] = list.get(j-1).value;
                last = keyIdx;

                int cntIdx = j*2;
                if(cntIdx > 100) break;
                cLen = Math.max(cLen,cntIdx);
                data[i][cntIdx] = list.get(j-1).cnt;
                last = cntIdx;
            }

            for(int j = last+1;j<=100;j++){
                data[i][j] = 0;
            }

        }
    }

    static void sortCol(){
        for(int j = 1; j<=cLen;j++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= 100; i++) {
                int value = data[i][j];
                if (value == 0) continue;

                if (map.containsKey(value)) {
                    map.put(value, map.get(value) + 1);
                } else {
                    map.put(value, 1);
                }
            }

            List<Pair> list = new ArrayList<>(map.size());
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                list.add(new Pair(key, value));
            }

            Collections.sort(list);

            int last = 0;
            for (int i = 1; i <= list.size(); i++) {
                int keyIdx = i * 2 - 1;
                if (keyIdx > 100) break;
                rLen = Math.max(rLen, keyIdx);
                data[keyIdx][j] = list.get(i - 1).value;
                last = keyIdx;

                int cntIdx = i * 2;
                if (cntIdx > 100) break;
                rLen = Math.max(rLen, cntIdx);
                data[cntIdx][j] = list.get(i - 1).cnt;
                last = cntIdx;
            }

            for (int i = last + 1; i <= 100; i++) {
                data[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=3;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(cnt <= 100){
            if(data[r][c] == k){
                System.out.println(cnt);
                return;
            }

            if(rLen >= cLen) sortRow();
            else sortCol();

            cnt++;
        }

        System.out.println(-1);
    }




}
