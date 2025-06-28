class Solution {

    static int cnt = 0;

    double h_d;
    double m_d;
    double s_d;


    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int acc = 0;
        calculateDegree(h1,m1,s1);
        int start = h1*3600 + m1*60 + s1;
        int end = h2*3600 + m2*60 + s2;

        while(start < end){
            start++;
            acc += timePast();
        }

        calculateDegree(h1,m1,s1);
        if(Double.compare(h_d, m_d) == 0 || Double.compare(m_d,s_d) == 0) acc++;
        calculateDegree(h2,m2,s2);
        if(Double.compare(h_d, m_d) == 0 || Double.compare(m_d,s_d) == 0) acc++;

        return acc;
    }


    public void calculateDegree(int h1, int m1, int s1) {
        this.h_d = (h1%12)*30 + m1*(0.5) + s1*0.0083;
        this.m_d = (m1*6) + (s1*0.1);
        this.s_d = (s1*6);
    }


    public int timePast() {
        int ans = 0;
        double prevH = this.h_d;
        double prevM = this.m_d;
        double prevS = this.s_d;

        this.h_d += 0.0083333333333333;
        this.m_d += 0.1;
        this.s_d += 6;

        if(Double.compare(prevH, prevS) > 0 && Double.compare(h_d,s_d) <= 0) ans++;
        if(Double.compare(prevM, prevS) > 0 && Double.compare(m_d,s_d) <= 0) ans++;
        if((Double.compare(prevH, prevM) > 0 && Double.compare(prevH,prevS) > 0) 
           && (Double.compare(h_d, m_d) <= 0 && Double.compare(h_d, s_d) <= 0)) ans--;

        if(this.h_d >=360) this.h_d %= 360; 
        if(this.m_d >=360) this.m_d %= 360;
        if(this.s_d >=360) this.s_d %= 360;

        return ans;
    }
}