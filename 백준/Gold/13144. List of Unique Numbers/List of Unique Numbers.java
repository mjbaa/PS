import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[100001];
        int left = 0;
        long cnt = 0;

        for(int right = 0; right < n; right++){
            while(visited[data[right]]){
                visited[data[left]] = false;
                left++;
            }
            visited[data[right]] = true;
            cnt += right - left + 1;
        }

        System.out.println(cnt);
    }




}
