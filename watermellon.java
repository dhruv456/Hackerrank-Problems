import java.util.Scanner;


public class watermellon {

    public static  boolean fun(int sum, int n) {
        if(sum == 0) {
            return true;
        }
        int temp = sum;
        for (int i = n; i >= 1; i--) {
            temp -= i;
            if(temp == 0) {
                return true;
            }
            if(temp < 0) {
                temp += i;
            }
            
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            // int[] arr = new int[n];
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum = sum + sc.nextInt();
            }

            System.out.println(fun(sum,n)?"YES":"NO");
        }
        sc.close();
    }
}
