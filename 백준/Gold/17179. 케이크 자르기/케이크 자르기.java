import java.util.*;
import java.io.*;

public class Main {
    static int n,m,l;
    static int[] data;

    // 조각 최소 길이가 mid 이상일 때, cut번 이하로 자를 수 있는지를 확인
    static boolean check(int mid, int cut){
        int preCut = 0;
        int cnt = 0;

        for(int i=0;i<data.length;i++){

            // 이전 잘린 위치와 현재 위치의 차이가 mid 이상이면 자를 수 있음
            if(data[i] - preCut >= mid){
                preCut = data[i];
                cnt++;
            }
        }

        // 만들어진 조각 수가 cut + 1 이상이면 자를 수 있는 횟수 내에서 가능하다는 뜻
        //최소 길이 이상 조각이 cut+1 개 이상 있음 -> true
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
        
        //맨 끝도 자를 수 있는걸로 포함시키기
        data[m] = l;

        for(int k=0;k<n;k++){
            int cut = Integer.parseInt(br.readLine());

            int result = bnSearch(1,l, cut);
            sb.append(result).append("\n");
        }

        System.out.println(sb);

    }
}
