import java_cup.runtime.*;
import ast.*;
import java.util.ArrayList;
import callgraph.*;
import java.io.*;

public class Main {
	public static void main(String argv[]) throws Exception {
		if (argv.length == 0) {
			System.out.println("Usage : java Main <inputfile>");
		} else {
			for (int i = 0; i < argv.length; i++) {
				int id = 0;
				ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
				// ScannerBuffer scanner = new ScannerBuffer(
						// new OberonScanner(new BufferedReader(new FileReader(argv[i])), symbolFactory));
				OberonScanner scanner = new OberonScanner(new BufferedReader(new FileReader(argv[i])), symbolFactory);
				Parser p = new Parser(scanner, symbolFactory);
				System.out.println(argv[i] + ":");
				try {
					p.parse();
				} catch (Exception ex) {
					// System.out.printf("<%d, %d>\n", scanner.getLine(), scanner.getColumn());
					System.out.println(ex.getMessage());
					System.exit(1);
				}
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
