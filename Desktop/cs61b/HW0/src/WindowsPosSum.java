public class WindowsPosSum {
    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }

    public static void windowPosSum(int[] a, int n){
        int size = a.length;
        for(int i = 0; i < size; i++){
            if(a[i] <= 0) {
                continue;
            }
            for(int m = i+1; m < n + i; m++){ // Error is m = m + i. m < size
                if(m >= size) {
                    break;
                }
                a[i] += a[m];
            }
        }
    }


}
