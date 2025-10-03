import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    static int[] data = {1,2,3,5,7,9};


    static boolean isPrime(int x){
        if(x < 2) return false;

        for(int i=2;i * i <= x; i++){
            if(x % i == 0) return false;
        }
        return true;
    }

    static void dfs(int cnt, int cur){

        if(cnt == n){
            sb.append(cur).append('\n');
            return;
        }

        for(int d : data){
            int next = 10*cur + d;
            if(isPrime(next)){
                dfs(cnt+1, next);
            }
        }
    }

    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());



        dfs(0,0);

        System.out.println(sb);

    }

}
