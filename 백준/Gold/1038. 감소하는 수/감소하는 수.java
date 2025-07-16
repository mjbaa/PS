import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Long> numbers = new ArrayList<>();

    static void dfs(long num, int limit){
        numbers.add(num);
        for(int i = 0; i < limit; i++){
            dfs(10*num + i, i);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=0;i<=9;i++){
            dfs(i,i);
        }

        Collections.sort(numbers);

        if(n >= numbers.size()){
            System.out.println(-1);
        }else{
            System.out.println(numbers.get(n));
        }

    }


}
