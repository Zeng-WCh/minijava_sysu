import java.io.*;

// <expr> ::= <term> <rest>
// <rest> ::= + <term> <rest> | - <term> <rest> | epsilon
// <term> ::= <digit>
// <digit> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
/**
 * A Parser Class that use non recursion
 */
class Parser {
	/**
	 * A lexer
	 */
	Lexer l;
	/**
	 * A string builder that used to display the postfix expression
	 * due to we may display some error msg when parsing
	 */
	StringBuilder strBd;
	/*
	 * A flag, set to true if we want to know how much time takes when parsing
	 */
	boolean calTime;

	/**
	 * A variable used to record the time while parsing
	 */
	long beforeTime, afterTime;

	/**
	 * Constructor of Parser
	 * @throws IOException
	 */
	public Parser() throws IOException {
		this.calTime = false;
		this.beforeTime = 0;
		this.afterTime = 0;
		this.l = new Lexer();
		this.strBd = new StringBuilder();
	}

	/**
	 * Constructor of Parser, with a flag
	 * @param calcTime, a flag, set to true if we want to know how much time takes when parsing
	 * @throws IOException
	 */
	public Parser(boolean calcTime) throws IOException {
		this.calTime = calcTime;
		this.beforeTime = 0;
		this.afterTime = 0;
		this.l = new Lexer();
		this.strBd = new StringBuilder();
	}

	/**
	 * method used to parse <expr>
	 * 
	 * @throws IOException
	 */
	public void expr() throws IOException {
		term();
		// only record time cost of parsing rest
		if (this.calTime) {
			this.beforeTime = System.currentTimeMillis();
		}
		rest();
		if (this.calTime) {
			this.afterTime = System.currentTimeMillis();
			System.err.printf("Time cost of parsing rest using non recursion: %d ms\n", this.afterTime - this.beforeTime);
		}
		display();
	}

	/**
	 * To display the postfix expression
	 */
	private void display() {
		System.out.println(this.strBd.toString());
	}

	/**
	 * method used to parse <rest>
	 * 
	 * @throws IOException
	 */
	private void rest() throws IOException {
		Token t = this.next();

		while (t == Token.tok_num) {
			logError(this.l.getReadIn(), "Syntax Error, Expecting an operator, ignore it and coutinue parsing.",
					this.l.getPos() - 1, 1);
			t = this.next();
		}

		if (t == Token.tok_plus || t == Token.tok_minus) {
			term();
			if (t == Token.tok_plus) {
				strBd.append('+');
			} else if (t == Token.tok_minus) {
				strBd.append('-');
			}
			rest();
		}
	}

	/**
	 * method used to parse <term>
	 */
	private void term() throws IOException {
		Token t = this.next();
		while (t != Token.tok_num) {
			logError(this.l.getReadIn(), "Syntax Error, Expecting a number, ignore it and coutinue parsing.",
					this.l.getPos() - 1, 1);
			t = this.next();
		}

		if (t == Token.tok_num) {
			this.strBd.append((char) this.l.getCur());
		}
	}

	/**
	 * 
	 * @param passed,   string that has been parsed
	 * @param expected, the info that used to display what kind of error
	 * @param pos,      where the error happen
	 * @param length,   how long the error is
	 *                  For example, we get an input like "aaa+1",
	 *                  then we can set passed "aaa+", expected "Expecting a
	 *                  number",
	 *                  pos to the first 'a', which is 0 and length to 3
	 */
	private void logError(String passed, String expected, int pos, int length) {
		System.out.println(passed);
		if (length > 1) {
			pos += length - 1;
			for (int i = 0; i < pos - length; ++i) {
				System.out.print(" ");
			}
			for (int i = 0; i < length - 1; ++i) {
				System.out.print("~");
			}
			System.out.printf("^ %s\n", expected);
		} else {
			for (int i = 0; i < pos - 1; ++i) {
				System.out.print(" ");
			}
			System.out.printf("^ %s\n", expected);
		}
	}

	/**
	 * 
	 * @return the next token, but filtered all whitespaces and unknown tokens
	 * @throws IOException
	 */
	private Token next() throws IOException {
		int lPos = this.l.getPos();
		;
		Token t = this.l.next();

		boolean hasSpace = false, hasUnknown = false;

		while (t == Token.tok_space || t == Token.tok_unknown) {
			if (t == Token.tok_space) {
				hasSpace = true;
			} else if (t == Token.tok_unknown) {
				hasUnknown = true;
			}
			t = this.l.next();
		}

		// print error msg
		if (hasSpace && hasUnknown) {
			logError(this.l.getReadIn(), "Unknown token detected and No Space is allowed, ignore it.", lPos,
					this.l.getPos() - 1 - lPos);
		} else if (hasSpace) {
			logError(this.l.getReadIn(), "No Space is allowed, ignore it.", lPos, this.l.getPos() - 1 - lPos);
		} else if (hasUnknown) {
			logError(this.l.getReadIn(), "Unknown token detected, ignore it.", lPos, this.l.getPos() - 1 - lPos);
		}

		return t;
	}
}

/*
 * Main Class
 */
public class PostfixRecursion {
	public static void main(String[] args) throws IOException {
		boolean test = false, timeTest = false;
		for (String arg : args) {
			if (arg.equals("-t")) {
				test = true;
			}
			else if (arg.equals("--time")) {
				timeTest = true;
			}
		}
		if (!test)
			System.out.println("Input an infix expression and output its postfix notation:");
		new Parser(timeTest).expr();
		if (!test)
			System.out.println("\nEnd of program.");
	}
}
