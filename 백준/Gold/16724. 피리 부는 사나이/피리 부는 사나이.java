import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static char[][] data;
    static int[] parent;

    static int find(int a){
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        parent[aRoot] = bRoot;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new char[n][m];
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                data[i][j] = line.charAt(j);
            }
        }

        parent = new int[n*m]; //각 칸 번호 : m * i + j
        for(int i=0;i<n*m;i++){
            parent[i] = i;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(data[i][j] == 'U'){
                    union(i*m+j, (i-1)*m+j);
                }else if(data[i][j] == 'D'){
                    union(i*m+j, (i+1)*m+j);

                }else if(data[i][j] == 'L'){
                    union(i*m+j, i*m+j-1);
                }else{
                    union(i*m+j, i*m+j+1);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n*m;i++){
            set.add(find(i));
        }

        System.out.println(set.size());

    }
}
