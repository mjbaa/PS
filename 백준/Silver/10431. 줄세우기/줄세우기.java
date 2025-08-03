import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int p = Integer.parseInt(br.readLine());

        for(int t = 0; t< p; t++){
            st = new StringTokenizer(br.readLine());
            int tc = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();

            int sum = 0;
            for(int i=0;i<20;i++){
                int cur = Integer.parseInt(st.nextToken());
                if(list.isEmpty()) list.add(cur);
                else{
                    boolean found = false;
                    for(int j=0;j<list.size();j++){
                        int friend = list.get(j);
                        if(friend > cur){
                            sum += list.size() - j;
                            list.add(j, cur);
                            found = true;
                            break;
                        }
                    }

                    if(!found) list.add(cur);
                }
            }

            sb.append(tc).append(" ").append(sum).append("\n");
        }


        System.out.println(sb);
    }




}
