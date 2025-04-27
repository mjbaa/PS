import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Map<Character, Character[]> tree = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.put(parent, new Character[]{left, right});
        }

        preorder('A');
        sb.append("\n");
        inorder('A');
        sb.append("\n");
        postorder('A');

        System.out.println(sb);
    }

    static void preorder(char node) {
        if (node == '.') return;
        sb.append(node);
        preorder(tree.get(node)[0]);
        preorder(tree.get(node)[1]);
    }

    static void inorder(char node) {
        if (node == '.') return;
        inorder(tree.get(node)[0]);
        sb.append(node);
        inorder(tree.get(node)[1]);
    }

    static void postorder(char node) {
        if (node == '.') return;
        postorder(tree.get(node)[0]);
        postorder(tree.get(node)[1]);
        sb.append(node);
    }
}
