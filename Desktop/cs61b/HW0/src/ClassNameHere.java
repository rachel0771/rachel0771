public class ClassNameHere {
    public static void main(String[] args) {
        int x = 5;
        if (x < 10) {
            // It is also possible to execute multiple statements in response to a signle condition
            // But we need curly braces at this time
            // Statements are grouped by the braces and not by indentation.
            System.out.println("I shall increment x by 10.");
            x = x + 10;
        }
        if (x > 10) {
            System.out.println("I shall increment x by 5");
            x = x + 10;
        }
        System.out.println(x);
    }
}