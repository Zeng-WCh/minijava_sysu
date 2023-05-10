package parser;

import token.TokenType;

/**
 * Use this class to represent Terminal parsing stack.
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class Terminal extends StackElement {
    /**
     * Construct a Terminal
     * 
     * @param type  token type
     * @param value token value
     */
    public Terminal(TokenType type, String value) {
        super(type, value);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}
