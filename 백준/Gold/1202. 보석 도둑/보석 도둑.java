import java.io.*;
import java.util.*;

public class Main {
	static class Item implements Comparable<Item>{
		int weight;
		int value;
		Item(int weight,int value){
			this.weight = weight;
			this.value = value;
			
		}
		

		public int compareTo(Item o) {
		    return Integer.compare(o.value, this.value); // value 내림차순
		}

	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        Item[] items = new Item[n];
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	items[i] = new Item(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()) );
        }
        
        int[] bags = new int[k];
        for(int i=0;i<k;i++) {
        	bags[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(items,(a,b) -> a.weight - b.weight); // 무게 순 오름차순 정렬
        Arrays.sort(bags); // 가방 저장 공간 순 오름차순 정렬
        
        PriorityQueue<Item> pq = new PriorityQueue<>();
        //pq : 담을 수 있는 item들 가치 기준 내림차순 정렬
        
        int itemIdx = 0;
        long sum = 0;
        for(int i=0;i<k;i++) {
        	int capacity = bags[i];
        	while(itemIdx < n && items[itemIdx].weight <= capacity ) {
        		pq.offer(items[itemIdx]);
        		itemIdx++;
        	}
        	
        	if (!pq.isEmpty()) {
        	    sum += pq.poll().value;
        	}
        }
        
        System.out.println(sum);
        
    }
}
