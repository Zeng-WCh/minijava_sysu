import exceptions.*;
import parser.*;
import ast.ast;

/**
 * Test for Parser, will print the ast and result
 */
public class ParserTest {
    public static void main(String[] args) throws ExpressionException {
        System.out.println("Start Test for Parser");

        for (int i = 0; i < args.length; ++i) {
            System.out.printf("======================================================\n");
            System.out.printf("Input No.%d: %s\n", i + 1, args[i]);
            Parser parser = new Parser(args[i]);
            ast r = null;
            try {
                r = parser.parse();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            r.print(0);
            System.out.printf("Result: %.3f\n", r.eval());
        }

        System.out.printf("======================================================\n");

        System.out.println("Test finished");
    }
}
