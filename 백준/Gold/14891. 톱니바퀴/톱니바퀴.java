import java.io.*;
import java.util.*;


public class Main {
    /*
      ptr : 12시 idx
      오른쪽 : (ptr + 2 ) % 8;
      왼쪽 : (ptr + 6) % 8;
      시계방향 회전 : 1보다 크면 ptr - 1, 0이면 ptr +8, -1
      반시계방향 회전 : (ptr + 1) % 8
    */
    static class Wheel{
        int[] state = new int[8];
        int ptr;

        Wheel(int[] state, int ptr){
            this.state=state;
            this.ptr=ptr;
        }
    }

    static Wheel[] wheels =  new Wheel[4];

    //dir = true -> 시계방향
    static void move(int widx, boolean dir, boolean goLeft, boolean goRight){
        Wheel cur = wheels[widx];

        if(goLeft && widx != 0){
            Wheel left = wheels[widx-1];

            if(left.state[(left.ptr + 2) % 8] != cur.state[(cur.ptr + 6) % 8]){ // left의 오른쪽, right의 왼쪽 다른지 비교
                move(widx-1, !dir, true,false);
            }
        }

        if(goRight && widx != 3){
            Wheel right = wheels[widx+1];
            if(right.state[(right.ptr+6) % 8] != cur.state[(cur.ptr+2) % 8]){
                move(widx+1, !dir, false, true);
            }
        }

        if(dir){
            if(cur.ptr == 0) cur.ptr += 8;
            cur.ptr -= 1;
        }else{
            cur.ptr = (cur.ptr + 1) % 8;
        }

    }
    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0;i<4;i++){
            String line = br.readLine();
            int[] state = new int[8];
            for(int j=0;j<8;j++){
                state[j]=line.charAt(j) - '0';
            }
            wheels[i]=new Wheel(state,0);
        }

        int k = Integer.parseInt(br.readLine());
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int widx =  Integer.parseInt(st.nextToken()) -1;
            int dir =  Integer.parseInt(st.nextToken());

            if(dir == 1){
                move(widx,true,true,true);
            }else{
                move(widx,false,true,true);
            }


        }

        int result = 0;
        for(int i=0;i<4;i++){
            Wheel cur = wheels[i];
            int val = 1;
            if(cur.state[cur.ptr] == 1){
                for(int j = 0; j< i;j++){
                    val *= 2;
                }
            }
            if(cur.state[cur.ptr] == 1) result += val;
        }

        System.out.println(result);
    }
}
