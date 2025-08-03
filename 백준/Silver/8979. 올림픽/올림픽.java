import java.io.*;
import java.util.*;

public class Main {
    static class Country implements Comparable<Country> {
        int id;
        int g,s,v;
        Country(int id,int g,int s,int v){
            this.id = id;
            this.g=g;
            this.s=s;
            this.v=v;
        }

        public boolean isDraw(Country c){
            return this.g==c.g && this.s==c.s && this.v==c.v;
        }

        public int compareTo(Country c){
            if(this.g!=c.g){
                return Integer.compare(this.g,c.g);
            }else if(this.s!=c.s){
                return Integer.compare(this.s,c.s);
            }else {
                return Integer.compare(this.v,c.v);
            }
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Country> countries = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            countries.add(new Country(id,g,s,v));
        }

        Collections.sort(countries);

        int idx = 0;
        for(int i=0;i<n;i++){
            if(countries.get(i).id == k){
                idx = i;
            }
        }

        Country target =  countries.get(idx);

        int result = 0;
        for(int i = idx; i>=0; i--){
            if(!countries.get(i).isDraw(target)) break;

            result = i + 1;
        }

        System.out.println(result);


    }




}
