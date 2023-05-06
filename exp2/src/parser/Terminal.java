package parser;

import token.TokenType;

public class Terminal extends StackElement {
    public Terminal(TokenType type, String value) {
        super(type, value);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}
