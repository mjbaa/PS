import java.io.*;

public class Main {

    static int getLength(String a, String b) {
        int length = Math.min(a.length(), b.length());
        int idx = 0;
        while (idx < length && a.charAt(idx) == b.charAt(idx)) {
            idx++;
        }
        return idx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            strings[i] = br.readLine();
        }

        int max = -1;
        String a = "";
        String b = "";


        for (int i = 0; i < n - 1; i++) {
            String cur = strings[i];
//            if(cur.length() <= max) continue;
            for (int j = i+1; j < n; j++) {
                String next = strings[j];
//                if(next.length() <= max) continue;
                int length = getLength(cur, next);

                if(length > max){
                    max = length;
                    a = cur;
                    b = next;
                }
            }
        }
        System.out.println(a);
        System.out.println(b);
    }


}