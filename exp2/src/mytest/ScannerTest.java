package mytest;
import scanner.*;
import token.*;
import exceptions.*;
public class ScannerTest {
    public static void main(String[] args) throws ExpressionException {
        System.out.println("Start Test for Scanner");

        String input = "1+1+6-10+sin(3.14)-cos(1)+max(1,2, 3)+1e6";
        Scanner sc = new Scanner(new Buffer(input));
        while (true) {
            TokenBase now = sc.next();
            if (now == null) {
                break;
            }
            System.printf("%s ", now.toString());
        }
        System.out.println("Test finish");
    }
}
