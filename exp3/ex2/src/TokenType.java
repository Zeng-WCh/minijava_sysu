public enum TokenType {
    // END OF FILE
    tok_eof,

    // NUMBERS
    tok_decimal,
    tok_octal,

    // IDENTIFIER
    tok_identifier,

    // TYPES
    tok_integer,
    tok_boolean,

    // KEYWORDS
    tok_write,
    tok_writeln,
    tok_read,

    tok_if, 
    tok_else,
    tok_elsif,
    tok_then,
    tok_while,
    tok_begin,
    tok_do,
    tok_end,
    tok_of,
    tok_var,
    tok_const,
    tok_type,
    tok_procedure,
    tok_record,
    tok_array,
    tok_module,

    tok_comma, // ,
    tok_semicolon, // ;

    tok_plus,
    tok_minus,
    tok_multiply,
    tok_divide,
    tok_mod,

    tok_greater,
    tok_greater_equal,
    tok_less,
    tok_less_equal,
    tok_equal,
    tok_not_equal,

    tok_and,
    tok_or,
    tok_not,

    tok_assign,
    tok_dot,

    tok_lparen, // (
    tok_rparen, // )
    tok_lbracket, // [
    tok_rbracket, // ]

    tok_colon, // :

    tok_comment,
}
