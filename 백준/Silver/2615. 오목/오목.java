import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] data = new int[19][19];
        StringTokenizer st;
        for(int i=0;i<19;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<19;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {1,0,-1,1};
        int[] dy = {0,1,1,1};
        //방향 : 아래, 오른쪽, 오른 위 대각, 오른 아래 대각만 체크

        int val;
        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                if(data[i][j]!=0){
                    val = data[i][j];

                    for(int v=0;v<4;v++){//v : 방향설정 index
                        int[] dir = {dx[v],dy[v]};

                        int cnt=0;
                        for(int k=0;k<5;k++){
                            int nx = i+dir[0] * k;
                            int ny = j+dir[1]*k;
                            if(nx>=0 && nx<19 && ny>=0 && ny<19){
                                if(data[nx][ny]==val){
                                    cnt++;
                                }
                            }
                        }
                        if(cnt==5){ //각 방향에서 같은 돌이 5번 등장

                            //앞 확인
                            int nx = i-dir[0];
                            int ny = j-dir[1];
                            if(nx>=0 && nx<19 && ny>=0 && ny<19){
                                if(data[nx][ny]==val){
                                    continue;
                                }
                            }

                            //뒤 확인
                            nx = i+dir[0]*5;
                            ny = j+dir[1]*5;
                            if(nx>=0 && nx<19 && ny>=0 && ny<19){
                                if(data[nx][ny]==val){
                                    continue;
                                }
                            }


                            System.out.println(val);
                            System.out.println((i+1) +" "+(j+1));
                            return;
                        }


                    }
                }
            }
        }
        System.out.println(0);

    }

}
