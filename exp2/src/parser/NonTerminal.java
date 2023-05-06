package parser;

import token.TokenType;

import java.util.Vector;

public class NonTerminal extends StackElement {
    private final Vector<StackElement> production;

    public NonTerminal(TokenType type, String value) {
        super(type, value);
        this.production = new Vector<>();
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    public void addProduction(StackElement element) {
        this.production.add(element);
    }
}
