package token;

/**
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/04/28)
 * Use to represent the token type, which is used in Scanner
 */
public enum TokenType {
    tok_true,
    tok_false,
    tok_decimal,

    tok_add, // +
    tok_unary_minus, // -, need special judge when parsing
    tok_minus, // - 
    tok_star, // *
    tok_slash, // /

    tok_lparen, // (
    tok_rparen, // )
    tok_colon, // :
    tok_caret, // ^
    tok_comma, // ,
    tok_question, // ?
    tok_and, // &
    tok_or, // |
    tok_not, // !
    tok_equal, // =

    tok_greater, // >
    tok_greater_equal, // >=
    
    tok_less, // <
    tok_less_equal, // <=
    tok_not_equal, // <>

    tok_min, // min
    tok_max, // max
    tok_sin, // sin
    tok_cos, // cos

    tok_eof, // end of file
}
