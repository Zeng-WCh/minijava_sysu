package parser;

import token.TokenType;
import ast.*;

/**
 * Use this class to represent NonTerminal in parsing stack.
 * 
 * update: add new method genAST() to generate AST, and using ast as IR.
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class NonTerminal extends StackElement {
    /**
     * the AST node, which will be assigned when reduce
     */
    private ast ast = null;

    /**
     * Construct a NonTerminal
     * 
     * @param type,  is not used actually, but the supertype need it to construct
     * @param value, the ast node
     */
    public NonTerminal(TokenType type, ast value) {
        super(type, "");
        this.ast = value;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    /**
     * get the ast node
     * 
     * @return the ast node this nonterminal represents
     */
    public ast genAST() {
        return this.ast;
    }
}
