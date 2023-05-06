/**
 * @Copyright(C) 2008 Software Engineering Laboratory (SELAB), Department of Computer 
 * Science, SUN YAT-SEN UNIVERSITY. All rights reserved.
**/

package parser;

import ast.ast;
import exceptions.*;

/**
 * Main program of the expression based calculator ExprEval
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/04/28)
 **/
public class Calculator {
	/**
	 * The main program of the parser. You should substitute the body of this method
	 * with your experiment result.
	 * 
	 * @param expression user input to the calculator from GUI.
	 * @return if the expression is well-formed, return the evaluation result of it.
	 * @throws ExpressionException if the expression has error, a corresponding
	 *                             exception will be raised.
	 **/
	public double calculate(String expression) throws ExpressionException {
		return new Parser(expression).parse().eval();
	}
}
