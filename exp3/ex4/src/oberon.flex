import exceptions.*;


%%

%type TokenType
%line
%column
%ignorecase
%unicode
%class OberonScanner
%standalone
%public
%yylexthrow LexicalException

LineTerminator = \r|\n|\r\n
Ignore = [ \t\f] | {LineTerminator}

Comment = "(*"~"*)"
UnMatchedComment = "(*" ([^\*] | "*"+[^\)])* | ([^\(]|"("+[^\*])* "*)"

Identifier = [a-zA-Z][a-zA-Z|0-9]*

Octal = 0[0-7]*
Decimal = [1-9][0-9]*
Number = {Octal}|{Decimal}
IllegalNumber = {Number}+{Identifier}+
IllegalOctal = 0[0-7]*[8|9]+[0-9]*

%eofval{
    return TokenType.tok_eof;
%eofval}

%%

{Comment} { 
}

{UnMatchedComment} { 
    throw new MismatchedCommentException(); 
}

"boolean" { 
    return TokenType.tok_boolean; 
}
"integer" { 
    return TokenType.tok_integer; 
}
"write" { 
    return TokenType.tok_write; 
}
"read" { 
    return TokenType.tok_read; 
}
"writeln" { 
    return TokenType.tok_writeln; 
}

"array" { 
    return TokenType.tok_array; 
}
"of" { 
    return TokenType.tok_of; 
}
"record" { 
    return TokenType.tok_record; 
}
"var" { 
    return TokenType.tok_var; 
}
"procedure" { 
    return TokenType.tok_procedure; 
}
"begin" { 
    return TokenType.tok_begin; 
}
"end" { 
    return TokenType.tok_end; 
}
"if" { 
    return TokenType.tok_if; 
}
"then" { 
    return TokenType.tok_then; 
}
"else" { 
    return TokenType.tok_else; 
}
"while" { 
    return TokenType.tok_while; 
}
"do" { 
    return TokenType.tok_do; 
}
"elsif" { 
    return TokenType.tok_elsif; 
}
"const" { 
    return TokenType.tok_const; 
}
"module" { 
    return TokenType.tok_module; 
}
"type" { 
    return TokenType.tok_type; 
}

":=" { 
    return TokenType.tok_assign; 
}

"," { 
    return TokenType.tok_comma; 
}
"." { 
    return TokenType.tok_dot; 
}
":" { 
    return TokenType.tok_colon; 
}
";" { 
    return TokenType.tok_semicolon; 
}
"(" { 
    return TokenType.tok_lparen; 
}
")" { 
    return TokenType.tok_rparen; 
}
"[" { 
    return TokenType.tok_lbracket; 
}
"]" { 
    return TokenType.tok_rbracket; 
}

"=" { 
    return TokenType.tok_equal; 
}
"#" { 
    return TokenType.tok_not_equal; 
}

"<=" { 
    return TokenType.tok_less_equal; 
}
"<" { 
    return TokenType.tok_less; 
}
">=" { 
    return TokenType.tok_greater_equal; 
}
">" { 
    return TokenType.tok_greater; 
}
"+" { 
    return TokenType.tok_plus; 
}
"-" { 
    return TokenType.tok_minus; 
}
"*" { 
    return TokenType.tok_multiply; 
}
"div" { 
    return TokenType.tok_divide; 
}
"mod" { 
    return TokenType.tok_mod; 
}
"&" { 
    return TokenType.tok_and; 
}
"or" { 
    return TokenType.tok_or; 
}
"~" { 
    return TokenType.tok_not; 
}

{Octal} { 
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    return TokenType.tok_octal; 
}
{Decimal} { 
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    return TokenType.tok_decimal; 
}
{IllegalNumber} { throw new IllegalIntegerException(); }
{IllegalOctal} { throw new IllegalOctalException(); }

{Ignore} { 
}

{Identifier} { 
    if (yylength() > 24)
        throw new IllegalIdentifierLengthException();
    return TokenType.tok_identifier; 
}

. { throw new IllegalSymbolException(); }