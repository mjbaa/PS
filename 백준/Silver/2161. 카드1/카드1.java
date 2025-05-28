import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) q.offer(i);

        List<Integer> result = new ArrayList<>();

        while (q.size() > 1) {
            result.add(q.poll());
            q.offer(q.poll());
        }

        result.add(q.poll());

        for (int num : result) System.out.print(num + " ");
    }
}
