public class ArrayNums {
    // Array is an object consisting of a numbered list of variables
    // The variables in array are always indexed from zero in increment of n-1
    public static int whileSum(int[] a){
        int i = 0; // initialization
        int sum = 0;
        while(i < a.length){ // termination
            sum = sum + a[i];
            i = i + 1; // increment
        }
        return sum;
    }

    public static int forSum(int[] a){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum = sum + a[i];
        }
        return sum;
    }

    public static int max(int[] a){
        int maxNum = a[0];
        for(int i = 1; i < a.length; i++){
            if(maxNum < a[i])
                maxNum = a[i];
            if(maxNum >= a[i])
                maxNum = maxNum;
        }
        return maxNum;
    }

    public static void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
