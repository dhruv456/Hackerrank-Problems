import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaximumPalindromes {
    final static int mode = 1000000007;

    public static int fast_pow(long base, long n, long M) {
    
        if(n==0){
            return 1;
        }
        if(n==1){
            return (int)base;
        }
        long halfn=fast_pow(base,n/2,M);

    if(n%2==0){
        return (int)(( halfn * halfn ) % M);
    } else {
        return (int)(( ( ( halfn * halfn ) % M ) * base ) % M);
        }
    }
    

    public static int findMMI_fermat(int n, int M) {
        return fast_pow(n, M - 2, M);
    }

    public static void initialize(long[] factorial,  long[] revFactorial) {
        factorial[0] = 1;
        factorial[1] = 1;
        for (int i = 2; i < factorial.length; i++) {
            factorial[i] = (i*factorial[i-1])%mode;
        }

        revFactorial[0] = mode + 1;
        revFactorial[1] = mode + 1;
        for (int i = 2; i < revFactorial.length; i++) {
            revFactorial[i] = findMMI_fermat((int)factorial[i],mode);
            System.out.println(revFactorial[i]);
        }
    }
   
    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return (n * factorial(n - 1)) % mode;
    }

    public static int howManyNotDivisibelBy2(Map<Character, Integer> map) {
        int n = 0;
        for (char a : map.keySet()) {
            if (map.get(a) % 2 != 0) {
                n++;
            }
        }
        return n;
    }

    public static int maxPalindrome(Map<Character, Integer> map, int len, long[] factorial, long[] revFactorial) {
        int notDivisibel = howManyNotDivisibelBy2(map);
        // length is even then
        if (notDivisibel % 2 == 0) {
            if (notDivisibel == 0) {
                int denominatorMMI = 1;
                for (char a : map.keySet()) {
                    denominatorMMI *= revFactorial[map.get(a) / 2];
                }
                return (int)(factorial(len / 2) * denominatorMMI)%mode;
            }
        }
        int denominatorMMI = 1;
        for (char a : map.keySet()) {
            denominatorMMI *= revFactorial[map.get(a) / 2];
        }
        // System.out.println(denominator + "<<<-----------"); // 4838400
        // System.out.println(len);
        // int k = factorial((len - notDivisibel + 1) / 2) % 1000000007;
        // System.out.println(factorial(13) + "<<<-------------");
        // return (factorial((len-notDivisibel+1)/2)/denominator)*notDivisibel;
        return ((int)(factorial[(len-notDivisibel+1)/2] * denominatorMMI)%mode * notDivisibel)%mode;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        long[] factorial = new long[s.length() + 1]; 
        long[] revFactorial = new long[s.length() + 1]; 
        initialize(factorial, revFactorial);

        int days = sc.nextInt();
        while (days-- > 0) {
            int start = sc.nextInt() - 1;
            int end = sc.nextInt();
            sc.nextLine();

            Map<Character, Integer> map = new HashMap<>();
            for (int i = start; i < end; i++) {
                if (!map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), 1);
                    continue;
                }
                int value = map.get(s.charAt(i));
                map.put(s.charAt(i), ++value);
            }
            
            System.out.println(maxPalindrome(map, (end - start), factorial, revFactorial));
        }
        sc.close();
    }
}