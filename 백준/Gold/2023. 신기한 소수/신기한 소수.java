import java.util.*;
import java.io.*;


class Main {
	static int N;
	static int[] firstDigit = {2,3,5,7};
	static int[] nextDigit = {1,3,5,7,9};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        for (int digit : firstDigit) {
            findPrime(digit, 1); 
        }
    }


    static void findPrime(int num, int length) {
        if (length == N) { 
            System.out.println(num);
            return;
        }


        for (int next : nextDigit) {
            int newNum = num * 10 + next;
            if (isPrime(newNum)) {
                findPrime(newNum, length + 1); 
            }
        }
    }


    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}