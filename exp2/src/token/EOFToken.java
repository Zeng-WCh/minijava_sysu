package token;

/**
 * EOF Token class.
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class EOFToken extends Token {
    /**
     * Construct an EOFToken
     */
    public EOFToken() {
        super(TokenType.tok_eof);
    }
    
    @Override
    public String toString() {
        return "EOF";
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}
