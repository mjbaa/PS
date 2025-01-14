import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int getMinCost(int startrow, int startcol, String[] chessboard) {
        String[] board = {"WBWBWBWB", "BWBWBWBW"}; 

        int whiteVerCount = 0; 

        for(int i = 0; i < 8; i++){ 
            int row = startrow + i; 
            for(int j = 0; j < 8; j++){ 
                int col = startcol + j;

                if(chessboard[row].charAt(col) != board[row%2].charAt(j)){
                    whiteVerCount++;
                }
            }
        }

        return Math.min(whiteVerCount, 64-whiteVerCount);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        String[] chessboard = new String[N];

        for(int i = 0; i < N; i++){
            chessboard[i] = br.readLine(); 
        }

        br.close();

        int count = Integer.MAX_VALUE; 
        for(int i = 0; i <= N-8; i++){
            for(int j = 0; j <= M-8; j++){
                int resultCount = getMinCost(i, j, chessboard);

                if(count > resultCount){
                    count = resultCount;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }
}