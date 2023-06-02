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
	 * To generate the special type info for IDENTIFIERs
	 */
	public static void generateTypeInfo(moduleBlock mb) throws SyntacticException {
		ArrayList<varDec> globalVars = mb.declarations.varDecs;
		System.out.println(globalVars);
		ArrayList<constDec> globalConstants = mb.declarations.constDecs;
		ArrayList<typeDec> globalTypes = mb.declarations.typeDecs;
		ArrayList<procedureDec> funcs = mb.declarations.procDecs;

		HashMap<String, Integer> globalVarMap = mb.declarations.vars;
		HashMap<String, Integer> globalConstantsMap = mb.declarations.constant;
		HashMap<String, Integer> globalTypesMap = mb.declarations.types;
		HashMap<String, Integer> funcsMap = mb.declarations.pros;

		for (procedureDec f : funcs) {
			ArrayList<fp> fps = f.head.fp.fps;
			ArrayList<varDec> localVars = f.body.declarations.varDecs;
			ArrayList<constDec> localConstans = f.body.declarations.constDecs;
			ArrayList<typeDec> localTypes = f.body.declarations.typeDecs;
			ArrayList<procedureDec> localProcedure = f.body.declarations.procDecs;

			HashMap<String, Integer> localVarMap = f.body.declarations.vars;
			HashMap<String, Integer> localConstMap = f.body.declarations.constant;
			HashMap<String, Integer> localTypesMap = f.body.declarations.types;
			HashMap<String, Integer> localProcedureMap = f.body.declarations.pros;

			ArrayList<stmt> states = f.body.stmts.statements;

			for (stmt instruction : states) {
				ast i = instruction.getStmt();
				if (i == null)
					continue;
				if (i instanceof assignmentStmt) {
					assignmentStmt asg = (assignmentStmt) i;
					String ident = asg.name;
					expr right = asg.value;
					typeAST type = null;
					// from local vars to global vars to local const to global const
					Integer idx = localVarMap.get(ident);
					if (idx == null) {
						idx = globalVarMap.get(ident);

						if (idx == null) {
							idx = localConstMap.get(ident);
							if (idx == null) {
								idx = globalConstantsMap.get(ident);
							}
							else {
								throw new SyntacticException(String.format("%s is in local constant, can not be assigned", ident));
							}
							if (idx == null) {
								throw new SyntacticException(String.format("%s can not be found in procedure: %s", ident, f.head.name));
							}
							throw new SyntacticException(String.format("%s is in global constant, can not be assigned", ident));
						}
						else {
							// global vars
							type = globalVars.get(idx).type;
						}
					}
					else {
						// local vars
						type = localVars.get(idx).type;
					}

					if (type.name.equals("INTEGER")) {

					}
					else if (type.name.equals("BOOLEAN")) {

					}
					else {

					}
				}
				else if (i instanceof callStmt) {
					callStmt call = (callStmt) i;
				}
				else if (i instanceof ifStmt) {
					ifStmt ifs = (ifStmt) i;
				}
				else if (i instanceof whileStmt) {
					whileStmt whiles = (whileStmt) i;
				}
			}

		}
	}


	/**
	 * 
	 * @param mb, module Block AST
	 * @param asgMap, assignment statement, string is the position used to locate the assignment statement
	 * @throws TypeMismatchedException if the assignment type is not matched the expr type
	 * @throws SemanticException if the assignment is not valid
	 */
	public static void checkAsgs(moduleBlock mb) throws SemanticException {
		ArrayList<varDec> vars = mb.declarations.varDecs;

	}
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
		
		return true;
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
				throw new TypeMismatchedException();
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
					System.err.println(String.format("Exception occured when parsing near by <%d:%d>: ", scanner.getLine(), scanner.getCol()));
					System.err.println(ex.getMessage());
					System.exit(1);
				}
				moduleBlock mb = p.getAST();
				generateTypeInfo(mb);
				System.out.println("SUCCESS");

			// 	moduleBlock mb = p.getAST();
			// 	declarations d = mb.declarations;
			// 	ArrayList<procedureDec> pd = d.procDecs;
			// 	for (procedureDec pdc : pd) {
			// 		boolean hasPar = false;
			// 		System.out.print(pdc.head.name + "(");
			// 		for (fp param : pdc.head.fp.fps) {
			// 			hasPar = true;
			// 			boolean isVar = param.isVar;
			// 			if (isVar) {
			// 				System.out.print("var ");
			// 			}
			// 			System.out.print(param.name + ":" + param.type.toString() + ",");
			// 		}
			// 		System.out.println((hasPar ? "\b" : "") + ")");

			// 		procedureBody pb = pdc.body;
			// 		stmts statements = pb.stmts;
			// 		ArrayList<stmt> stmtList = statements.statements;
			// 		for (stmt state:stmtList) {
			// 			// System.out.println(state);
			// 			ast a = state.getStmt();
			// 			if (a == null) {
			// 				continue;
			// 			}
			// 			if (a instanceof callStmt) {
			// 				callStmt c = (callStmt) a;
			// 				// String callee = c.name;
			// 				// actualParameters ap = c.params;
			// 				// System.out.printf("%s(", callee);
			// 				// boolean hasParam = false;
			// 				// for (expr ape:ap.exprs) {
			// 				// 	hasParam = true;
			// 				// 	System.out.printf("%s,", ape.toString());
			// 				// }
			// 				// System.out.println((hasParam ? "\b" : "") + ")");
			// 				System.out.println(c.toString());
			// 			}
			// 			else if (a instanceof ifStmt) {
			// 				ifStmt ifs = (ifStmt) a;
			// 				System.out.println(ifs.toString());
			// 			}
			// 			else if (a instanceof assignmentStmt) {
			// 				assignmentStmt asg = (assignmentStmt) a;
			// 				System.out.println(asg.toString());
			// 			}
			// 			else if (a instanceof whileStmt) {
			// 				whileStmt ws = (whileStmt) a;
			// 				System.out.println(ws.toString());
			// 			}


					// }
				// }
			}
		}
	}
}
