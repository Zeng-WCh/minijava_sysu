import scanner.*;
import token.*;
import exceptions.*;

/**
 * Scanner Test
 */
public class ScannerTest {
    private static void test(int testNum, String input) throws LexicalException {
        System.out.printf("======================================================\n");
        System.out.printf("Input No.%d: %s\n", testNum, input);
        Scanner sc = new Scanner(new Buffer(input));
        while (true) {
            Token now = sc.next();
            System.out.printf("Read: %s, Get: {%s, %s}\n", now.toString(), now.toString(), now.getType().toString());
            if (now.getType() == TokenType.tok_eof) {
                break;
            }
        }
        System.out.printf("======================================================\n");
    }

    public static void main(String[] args) throws LexicalException {
        System.out.println("Start Test for Scanner");

        for (int i = 0; i < args.length; ++i) {
            test(i + 1, args[i]);
        }

        System.out.println("Test finished");
    }
}