/**
 * the Token class used in scanner
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update: 2023/06/04)
 */
public class Token {
    /**
     * The Tokentype
     */
    private TokenType type;
    /**
     * The lex val in String form
     */
    private Object value;

    /**
     * Default Construtor
     */
    public Token() {
        this(null, null);
    }

    /**
     * Constructor used in non-number, non-identifier
     * 
     * @param type, the token type
     */
    public Token(TokenType type) {
        this(type, null);
    }

    /**
     * Constructor used in number or identifier
     * 
     * @param type, the token type
     * @param value, the lex val, can be Integer or String
     */
    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * return the lex val of the token.
     * 
     * @return the lexval
     */
    public Object getVal() {
        return this.value;
    }

    /**
     * return the type of the token.
     * 
     * @return the tokentype
     */
    public TokenType getType() {
        return this.type;
    }
}
