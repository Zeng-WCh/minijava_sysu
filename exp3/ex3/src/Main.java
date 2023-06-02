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

	/**
	 * Main entry for the oberon-1 parser
	 * 
	 * @param argv, the input file
	 * @throws Exception, if the input file is not found or the parser failed to parse
	 */
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
				
				for (procedureDec func : mb.declarations.procDecs) {
					String ident = func.head.name.toLowerCase();
					ArrayList<fp> formalParam = func.head.fp.fps;
					StringBuilder strbd = new StringBuilder(ident);
					boolean hasParam = false;
					strbd.append('(');
					for (fp formalP : formalParam) {
						if (formalP.isVar) {
							strbd.append("VAR ");
						}
						strbd.append(formalP.type.name);
						strbd.append(',');
						hasParam = true;
					}
					if (hasParam) {
						strbd.deleteCharAt(strbd.length() - 1);
					}
					strbd.append(')');
					graph.addProcedure(ident.toLowerCase(), strbd.toString());
				}

				for (procedureDec func : mb.declarations.procDecs) {
					ArrayList<callStmt> calls = func.body.calls;
					Integer begin = 0;
					for (callStmt call : calls) {
						Integer idx = mb.declarations.pros.get(call.name.toUpperCase());
						if (idx == null) {
							throw new SyntacticException(String.format("Procedure: %s is not defined.", call.name));
						}

						// parameters check
						ArrayList<fp> formalParam = mb.declarations.procDecs.get(idx).head.fp.fps;
						ArrayList<expr> actualParam = call.params.exprs;
						checkArgs(formalParam, actualParam);

						StringBuilder actuaP = new StringBuilder(call.name);
						actuaP.append('(');
						for (int id1 = 0; id1 < actualParam.size() ; ++id1) {
							actuaP.append(actualParam.get(id1).toString());
							actuaP.append(',');
						}

						if (!actualParam.isEmpty()) {
							actuaP.deleteCharAt(actuaP.length() - 1);
						}

						actuaP.append(')');
						StringBuilder callerId = new StringBuilder(func.head.name);
						callerId.append(begin.toString());
						begin = begin + 1;
						graph.addCallSite(callerId.toString().toLowerCase(), call.name.toLowerCase() + "()", actuaP.toString().toLowerCase());
						graph.addEdge(callerId.toString().toLowerCase(), call.name.toLowerCase());
					}
				}

				graph.show();
			}
		}
	}
}
