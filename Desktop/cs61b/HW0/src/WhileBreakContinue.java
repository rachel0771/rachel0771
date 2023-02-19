public class WhileBreakContinue {
    //Occasionally, you may find it useful to use the break or continue keywords.
    // The continue statement skips the rest of the current iteration of the loop,
    // - effectively jumping straight to the increment condition.
    //
    //For example the code below prints each String from an array three times,
    // -but skips any strings that contain “horse”.

    public static void continueEg(String[] n){
        for(int i = 0; i < n.length; i++){
            if (n[i].contains("horse")){
                continue;
                // otherwise it will break this for loop to the next for loop
            }
            for(int j =0; j < 3; j += 1){
                System.out.println(n[i]); // it will print 3 times of each element
            }
        }
    }

   //By contrast, the break keyword completely terminates the innermost loop when it is called.
    // For example the code below prints each String from an array three times,
    // - except for strings that contain horse, which are only printed once.

    public static void breakEg(String[] n){
        for(int i = 0; i < n.length; i++){
            for(int j =0; j<3; j+=1){
                System.out.println(n[i]);
                if (n[i].contains("horse")){ // element that contains horse will only print once
                    break;
                }
            }
        }
    }

    public static void main(String[] args){
        String[] animals = new String[]{"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};
        // or String[] animals = {"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};
    }
}
