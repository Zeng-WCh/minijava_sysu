import java.io.IOException;

/**
 * Postfix, main entry point for the program.
 */
public class Postfix {
    /**
     * printPost: print the postfix expression
     * printEval: print the evaluation result
     * test: test mode, only print the evaluation result
     */
    static boolean printPost = true;
    static boolean printEval = true;
    static boolean test = false;

    private static void parse(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-p")) {
                printPost = true;
            }
            else if (args[i].equals("-e")) {
                printEval = true;
            }
            else if (args[i].equals("-t")) {
                test = true;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        parse(args);
        if (!test)
            System.out.print("Enter an expression: ");
        
        Parser p = new Parser();
        p.parse();
        
        if (test) {
            System.out.printf("%f", p.eval());
            return;
        }

        if (printPost) {
            System.out.printf("Postfix is: %s\n", p.postFix());
        }

        if (printEval) {
            System.out.printf("Result is: %f\n", p.eval());
        }
        
    }
}