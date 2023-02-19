public class IsPrime {
    public static  boolean isPrime(int n){
        for(int divisor = 2; divisor < n; divisor++){
            if(n % divisor ==0){
                return false;
            }
        }
        return true;
    }

    public static void printPrimes(int n){
        for (int i = 2; i <= n; i++){
            if(isPrime(i)){ // means if isPrime(i) is true
                System.out.println(i);
            }
        }
    }
}

// The common mistake among Java and C is to get the condition wrong
// Such as it should be i <= n not i < n if you want to print all the
// - prime numbers between 2 - n
