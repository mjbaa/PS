import java.io.*;
import java.util.*;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] n = br.readLine().toCharArray();

        Arrays.sort(n);

        for (int i = 0; i < n.length / 2; i++) {
            char temp = n[i];
            n[i] = n[n.length - 1 - i];
            n[n.length - 1 - i] = temp;
        }
        
        System.out.println(new String(n));

    }
}
