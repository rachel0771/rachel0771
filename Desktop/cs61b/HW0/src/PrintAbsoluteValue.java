public class PrintAbsoluteValue {
    public static void main(String[] args){
        int x = -5;
        if(x < 0) {
            System.out.println("I should negate X");
            x = -x;
        }else if(x == 0){
            x = x;
        }else{
            x = x;
        }
        System.out.println(x);
    }
}


// While and For are both a kind of iteration
// initialize and next are both expressions that affect a variable that changes every loop iteration