import exceptions.*;
import java.io.IOException;

import ast.*;
import java.util.ArrayList;

/**
 * the Oberon Parser Class
 */
public class Parser {
    /**
     * the scanner of the Oberon, return the TokenType by method yylex()
     */
    private OberonScanner scanner;
    /**
     * the moduleBlock is what the parser will return in the future, which is the AST
     */
    private moduleBlock module;
    /**
     * the lookahead symbol
     */
    private Token lookahead;
    /**
     * to determine whether to stop get or not.
     */
    private boolean freeze;

    /**
     * return the next token from the file
     * 
     * @return the next token in Token Form
     * @throws Exception, if IO error or Lex Error
     */
    private Token next() throws Exception {
        if (this.scanner == null) {
            return null;
        }
        if (this.freeze) {
            this.freeze = false;
            return lookahead;
        }
        lookahead = this.scanner.yylex();
        return lookahead;
    }

    /**
     * Parser constructor.
     * 
     * @param scanner, the scanner which use method yylex() to get the next Token
     */
    public Parser(OberonScanner scanner) {
        this.scanner = scanner;
        this.module = null;
        this.lookahead = null;
        this.freeze = false;
    }

    /**
     * To begin parse.
     * 
     * @throws Exception if Syntax Error
     */
    public void parse() throws Exception {
        this.module = this.parseModule();
    }

    /**
     * To get the result of the parser.
     * 
     * @return the module block AST
     */
    public moduleBlock getResult() {
        return this.module;
    }

    /**
     * To parse the module part
     * 
     * @return the module block AST
     * @throws Exception if Grammar is not correct
     */
    private moduleBlock parseModule() throws Exception {
        Token ModuleName = this.next();
        if (ModuleName.getType() != TokenType.tok_module) {
            throw new SyntacticException("Program should start with a module declaration.");
        }
        Token ModuleIdent = this.next();
        if (ModuleIdent.getType() != TokenType.tok_identifier) {
            throw new SyntacticException("Program should start with a module declaration.");
        }
        declarations globalDec = this.parseDeclaration();
        stmts globalState = null;
        Token isBegin = this.next();
        if (isBegin.getType() == TokenType.tok_begin) {
            globalState = parseStatements();
        }
        else {
            this.freeze = true;
            globalState = new stmts();
        }
        Token endPart = this.next();
        if (endPart.getType() != TokenType.tok_end) {
            throw new SyntacticException("Program should end with a module ending.");
        }
        Token endId = this.next();
        if (endId.getType() != TokenType.tok_identifier) {
            throw new SyntacticException("Program should end with a module ending.");
        }
        if (!endId.getVal().equals(ModuleIdent.getVal())) {
            throw new SyntacticException("Program start does not match the end.");
        }
        Token endDot = this.next();
        if (endDot.getType() != TokenType.tok_dot) {
            throw new SyntacticException("Program should end with a module ending.");
        }
        return new moduleBlock((String) ModuleIdent.getVal(), globalDec, globalState);
    }

    /**
     * To parse the declaration part
     * 
     * @return the declaration AST
     * @throws Exception if Grammar is not correct
     */
    private declarations parseDeclaration() throws Exception {
        return new declarations();
    }

    /**
     * 
     */
    private ArrayList<constDec> parseConstDecl() throws Exception {
        return new constDecl();
    }
}
