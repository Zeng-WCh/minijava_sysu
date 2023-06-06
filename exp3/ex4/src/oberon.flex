import exceptions.*;

%%

%{
    public int getLine() {
        return yyline;
    }

    public int getCol() {
        return yycolumn;
    }

    public String getPos() {
        return String.format("<%d:%d>", getLine() + 1, getCol() + 1);
    }
%}

%type Token
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
    return new Token(TokenType.tok_eof);
%eofval}

%%

{Comment} { 
}

{UnMatchedComment} { 
    throw new MismatchedCommentException(); 
}

"boolean" { 
    return new Token(TokenType.tok_boolean);
}
"integer" { 
    return new Token(TokenType.tok_integer); 
}
"write" { 
    return new Token(TokenType.tok_write, "WRITE"); 
}
"read" { 
    return new Token(TokenType.tok_read, "READ");
}
"writeln" { 
    return new Token(TokenType.tok_writeln, "WRITELN"); 
}

"array" { 
    return new Token(TokenType.tok_array); 
}
"of" { 
    return new Token(TokenType.tok_of);
}
"record" { 
    return new Token(TokenType.tok_record); 
}
"var" { 
    return new Token(TokenType.tok_var); 
}
"procedure" { 
    return new Token(TokenType.tok_procedure); 
}
"begin" { 
    return new Token(TokenType.tok_begin); 
}
"end" { 
    return new Token(TokenType.tok_end); 
}
"if" { 
    return new Token(TokenType.tok_if); 
}
"then" { 
    return new Token(TokenType.tok_then); 
}
"else" { 
    return new Token(TokenType.tok_else); 
}
"while" { 
    return new Token(TokenType.tok_while); 
}
"do" { 
    return new Token(TokenType.tok_do); 
}
"elsif" { 
    return new Token(TokenType.tok_elsif); 
}
"const" { 
    return new Token(TokenType.tok_const); 
}
"module" { 
    return new Token(TokenType.tok_module); 
}
"type" { 
    return new Token(TokenType.tok_type); 
}

":=" { 
    return new Token(TokenType.tok_assign); 
}

"," { 
    return new Token(TokenType.tok_comma); 
}
"." { 
    return new Token(TokenType.tok_dot); 
}
":" { 
    return new Token(TokenType.tok_colon); 
}
";" { 
    return new Token(TokenType.tok_semicolon); 
}
"(" { 
    return new Token(TokenType.tok_lparen); 
}
")" { 
    return new Token(TokenType.tok_rparen); 
}
"[" { 
    return new Token(TokenType.tok_lbracket); 
}
"]" { 
    return new Token(TokenType.tok_rbracket); 
}

"=" { 
    return new Token(TokenType.tok_equal, "="); 
}
"#" { 
    return new Token(TokenType.tok_not_equal, "&ne"); 
}

"<=" { 
    return new Token(TokenType.tok_less_equal, "&le"); 
}
"<" { 
    return new Token(TokenType.tok_less, "&lt"); 
}
">=" { 
    return new Token(TokenType.tok_greater_equal, "&ge"); 
}
">" { 
    return new Token(TokenType.tok_greater, "&gt"); 
}
"+" { 
    return new Token(TokenType.tok_plus, "+"); 
}
"-" { 
    return new Token(TokenType.tok_minus, "-"); 
}
"*" { 
    return new Token(TokenType.tok_multiply, "*"); 
}
"div" { 
    return new Token(TokenType.tok_divide, "DIV"); 
}
"mod" { 
    return new Token(TokenType.tok_mod, "MOD"); 
}
"&" { 
    return new Token(TokenType.tok_and, "&"); 
}
"or" { 
    return new Token(TokenType.tok_or, "OR"); 
}
"~" { 
    return new Token(TokenType.tok_not, "~"); 
}

{Octal} { 
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    return new Token(TokenType.tok_number, Integer.parseInt(yytext(), 8)); 
}
{Decimal} { 
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    return new Token(TokenType.tok_number, Integer.parseInt(yytext())); 
}
{IllegalNumber} { throw new IllegalIntegerException(); }
{IllegalOctal} { throw new IllegalOctalException(); }

{Ignore} { 
}

{Identifier} { 
    if (yylength() > 24)
        throw new IllegalIdentifierLengthException();
    return new Token(TokenType.tok_identifier, yytext().toUpperCase()); 
}

. { throw new IllegalSymbolException(); }