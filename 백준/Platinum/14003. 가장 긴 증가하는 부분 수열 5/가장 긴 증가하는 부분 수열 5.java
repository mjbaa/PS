import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        
        int[] data= new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> list = new ArrayList<>();
        int[] pos = new int[n];
        // 각 data[i]가 list의 몇 번째 인덱스에 들어갔는지 기록
        //각 값의 index(data[]의 인덱스) 에 대해 list의 어떤 index에 들어갔었는지
        
        for(int i=0;i<n;i++) {
            int val = data[i];
            int idx = Collections.binarySearch(list, val);
            
            if(idx < 0) {
                idx++;
                idx = -1 * idx;
            }
            
            if(idx == list.size()) {
                list.add(val);
            } else {
                list.set(idx, val);
            }
            
            pos[i] = idx;
        }
        

        int length = list.size();
        System.out.println(length);
        

        Stack<Integer> stack = new Stack<>();
        
        //list| post 맨 뒤부터 탐색
        // data가 list에 해당 위치에 들어간 적이 있음? -> 포함
        int target = length - 1;
        for(int i = n-1; i >= 0; i--) {
            if(pos[i] == target) {
                stack.push(data[i]);
                target--;
            }
        }
        
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
