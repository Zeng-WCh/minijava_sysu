/**
 * A Enum Class That Represents The Token Types
 */
public enum Token {
    /**
     * End of File
     */
    tok_eof,
    /**
     * Digits
     */
    tok_num,
    /**
     * +
     */
    tok_plus,
    /**
     * -
     */
    tok_minus,
    /**
     * Whitespace
     */
    tok_space,
    /**
     * Any other Tokens, like 'a', 'u'...
     */
    tok_unknown,
}
