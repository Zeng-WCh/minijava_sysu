import scanner.*;
import token.*;
import exceptions.*;
public class ScannerTest {
    public static void main(String[] args) throws ExpressionException {
        System.out.println("Start Test for Scanner");

        int testNum = 0;

        ++testNum;
        String input = "1+1+6-10+sin(3.14)-cos(1)+max(1,2, 3)+1e6";
        System.out.printf("Input No.%d: %s\n", testNum, input);
        Scanner sc = new Scanner(new Buffer(input));
        while (true) {
            TokenBase now = sc.next();
            if (now == null) {
                break;
            }
            System.out.printf("%s ", now.getType().toString());
        }

        ++testNum;
        input = "1+ sin(2>3?4:5)";
        System.out.printf("\nInput No.%d: %s\n", testNum, input);
        sc = new Scanner(new Buffer(input));
        while (true) {
            TokenBase now = sc.next();
            if (now == null) {
                break;
            }
            System.out.printf("%s ", now.getType().toString());
        }

        System.out.println("\nTest finish\n");
    }
}