import java.util.*;

class Solution {

    
    static class Pos {
        int r, c;
        Pos(int r, int c) { this.r = r; this.c = c; }
        @Override public int hashCode() { return Objects.hash(r, c); }
        @Override public boolean equals(Object o) {
            Pos p = (Pos) o; return r == p.r && c == p.c;
        }
    }

    public int solution(int[][] points, int[][] routes) {

        
        Pos[] P = new Pos[points.length + 1];
        for (int i = 0; i < points.length; i++)
            P[i + 1] = new Pos(points[i][0], points[i][1]);

        List<List<Pos>> paths = new ArrayList<>();
        int maxLen = 0;

        for (int[] rts : routes) {
            List<Pos> path = new ArrayList<>();
            path.add(new Pos(P[rts[0]].r, P[rts[0]].c));

            for (int k = 0; k < rts.length - 1; k++) {

                Pos cur = P[rts[k]];
                Pos dst = P[rts[k + 1]];
                int cr = cur.r, cc = cur.c;

                while (cr != dst.r) {
                    cr += cr < dst.r ? 1 : -1;
                    path.add(new Pos(cr, cc));
                }

                while (cc != dst.c) {
                    cc += cc < dst.c ? 1 : -1;
                    path.add(new Pos(cr, cc));
                }
            }
            paths.add(path);
            maxLen = Math.max(maxLen, path.size());
        }

        
        int danger = 0;
        for (int t = 0; t < maxLen; t++) {
            Map<Pos, Integer> freq = new HashMap<>();
            for (List<Pos> path : paths) {
                if (t >= path.size()) continue;
                Pos p = path.get(t);
                freq.put(p, freq.getOrDefault(p, 0) + 1);
                if (freq.get(p) == 2) danger++;
            }
        }
        return danger;
    }
}
