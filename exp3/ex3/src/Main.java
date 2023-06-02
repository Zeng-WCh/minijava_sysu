import java_cup.runtime.*;
import ast.*;
import java.util.ArrayList;
import java.util.HashMap;
import callgraph.*;
import exceptions.*;
import java.io.*;


/**
 * the main class of the project
 * 
 * @author Weichao Zeng
 * @version 1.0 (LastUpdated: 2023/06/01)
 */
public class Main {
	/**
	 * check if formal meets the need of actual
	 * 
	 * @param formal, the formal parameter
	 * @param actual, the actual parameter
	 * @return true if formal meets the need of actual
	 */
	public static boolean checkArg(fp formal, expr actual) {
		if (formal.isVar && !actual.isVar())
			return false;
		if (!formal.isVar && actual.isVar())
			return false;
		typeAST formalType = formal.type;
		typeAST actualType = actual.getType();

		if (formalType.name.equals(actualType.name))
			return true;
		return false;
	}

	/**
	 * check if the actual parameters match the formal parameters
	 * 
	 * @param formal, the formal parameters
	 * @param actual, the actual parameters
	 * @return true if the actual parameters match the formal parameters
	 */
	public static boolean checkArgs(ArrayList<fp> formal, ArrayList<expr> actual) throws SemanticException {
		if (formal.size() != actual.size())
			throw new ParameterMismatchedException();
		for (int i = 0; i < formal.size(); ++i) {
			if (!checkArg(formal.get(i), actual.get(i)))
				throw new TypeMismatchedException(String.format("actual parameter: %s does not match formal parameter: %s", actual.get(i).getType().name, formal.get(i).type.name));
		}
		return true;
	}

	public static void main(String argv[]) throws Exception {
		if (argv.length == 0) {
			System.out.println("Usage : java Main <inputfile>");
		} else {
			for (int i = 0; i < argv.length; i++) {
				int id = 0;
				ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
				OberonScanner scanner = new OberonScanner(new BufferedReader(new FileReader(argv[i])), symbolFactory);
				Parser p = new Parser(scanner, symbolFactory);
				System.out.println(argv[i] + ":");
				try {
					p.parse();
				} catch (Exception ex) {
					System.err.println(String.format("Exception occured when parsing near by <%d:%d>: ", scanner.getLine() + 1, scanner.getCol() + 1));
					System.err.println(ex.getMessage());
					System.exit(1);
				}
				moduleBlock mb = p.getAST();
				System.out.println("SUCCESS");

				CallGraph graph = new CallGraph();
			}
		}
	}
}
