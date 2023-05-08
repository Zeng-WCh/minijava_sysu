package token;

/**
 * Bool Token class.
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/04/28)
 */
public class Bool extends Token {
    /**
     * The value of the token. It is either "true" or "false".
     */
    private String value;

    public Bool(TokenType type, String value) {
        super(type);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}
