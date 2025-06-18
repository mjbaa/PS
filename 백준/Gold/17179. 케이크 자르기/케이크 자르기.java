import java.util.*;
import java.io.*;

public class Main {
    static int n,m,l;
    static int[] data;

    static boolean check(int mid, int cut){
        int preCut = 0;
        int cnt = 0;

        for(int i=0;i<data.length;i++){
            if(data[i] - preCut >= mid){
                preCut = data[i];
                cnt++;
            }
        }

        return cnt >= cut+1;

    }


    static int bnSearch(int left,int right, int cut){
        int answer = 0;

        while(left <= right){
            int mid = left + (right-left)/2;

            if(check(mid, cut)){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid -1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        data = new int[m+1];



        for (int i = 0; i < m; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        data[m] = l;

        for(int k=0;k<n;k++){
            int cut = Integer.parseInt(br.readLine());

            int result = bnSearch(1,l, cut);
            sb.append(result).append("\n");
        }

        System.out.println(sb);

    }
}
