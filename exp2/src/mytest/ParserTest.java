import exceptions.*;
import parser.*;
import java.util.Vector;

public class ParserTest {
    public static void main(String[] args) throws ExpressionException {
        System.out.println("Start Test for Parser");

        for (int i = 0; i < args.length; ++i) {
            System.out.printf("Input No.%d: %s\n", i + 1, args[i]);
            Parser parser = new Parser(args[i]);
            try {
                parser.parse();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.printf("======================================================\n");

        System.out.println("Test finished");

    }
}
