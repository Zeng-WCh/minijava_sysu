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
    System.out.printf("comment: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_comment; 
}

{UnMatchedComment} { 
    throw new MismatchedCommentException(); 
}

"boolean" { 
    System.out.printf("boolean: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_boolean; 
}
"integer" { 
    System.out.printf("integer: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_integer; 
}
"write" { 
    System.out.printf("write: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_write; 
}
"read" { 
    System.out.printf("read: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_read; 
}
"writeln" { 
    System.out.printf("writeln: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_writeln; 
}

"array" { 
    System.out.printf("array: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_array; 
}
"of" { 
    System.out.printf("of: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_of; 
}
"record" { 
    System.out.printf("record: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_record; 
}
"var" { 
    System.out.printf("var: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_var; 
}
"procedure" { 
    System.out.printf("procedure: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_procedure; 
}
"begin" { 
    System.out.printf("begin: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_begin; 
}
"end" { 
    System.out.printf("end: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_end; 
}
"if" { 
    System.out.printf("if: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_if; 
}
"then" { 
    System.out.printf("then: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_then; 
}
"else" { 
    System.out.printf("else: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_else; 
}
"while" { 
    System.out.printf("while: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_while; 
}
"do" { 
    System.out.printf("do: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_do; 
}
"elsif" { 
    System.out.printf("elsif: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_elsif; 
}
"const" { 
    System.out.printf("const: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_const; 
}
"module" { 
    System.out.printf("module: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_module; 
}
"type" { 
    System.out.printf("type: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_type; 
}

":=" { 
    System.out.printf("assign: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_assign; 
}

"," { 
    System.out.printf("comma: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_comma; 
}
"." { 
    System.out.printf("dot: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_dot; 
}
":" { 
    System.out.printf("colon: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_colon; 
}
";" { 
    System.out.printf("semicolon: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_semicolon; 
}
"(" { 
    System.out.printf("lparen: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_lparen; 
}
")" { 
    System.out.printf("rparen: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_rparen; 
}
"[" { 
    System.out.printf("lbracket: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_lbracket; 
}
"]" { 
    System.out.printf("rbracket: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_rbracket; 
}

"=" { 
    System.out.printf("equal: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_equal; 
}
"#" { 
    System.out.printf("notequal: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_not_equal; 
}

"<=" { 
    System.out.printf("lessequal: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_less_equal; 
}
"<" { 
    System.out.printf("less: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_less; 
}
">=" { 
    System.out.printf("greaterequal: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_greater_equal; 
}
">" { 
    System.out.printf("greater: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_greater; 
}
"+" { 
    System.out.printf("plus: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_plus; 
}
"-" { 
    System.out.printf("minus: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_minus; 
}
"*" { 
    System.out.printf("multiply: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_multiply; 
}
"div" { 
    System.out.printf("divide: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_divide; 
}
"mod" { 
    System.out.printf("mod: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_mod; 
}
"&" { 
    System.out.printf("and: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_and; 
}
"or" { 
    System.out.printf("or: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_or; 
}
"~" { 
    System.out.printf("not: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_not; 
}

{Octal} { 
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    System.out.printf("Octal: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_octal; 
}
{Decimal} { 
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    System.out.printf("decimal: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_decimal; 
}
{IllegalNumber} { throw new IllegalIntegerException(); }
{IllegalOctal} { throw new IllegalOctalException(); }

{Ignore} { 
}

{Identifier} { 
    if (yylength() > 24)
        throw new IllegalIdentifierLengthException();
    System.out.printf("identifier: '%s'\t\tLoc=<%d:%d>\n", yytext(), yyline, yycolumn);
    return TokenType.tok_identifier; 
}

. { throw new IllegalSymbolException(); }