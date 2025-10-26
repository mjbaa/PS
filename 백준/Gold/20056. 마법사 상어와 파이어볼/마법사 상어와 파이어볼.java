import java.util.*;
import java.io.*;

class Main {
    static class Fire{
        int r,c,m,d,s;
        Fire(int r, int c, int m,int d,int s){
            this.r=r;
            this.c=c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
    static int N,M,K;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    static List<Fire> fires = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());



        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fires.add(new Fire(r,c,m,d,s));
        }

        for(int tc = 0; tc<K; tc++){
            //이동
            Map<String, Deque<Fire>> map = new HashMap<>();
            Set<String> keys = new HashSet<>();
            List<Fire> temp = new ArrayList<>();
            for(Fire fire : fires){

                int step = fire.s % N;

                int nr = ((fire.r + step * dx[fire.d]) % N + N) % N;
                int nc = ((fire.c + step * dy[fire.d]) % N + N) % N;

                Fire moved = new Fire(nr, nc, fire.m, fire.d, fire.s);
                temp.add(moved);

                String key = nr + " " + nc;
                if(!map.containsKey(key)){
                    map.put(key,new ArrayDeque<>());
                    keys.add(key);
                }

                map.get(key).add(moved);
            }

            fires = temp;

            //합치기, 분할하기
            temp = new ArrayList<>();

            for(String key : keys){
                Deque<Fire> dq = map.get(key);
                Set<Integer> dir = new HashSet<>(); // 0 : 홀수, 1 : 짝수
                int sumM = 0;
                int sumS = 0;
                int size = dq.size();
                if(size == 1){
                    temp.add(dq.poll());
                    continue;
                }

                while(!dq.isEmpty()){
                    Fire cur = dq.poll();
                    dir.add(cur.d%2);

                    sumM += cur.m;
                    sumS += cur.s;
                }

                st = new StringTokenizer(key);
                int nr = Integer.parseInt(st.nextToken());
                int nc = Integer.parseInt(st.nextToken());

                int newM = sumM / 5;
                if(newM == 0) continue;
                int newS = sumS / size;

                if(dir.size() == 1){
                    for(int i=0;i<7;i+=2){
                        temp.add(new Fire(nr,nc,newM,i,newS));
                    }
                }else{
                    for(int i=1;i<8;i+=2){
                        temp.add(new Fire(nr,nc,newM,i,newS));
                    }

                }
            }
            fires = temp;
        }

        int sumM = 0;
        for(Fire fire : fires){
            sumM += fire.m;
        }

        System.out.println(sumM);


    }
}

