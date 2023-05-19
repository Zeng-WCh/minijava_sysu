import parser.*;
import ast.*;

/**
 * Main program of the expression based calculator ExprEval, used to print AST of the expression
 */
public class test {
    public static void main(String[] args) throws Exception {
        for (String exp : args) {
            System.out.println("The expression is: " + exp);
            Parser parser = new Parser(exp);
            ast ast = parser.parse();
            Double result = ast.eval();
            System.out.println("The result is: " + result);
            ast.print(0);
        }
    }
}
