import java.io.*;
import java.util.*;

public class Main {
    static class Item implements Comparable<Item>{
        int weight, value;
        Item(int weight, int value){
            this.weight = weight;
            this.value = value;
        }

        public int compareTo(Item o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n,k;
    static List<Item> items;
    static List<Integer> bags;
    static boolean[] used;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        items = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items.add(new Item(weight,value));
        }
        Collections.sort(items); // 무게 기반 오름차순

        bags = new ArrayList<>(n);
        for(int i=0;i<k;i++){
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags); // 용량 기반 오름차순

        PriorityQueue<Item> pq = new PriorityQueue<>((a,b) -> b.value - a.value); // 가치 기반 내림차순
        long sum = 0;
        int idx = 0;
        for(int i=0;i<k;i++){
            int size = bags.get(i);

            while(idx < n && items.get(idx).weight <= size){
                pq.add(items.get(idx));
                idx++;
            }

            if(!pq.isEmpty()){
                sum += pq.poll().value;
            }
        }

        System.out.println(sum);

    }


}
