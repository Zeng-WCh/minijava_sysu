import scanner.*;
import token.*;
import exceptions.*;
public class ScannerTest {
    private static void test(int testNum, String input) throws LexicalException {
        System.out.printf("======================================================\n");
        System.out.printf("Input No.%d: %s\n", testNum, input);
        Scanner sc = new Scanner(new Buffer(input));
        while (true) {
            Token now = sc.next();
            if (now == null) {
                break;
            }
            System.out.printf("Read: %s, Get: %s\n", now.toString(), now.getType().toString());
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