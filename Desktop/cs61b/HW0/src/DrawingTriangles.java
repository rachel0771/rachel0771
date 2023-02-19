public class DrawingTriangles {
    public static void drawTrianglePlanA(int n){
        int i, j;
        for(i = 1; i < n; i++){ // Control the row of this triangle
            for (j = i; j >= 1; j --){
                System.out.print("*"); // Print the triangle
            }
            System.out.println("");// Start the new line which determine the height of the triangle
        }
    }

    public static void drawTrianglePlanB(int n){ // It is the method's signature
        for(int i = 1; i<= n; i++){
            for(int j = 1; j <= n - i; j++){
                System.out.print("");
            }
            for(int k = 1; k <= 2*i - 1; k++){
                System.out.print("&");
            }
            System.out.println("");
        }
    }
}
