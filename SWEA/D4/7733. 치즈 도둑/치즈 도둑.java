import java.util.*;
import java.io.*;

class Solution
{	
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    
    public static void eat(int[][] data, int day ){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                if(data[i][j] == day){                  
                    data[i][j] = 0;
                }
            }
        }
        return;
    }
	
    public static void move(int o, int p, int[][] data, boolean[][] visited){
        visited[o][p] = true;
        for(int i=0;i<4;i++){
         	int nx = o + dx[i];
            int ny = p + dy[i];
            if(0<=nx && nx < data.length && 0<= ny && ny < data.length){ //범위 내
                if(data[nx][ny] != 0 && !visited[nx][ny]){ // 0아님(안먹힘) + 아직 안지나감
                    move(nx,ny,data,visited); //이동
                }
                
            }
        }
        return; //이동할 좌표 없음
    }
    
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
            
        int T = Integer.parseInt(br.readLine());
        for(int i=1;i<T+1;i++){
         	int N = Integer.parseInt(br.readLine());
            int[][] data = new int[N][N];
            
            for(int j=0;j<N;j++){ //data 채우기
             	st = new StringTokenizer(br.readLine());
                for(int k=0;k<N;k++){
                 	data[j][k] = Integer.parseInt(st.nextToken());   
                }
            }
            
            int max=1;
            
           	for(int j=1;j<=100;j++){ // j번째 일
                boolean[][] visited = new boolean[N][N];

                eat(data,j); // j번째 일에 대해 치즈 먹기
                
                int cnt = 0; // 덩어리 수
                
                for(int o=0;o<N;o++){ //  각 day에 대해 모든 좌표 돌면서 덩어리 찾기
                    for(int p=0;p<N;p++){
                        if(data[o][p] != 0){ //안먹힌 좌표
                            if(visited[o][p]){ // 이미 지나간 좌표
                            	continue;
                        	}else{//아직 지나가지 않은 좌표
                                move(o,p,data,visited);//지나가기
                                cnt += 1;//\move 끝나면 덩이리수 +1
                        	}
                        }
                    }
                }
                if(cnt > max){
                    max = cnt;
            	}
            }
            System.out.println("#" + i+" "+max);
        }
        
		
	}
}