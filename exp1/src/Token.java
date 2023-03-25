/**
 * A token enum class
 * To represent the tokens in the language
 * num ::= [0-9]+
 * plus ::= +
 * minus ::= -
 * star ::= *
 * slash ::= /
 * lP ::= (
 * rP ::= )
 * eof ::= EOF
 * unknown ::= other, to raise an lexical error
 */
public enum Token {
    tok_num,
    tok_plus,
    tok_minus,
    tok_star,
    tok_slash,
    tok_lP,
    tok_rP,
    tok_eof,
    tok_unknown,
}
