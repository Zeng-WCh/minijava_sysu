import java.io.*;

// class Parser {
// 	static int lookahead;

// 	public Parser() throws IOException {
// 		lookahead = System.in.read();
// 	}

// 	void expr() throws IOException {
// 		term();
// 		rest();
// 	}

// 	void rest() throws IOException {
// 		while (lookahead == '+' || lookahead == '-') {
// 			if (lookahead == '+') {
// 				match('+');
// 				term();
// 				System.out.write('+');
// 			} else if (lookahead == '-') {
// 				match('-');
// 				term();
// 				System.out.write('-');
// 			} else {
// 				// do nothing with the input
// 			}
// 		}
// 		// if (lookahead == '+') {
// 		// 	match('+');
// 		// 	term();
// 		// 	System.out.write('+');
// 		// 	rest();
// 		// } else if (lookahead == '-') {
// 		// 	match('-');
// 		// 	term();
// 		// 	System.out.write('-');
// 		// 	rest();
// 		// } else {
// 		// 	// do nothing with the input
// 		// }
// 	}

// 	void term() throws IOException {
// 		if (Character.isDigit((char) lookahead)) {
// 			System.out.write((char) lookahead);
// 			match(lookahead);
// 		} else
// 			throw new Error("syntax error");
// 	}

// 	void match(int t) throws IOException {
// 		if (lookahead == t)
// 			lookahead = System.in.read();
// 		else
// 			throw new Error("syntax error");
// 	}
// }

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
