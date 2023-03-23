import java.io.*;

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.print("Input an infix expression and output its postfix notation: ");
		parser p = new parser();

		p.parse();

		//System.out.printf("Result: %d\n", p.eval());
		System.out.println(p.dump());
		System.out.println("\nEnd of program.");
	}
}
