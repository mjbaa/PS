public class Solution {
    public int solution(String name) {
        int move = name.length() - 1; 
        int answer = 0;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            int nextIndex = i + 1;

            while (nextIndex < name.length() && name.charAt(nextIndex) == 'A') {
                nextIndex++;
            }


            move = Math.min(move, i + name.length() - nextIndex + Math.min(i, name.length() - nextIndex));
        }

        return answer + move;
    }
}
