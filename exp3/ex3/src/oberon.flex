import exceptions.*;
import java.io.*;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%

%line
%column
%ignorecase
%unicode
%class OberonScanner
%standalone
%public
%cup
%char
%implements Symbol
%yylexthrow LexicalException

%{
    public OberonScanner(java.io.Reader in, ComplexSymbolFactory csf) {
        this(in);
        symbolFactory = csf;
    }

    ComplexSymbolFactory symbolFactory;

    public int getLine() { return yyline; }
    public int getCol() { return yycolumn; }

    // public java_cup.runtime.Symbol toSymbol(int type) {
    //     return new java_cup.runtime.Symbol(type);
    // }    

    // public java_cup.runtime.Symbol toSymbol(int type, Object value) {
    //     return new java_cup.runtime.Symbol(type, value);
    // }

    private java_cup.runtime.Symbol symbol(String ident, int type) {
        return symbolFactory.newSymbol(ident, type, new Location(yyline+1,yycolumn+1), new Location(yyline+1,yycolumn+yylength()));
    }

    private java_cup.runtime.Symbol symbol(String ident, int type, Object value) {
        Location left = new Location(yyline+1,yycolumn+1);
        Location right= new Location(yyline+1,yycolumn+yylength());
        return symbolFactory.newSymbol(ident, type, left, right, value);
    }

    private java_cup.runtime.Symbol symbol(String ident, int type, Object val, int buflength) {
        Location left = new Location(yyline+1,yycolumn+yylength()-buflength);
        Location right= new Location(yyline+1,yycolumn+yylength());
        return symbolFactory.newSymbol(ident, type, left, right, val);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t\f] | {LineTerminator}

Comment = "(*"~"*)"
UnMatchedComment = "(*" ([^\*] | "*"+[^\)])* | ([^\(]|"("+[^\*])* "*)"

Identifier = [a-zA-Z][a-zA-Z|0-9]*

Octal = 0[0-7]*
Decimal = [1-9][0-9]*
Number = {Octal}|{Decimal}
IllegalNumber = {Number}+{Identifier}+
IllegalOctal = 0[0-7]*[8|9]+[0-9]*

%eofval{
    // return toSymbol(Symbol.EOF);
    return symbolFactory.newSymbol("EOF", EOF, new Location(yyline+1, yycolumn+1), new Location(yyline+1, yycolumn+1));
%eofval}

%%

{Comment} { 
}

{UnMatchedComment} { 
    throw new MismatchedCommentException(); 
}

"boolean" { 
    return symbol("boolean", BOOLEAN);
}
"integer" { 
    return symbol("integer", INTEGER);
}
"write" { 
    return symbol("write", WRITE);
}
"read" { 
    return symbol("read", READ);
}
"writeln" { 
    return symbol("writeln", WRITELN);
}

"array" { 
    return symbol("array", ARRAY);
}
"of" { 
    return symbol("of", OF);
}
"record" { 
    return symbol("record", RECORD);
}
"var" { 
    return symbol("var", VAR);
}
"procedure" { 
    return symbol("procedure", PROCEDURE);
}
"begin" { 
    return symbol("begin", BEGIN);
}
"end" { 
    return symbol("end", END);
}
"if" { 
    return symbol("if", IF);
}
"then" { 
    return symbol("then", THEN);
}
"else" { 
    return symbol("else", ELSE);
}
"while" { 
    return symbol("while", WHILE);
}
"do" { 
    return symbol("do", DO);
}
"elsif" { 
    return symbol("elsif", ELSIF);
}
"const" { 
    return symbol("const", CONST);
}
"module" { 
    return symbol("module", MODULE);
}
"type" { 
    return symbol("type", TYPE);
}

":=" { 
    return symbol(":=", ASSIGN);
}

"," { 
    return symbol(",", COMMA);
}
"." {
    return symbol(".", DOT);
}
":" { 
    return symbol(":", COLON);
}
";" { 
    return symbol(";", SEMICOLON);
}
"(" { 
    return symbol("(", LPAREN);
}
")" { 
    return symbol(")", RPAREN);
}
"[" { 
    return symbol("[", LBRACKET);
}
"]" { 
    return symbol("]", RBRACKET);
}

"=" { 
    return symbol("=", EQUAL);   
}
"#" { 
    return symbol("#", NOT_EQUAL);
}
"<=" { 
    return symbol("<=", LESS_EQUAL);
}
"<" { 
    return symbol("<", LESS);
}
">=" { 
    return symbol(">=", GREATER_EQUAL);
}
">" { 
    return symbol(">", GREATER);
}
"+" { 
    return symbol("+", ADD);
}
"-" { 
    return symbol("-", MINUS);
}
"*" { 
    return symbol("*", MUL);
}
"div" { 
    return symbol("div", DIV);
}
"mod" { 
    return symbol("mod", MOD);
}
"&" { 
    return symbol("&", AND);
}
"or" { 
    return symbol("or", OR);
}
"~" { 
    return symbol("~", NOT);
}

{Decimal} {
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    Integer value = Integer.parseInt(yytext());
    return symbol("number", NUMBER, value);
}
{Octal} {
    if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    Integer value = Integer.parseInt(yytext(), 8);
    return symbol("number", NUMBER, value);
}
{IllegalNumber} { throw new IllegalIntegerException(); }
{IllegalOctal} { throw new IllegalOctalException(); }

{WhiteSpace} { 
}

{Identifier} { 
    if (yylength() > 24)
        throw new IllegalIdentifierLengthException();
    return symbol("identifier", IDENTIFIER, yytext());
}

. { 
    throw new IllegalSymbolException(); 
}