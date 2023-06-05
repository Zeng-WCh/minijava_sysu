import ast.*;
import exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    private boolean isGlobal;

    private HashMap<String, Integer> globalConstMap;
    private ArrayList<constDec> globalConstList;

    private HashMap<String, Integer> localConstMap;
    private ArrayList<constDec> localConstList;

    private HashMap<String, Integer> globalVarMap;
    private ArrayList<varDec> globalVarList;

    private HashMap<String, Integer> localVarMap;
    private ArrayList<varDec> localVarList;

    private HashMap<String, Integer> globalTypeMap;
    private ArrayList<typeDec> globalTypeList;

    private HashMap<String, Integer> localTypeMap;
    private ArrayList<typeDec> localTypeList;

    private HashMap<String, Integer> globalProcMap;
    private ArrayList<procedureHead> globalProcList;

    private HashMap<String, typeAST> params;

    private typeAST selectTypes;

    private ifStmt currentIf;

    private HashMap<String, ArrayList<actualParameters>> unsolvedProcCalling;
    private HashMap<String, ArrayList<String>> unsolvedProcPos;


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
     * check if argument meets the formal parameters
     * 
     * @param actual, the actual parameters
     * @param formal, the formal parameters
     * @return 0 if matched
     * @return 1 if params number not matched
     * @return 2 if params type not matched
     * @throws Exception, if Syntax Error
     */
    private int checkArgs(actualParameters actual, formalParameters formal) throws Exception {
        ArrayList<fp> fps = formal.fps;
        ArrayList<expr> exprs = actual.exprs;
        if (fps.size() != exprs.size()) {
            return 1;
        }
        for (int i = 0; i < fps.size(); ++i) {
            fp formalP = fps.get(i);
            expr actualP = exprs.get(i);
            if (formalP.isVar && !(actualP.isVar())) {
                return 2;
            }
            if (!formalP.type.name.equals(actualP.getType().name)) {
                return 2;
            }
        }
        return 0;
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
        this.globalConstList = new ArrayList<>();
        this.globalConstMap = new HashMap<>();
        this.localConstList = new ArrayList<>();
        this.localConstMap = new HashMap<>();
        this.globalVarList = new ArrayList<>();
        this.globalVarMap = new HashMap<>();
        this.localVarList = new ArrayList<>();
        this.localVarMap = new HashMap<>();
        this.globalTypeList = new ArrayList<>();
        this.globalTypeMap = new HashMap<>();
        this.localTypeList = new ArrayList<>();
        this.localTypeMap = new HashMap<>();
        this.globalProcList = new ArrayList<>();
        this.globalProcMap = new HashMap<>();
        this.params = null;
        this.isGlobal = true;
        this.selectTypes = null;
        this.currentIf = null;
        this.unsolvedProcCalling = new HashMap<>();
        this.unsolvedProcPos = new HashMap<>();
    }

    /**
     * To begin parse.
     * 
     * @throws Exception if Syntax Error
     */
    public void parse() throws Exception {
        this.module = this.parseModule();
        Token isEof = this.next();
        if (isEof.getType() != TokenType.tok_eof) {
            throw new SyntacticException("Unexpected token at the end of the program.");
        }

        for (String ident : this.unsolvedProcCalling.keySet()) {
            Integer idx = this.globalProcMap.get(ident);
            if (idx == null) {
                throw new SyntacticException(String.format("Procedure " + ident + " is not declared. (%s)", this.unsolvedProcPos.get(ident).get(0).toString()));
            }
            procedureHead head = this.globalProcList.get(idx);
            for (int i = 0; i < this.unsolvedProcCalling.get(ident).size(); ++i) {
                actualParameters actual = this.unsolvedProcCalling.get(ident).get(i);
                int result = this.checkArgs(actual, head.fp);
                if (result == 1) {
                    throw new SyntacticException(String.format("Procedure " + ident + " is called with wrong number of arguments. (%s)", this.unsolvedProcPos.get(ident).get(i).toString()));
                }
                else if (result == 2) {
                    throw new SyntacticException(String.format("Procedure " + ident + " is called with wrong type of arguments. (%s)", this.unsolvedProcPos.get(ident).get(i).toString()));
                }
            }
        }
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
        Token isSemicolon = this.next();
        if (isSemicolon.getType() != TokenType.tok_semicolon) {
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
        ArrayList<constDec> constList = new ArrayList<>();
        ArrayList<typeDec> typeDecs = new ArrayList<>();
        ArrayList<varDec> varDecs = new ArrayList<>();
        ArrayList<procedureDec> procDecs = new ArrayList<>();
        Token isConst = this.next();
        if (isConst.getType() != TokenType.tok_const) {
            this.freeze = true;
        }
        else {
            constList = this.parseConstDecl();
            // Token semi = this.next();
            // if (semi.getType() != TokenType.tok_semicolon) {
            //     throw new SyntacticException("Const declaration should end with a semicolon.");
            // }
        }
        Token isType = this.next();
        if (isType.getType() != TokenType.tok_type) {
            this.freeze = true;
        }
        else {
            typeDecs = this.parseTypeDecl();
        }
        Token isVar = this.next();
        if (isVar.getType() != TokenType.tok_var) {
            this.freeze = true;
        }
        else {
            varDecs = this.parseVarDecl();
        }
        Token isProc = this.next();
        if (isProc.getType() != TokenType.tok_procedure) {
            this.freeze = true;
        }
        else {
            this.freeze = true;
            procDecs = this.parseProcDecl();
        }

        return new declarations(constList, typeDecs, varDecs, procDecs);
    }

    /**
     * 
     */
    private ArrayList<constDec> parseConstDecl() throws Exception {
        ArrayList<constDec> ret = new ArrayList<>();
        Token ident = this.next();
        if (ident.getType() != TokenType.tok_identifier) {
            this.freeze = true;
            return ret;
        }
        Token equal = this.next();
        if (equal.getType() != TokenType.tok_equal) {
            throw new MissingOperatorException("Missing '=' for const declarations.");
        }
        expr constExpr = this.parseExpression();
        if (!constExpr.isConstant) {
            throw new TypeMismatchedException("Const declaration should be a constant expression.");
        }
        Token semi = this.next();
        if (semi.getType() != TokenType.tok_semicolon) {
            throw new SyntacticException("Const declaration should end with a semicolon.");
        }
        constDec constDec = new constDec((String) ident.getVal(), constExpr);
        if (isGlobal) {
            this.globalConstList.add(constDec);
            this.globalConstMap.put((String) ident.getVal(), this.globalConstList.size() - 1);
        }
        else {
            this.localConstList.add(constDec);
            this.localConstMap.put((String) ident.getVal(), this.localConstList.size() - 1);
        }
        ArrayList<constDec> constDecTail = this.parseConstDecTail();
        ret.add(constDec);
        ret.addAll(constDecTail);
        return ret;
    }

    private ArrayList<constDec> parseConstDecTail() throws Exception {
        // ArrayList<constDec> ret = new ArrayList<>();
        // Token ident = this.next();
        // if (ident.getType() != TokenType.tok_identifier) {
        //     this.freeze = true;
        //     return ret;
        // }
        // Token equal = this.next();
        // if (equal.getType() != TokenType.tok_equal) {
        //     throw new MissingOperatorException("Missing '=' for const declarations.");
        // }
        // expr constExpr = this.parseExpression();
        // Token semi = this.next();
        // if (semi.getType() != TokenType.tok_semicolon) {
        //     throw new SyntacticException("Const declaration should end with a semicolon.");
        // }
        // constDec constDec = new constDec((String) ident.getVal(), constExpr);
        // ArrayList<constDec> constDecTail = this.parseConstDecTail();
        // ret.add(constDec);
        // ret.addAll(constDecTail);
        // return ret;
        return this.parseConstDecl();
    }

    private ArrayList<typeDec> parseTypeDecl() throws Exception {
        ArrayList<typeDec> ret = new ArrayList<>();
        Token ident = this.next();
        if (ident.getType() != TokenType.tok_identifier) {
            this.freeze = true;
            return ret;
        }
        Token equal = this.next();
        if (equal.getType() != TokenType.tok_equal) {
            throw new MissingOperatorException("Missing '=' for type declarations.");
        }
        typeAST t = this.parseType();
        Token semi = this.next();
        if (semi.getType() != TokenType.tok_semicolon) {
            throw new SyntacticException("Type declaration should end with a semicolon.");
        }
        typeDec types = new typeDec((String) ident.getVal(), t);
        if (isGlobal) {
            this.globalTypeList.add(types);
            this.globalTypeMap.put((String) ident.getVal(), this.globalTypeList.size() - 1);
        }
        else {
            this.localTypeList.add(types);
            this.localTypeMap.put((String) ident.getVal(), this.localTypeList.size() - 1);
        }
        ret.add(types);
        ArrayList<typeDec> typeDecTail = this.parseTypeDecTail();
        ret.addAll(typeDecTail);
        return ret;
    }

    private ArrayList<typeDec> parseTypeDecTail() throws Exception {
        // ArrayList<typeDec> ret = new ArrayList<>();
        // Token ident = this.next();
        // if (ident.getType() != TokenType.tok_identifier) {
        //     this.freeze = true;
        //     return ret;
        // }
        // Token equal = this.next();
        // if (equal.getType() != TokenType.tok_equal) {
        //     throw new SyntacticException("Missing '=' for type declarations.");
        // }
        // typeAST t = this.parseType();
        // Token semi = this.next();
        // if (semi.getType() != TokenType.tok_semicolon) {
        //     throw new SyntacticException("Type declaration should end with a semicolon.");
        // }
        // typeDec types = new typeDec((String) ident.getVal(), t);
        // ret.add(types);
        // ArrayList<typeDec> typeDecTail = this.parseTypeDecTail();
        // ret.addAll(typeDecTail);
        // return ret;
        return parseTypeDecl();
    }

    private ArrayList<varDec> parseVarDecl() throws Exception {
        ArrayList<varDec> ret = new ArrayList<>();
        identifierList idList = this.parseIdentifierList();
        Token colon = this.next();
        if (colon.getType() != TokenType.tok_colon) {
            throw new SyntacticException("Missing ':' for var declarations.");
        }
        typeAST t = this.parseType();
        Token semi = this.next();
        if (semi.getType() != TokenType.tok_semicolon) {
            throw new SyntacticException("Var declaration should end with a semicolon.");
        }
        varDec var = new varDec(idList, t);
        if (isGlobal) {
            int size = this.globalVarList.size();
            for (String id : idList.identifiers) {
                Integer idx = this.globalVarMap.get(id);
                if (idx != null) {
                    throw new SyntacticException("Duplicate identifier '" + id + "' in global scope.");
                }
                this.globalVarMap.put(id, size);
            }
            this.globalVarList.add(var);

        }
        else {
            int size = this.localVarList.size();
            for (String id : idList.identifiers) {
                Integer idx = this.localVarMap.get(id);
                if (idx != null) {
                    throw new SyntacticException("Duplicate identifier '" + id + "' in local scope.");
                }
                this.localVarMap.put(id, size);
            }
            this.localVarList.add(var);
        }
        ret.add(var);
        ArrayList<varDec> varDecTail = this.parseVarDecTail();
        ret.addAll(varDecTail);
        return ret;
    }

    private ArrayList<varDec> parseVarDecTail() throws Exception {
        Token ident = this.next();
        ArrayList<varDec> ret = new ArrayList<>();
        if (ident.getType() != TokenType.tok_identifier) {
            this.freeze = true;
            return ret;
        }
        this.freeze = true;
        identifierList idList = this.parseIdentifierList();
        Token colon = this.next();
        if (colon.getType() != TokenType.tok_colon) {
            throw new SyntacticException("Missing ':' for var declarations.");
        }
        typeAST t = this.parseType();
        Token semi = this.next();
        if (semi.getType() != TokenType.tok_semicolon) {
            throw new SyntacticException("Var declaration should end with a semicolon.");
        }
        varDec var = new varDec(idList, t);
        if (isGlobal) {
            this.globalVarList.add(var);
            for (String id : idList.identifiers) {
                this.globalVarMap.put(id, this.globalVarList.size() - 1);
            }
        }
        else {
            this.localVarList.add(var);
            for (String id : idList.identifiers) {
                this.localVarMap.put(id, this.localVarList.size() - 1);
            }
        }
        ret.add(var);
        ArrayList<varDec> varDecTail = this.parseVarDecTail();
        ret.addAll(varDecTail);
        return ret;
    }

    private ArrayList<procedureDec> parseProcDecl() throws Exception {
        ArrayList<procedureDec> ret = new ArrayList<>();
        procedureHead head = this.parseProHead();
        if (head == null) {
            this.freeze = true;
            return ret;
        }
        Token isSemiToken = this.next();
        if (isSemiToken.getType() != TokenType.tok_semicolon) {
            throw new SyntacticException("Procedure declaration should end with a semicolon.");
        }

        
        this.globalProcMap.put(head.name, this.globalProcList.size());
        this.globalProcList.add(head);

        this.isGlobal = false;
        procedureBody body = this.parseProBody();
        if (!head.name.equals(body.name)) {
            throw new SyntacticException("Procedure name mismatch.");
        }
        Token semi = this.next();
        if (semi.getType() != TokenType.tok_semicolon) {
            throw new SyntacticException("Procedure declaration should end with a semicolon.");
        }
        procedureDec proc = new procedureDec(head, body);
        ret.add(proc);
        ArrayList<procedureDec> procDecTail = this.parseProcDecTail();
        ret.addAll(procDecTail);
        return ret;
    }

    private ArrayList<procedureDec> parseProcDecTail() throws Exception {
        // ArrayList<procedureDec> ret = new ArrayList<>();
        // procedureHead head = this.parseProHead();
        // if (head == null) {
        //     this.freeze = true;
        //     return ret;
        // }
        // this.isGlobal = false;
        // procedureBody body = this.parseProBody();
        // if (!head.name.equals(body.name)) {
        //     throw new SyntacticException("Procedure name mismatch.");
        // }
        // Token semi = this.next();
        // if (semi.getType() != TokenType.tok_semicolon) {
        //     throw new SyntacticException("Procedure declaration should end with a semicolon.");
        // }
        // procedureDec proc = new procedureDec(head, body);
        // this.globalProcList.add(head);
        // this.globalProcMap.put(head.name, this.globalProcList.size() - 1);
        // ret.add(proc);
        // ArrayList<procedureDec> procDecTail = this.parseProcDecTail();
        // ret.addAll(procDecTail);
        // return ret;
        return this.parseProcDecl();
    }

    private procedureHead parseProHead() throws Exception {
        Token proc = this.next();
        if (proc.getType() != TokenType.tok_procedure) {
            this.freeze = true;
            return null;
        }

        Token ident = this.next();
        if (ident.getType() != TokenType.tok_identifier) {
            throw new SyntacticException("Missing procedure name.");
        }

        formalParameters params = this.parseFormalParam();
        params.trans();

        return new procedureHead((String) ident.getVal(), params);
    }

    private procedureBody parseProBody() throws Exception {
        declarations localDec = this.parseDeclaration();
        Token isBegin = this.next();
        stmts s = null;
        if (isBegin.getType() != TokenType.tok_begin) {
            this.freeze = true;
            s = new stmts();
        }
        else {
            s = this.parseStatements();
        }
        Token isEnd = this.next();
        if (isEnd.getType() != TokenType.tok_end) {
            throw new SyntacticException("Procedure body should end with 'end {ident}'.");
        }
        Token ident = this.next();
        if (ident.getType() != TokenType.tok_identifier) {
            throw new SyntacticException("Procedure body should end with 'end {ident}'.");
        }

        return new procedureBody(localDec, s, (String) ident.getVal());
    }

    private stmts parseStatements() throws Exception {
        ArrayList<stmt> stmtList = new ArrayList<>();
        Token semi = null;
        do {
            stmt s = this.parseStatement();
            stmtList.add(s);
            semi = this.next();
        } while (semi.getType() == TokenType.tok_semicolon);
        this.freeze = true;
        return new stmts(stmtList);
    }

    private stmt parseStatement() throws Exception {
        Token nextFlag = this.next();
        if(nextFlag.getType() == TokenType.tok_while) {
            this.freeze = true;
            return new stmt(this.parseWhileStmt());
        }
        else if(nextFlag.getType() == TokenType.tok_if) {
            this.freeze = true;
            return new stmt(this.parseIfStmt());
        }
        else if(nextFlag.getType() == TokenType.tok_identifier) {
            // 2 ways, assignment or call
            String ident = (String) nextFlag.getVal();
            Token nextNextFlag = this.next();
            if (nextNextFlag.getType() == TokenType.tok_lbracket || nextNextFlag.getType() == TokenType.tok_dot || nextNextFlag.getType() == TokenType.tok_assign) {
                // if (nextNextFlag.getType() != TokenType.tok_assign)
                this.freeze = true;
                return new stmt(this.parseAssignmentStmt(ident));
            }
            this.freeze = true;
            return new stmt(this.parseCallStmt(ident));
        }
        else if (nextFlag.getType() == TokenType.tok_write || nextFlag.getType() == TokenType.tok_read || nextFlag.getType() == TokenType.tok_writeln) {
            this.freeze = true;
            return new stmt(this.parseIOStmt());
        }
        throw new SyntacticException("Invalid statement.");
    }

    private assignmentStmt parseAssignmentStmt(String ident) throws Exception {
        Integer idx = null;
        typeAST type = null;
        varDec var = null;

        type = this.params.get(ident);

        if (type != null) {
            
        }
        else {
            // check if it is a local variable
            idx = localVarMap.get(ident);
            
            if (idx == null) {
                // global Var
                idx = globalVarMap.get(ident);
                // constant, but not allowed to assign, so throw exception
                if (idx == null) {
                    idx = globalConstMap.get(ident);
                    if (idx != null) {
                        throw new SyntacticException(String.format("Global constant %s cannot be assigned.", ident));
                    }
                    idx = localConstMap.get(ident);
                    if (idx != null) {
                        throw new SyntacticException(String.format("Local constant %s cannot be assigned.", ident));
                    }
                    throw new SyntacticException(String.format("Variable %s is not declared.", ident));
                }
                var = this.globalVarList.get(idx);
            }
            if (var == null) {
                var = this.localVarList.get(idx);
            }
        }
        this.selectTypes = null;
        selectorAST select = this.parseSelect(ident, var);

        Token assign = this.next();
        
        if (assign.getType() != TokenType.tok_assign) {
            throw new SyntacticException("Missing assign(':=') for assignment.");
        }

        expr e = this.parseExpression();

        
        if (select.selectors.isEmpty()) {
            if (type != null) {
                if (!type.name.equals(e.getType().name)) {
                    throw new TypeMismatchedException(String.format("Type mismatched in assignment to %s.", ident));
                }
            }
            else if (!e.getType().name.equals(var.type.name)) {
                throw new TypeMismatchedException(String.format("Type mismatched in assignment to %s.", ident));
            }
        }
        else {
            if (type != null) {
                if (!type.name.equals(this.selectTypes.name)) {
                    throw new TypeMismatchedException(String.format("Type mismatched in assignment to %s.", ident));
                }
            }
            else if (!e.getType().name.equals(this.selectTypes.name)) {
                throw new TypeMismatchedException(String.format("Type mismatched in assignment to %s.", ident));
            }
        }
        
        return new assignmentStmt(ident, select, e);
    }

    private callStmt parseCallStmt(String ident) throws Exception {        
        Integer idx = globalProcMap.get(ident);
        boolean unfound = false;
        if (idx == null) {
            unfound = true;
        }

        actualParameters params = this.parseActualParam();
        if (unfound) {
            if (this.unsolvedProcCalling.get(ident) == null) {
                this.unsolvedProcCalling.put(ident, new ArrayList<>());
                this.unsolvedProcPos.put(ident, new ArrayList<>());
            }
            this.unsolvedProcCalling.get(ident).add(params);
            this.unsolvedProcPos.get(ident).add(String.format("<%d:%d>", this.scanner.getLine(), this.scanner.getCol()));
            return new callStmt(ident, params);
        }

        procedureHead proc = this.globalProcList.get(idx);
        formalParameters fp = proc.fp;
        int flag = checkArgs(params, fp);
        if (flag == 1) {
            throw new ParameterMismatchedException(String.format("Parameter mismatched in %s call.", ident));
        }
        else if (flag == 2) {
            throw new TypeMismatchedException(String.format("Type mismatched in %s call.", ident));
        }
        return new callStmt(ident, params);

    }

    private ifStmt parseIfStmt() throws Exception {
        Token ifF = this.next();
        if (ifF.getType() != TokenType.tok_if) {
            throw new SyntacticException("Invalid if statement.");
        }
        expr condition = this.parseExpression();
        if (!condition.getType().name.equals("BOOLEAN")) {
            throw new TypeMismatchedException("Condition in if statement should be boolean expression.");
        }
        Token thenF = this.next();
        if (thenF.getType() != TokenType.tok_then) {
            throw new SyntacticException("Invalid if statement.");
        }
        stmts thenStmts = this.parseStatements();
        this.currentIf = new ifStmt(condition, thenStmts);
        Token elseF = this.next();
        if (elseF.getType() == TokenType.tok_elsif) {
            this.freeze = true;
            this.parseElseIf();
        }
        else {
            this.freeze = true;
        }

        Token elsePart = this.next();
        if (elsePart.getType() == TokenType.tok_else) {
            this.freeze = true;
            this.parseElse();
        }
        else {
            this.freeze = true;
        }
        Token end = this.next();
        if (end.getType() != TokenType.tok_end) {
            throw new SyntacticException("If Statement should end with an 'end'.");
        }
        ifStmt ret = currentIf;
        currentIf = null;
        return ret;
    }

    private whileStmt parseWhileStmt() throws Exception {
        Token whileFlag = this.next();
        if (whileFlag.getType() != TokenType.tok_while) {
            throw new SyntacticException("Invalid while statement.");
        }
        expr condition = this.parseExpression();
        if (!condition.getType().name.equals("BOOLEAN")) {
            throw new TypeMismatchedException("Condition in while statement should be boolean expression.");
        }
        Token doFlag = this.next();
        if (doFlag.getType() != TokenType.tok_do) {
            throw new SyntacticException("Invalid while statement.");
        }
        stmts body = this.parseStatements();
        Token end = this.next();
        if (end.getType() != TokenType.tok_end) {
            throw new SyntacticException("While Statement should end with an 'end'.");
        }
        return new whileStmt(condition, body);
    }

    private callStmt parseIOStmt() throws Exception {
        Token IOType = this.next();
        if (IOType.getType() != TokenType.tok_write && IOType.getType() != TokenType.tok_read && IOType.getType() != TokenType.tok_writeln) {
            throw new SyntacticException("Invalid IO statement.");
        }
        if (IOType.getType() == TokenType.tok_write || IOType.getType() == TokenType.tok_read) {
            actualParameters params = this.parseActualParam();
            if (params.exprs.size() != 1) {
                throw new ParameterMismatchedException(String.format("Invalid %s call.", (String) IOType.getVal()));
            }
            return new callStmt((String) IOType.getVal(), params);
        }
        // writeln, just judge it specially
        Token isLparen = this.next();
        if (isLparen.getType() != TokenType.tok_lparen) {
            if (isLparen.getType() == TokenType.tok_rparen) {
                throw new MissingRightParenthesisException();
            }
            // then now '()' part
            this.freeze = true;
            return new callStmt((String) IOType.getVal(), new actualParameters());
        }
        Token isRparen = this.next();
        if (isRparen.getType() != TokenType.tok_rparen) {
            throw new MissingRightParenthesisException("Invalid Writeln func call.");
        }
        return new callStmt((String) IOType.getVal(), new actualParameters());
    }

    private selectorAST parseSelect(String ident, varDec var) throws Exception {
        this.selectTypes = null;
        Token next = this.next();

        typeAST type = null;
        if (var != null) {
            type = var.type;
        }
        selectorAST sel = new selectorAST();
        while (next.getType() == TokenType.tok_dot || next.getType() == TokenType.tok_lbracket) {
            if (next.getType() == TokenType.tok_dot) {
                if (var == null) {
                    throw new SyntacticException("CONSTANT or TYPE cannot be selected.");
                }
                // field
                if (!type.name.equals("RECORD")) {
                    throw new TypeMismatchedException(String.format("Expected a record type, but got %s. (Variable: %s)", type.name, ident));
                }
                Token field = this.next();
                if (field.getType() != TokenType.tok_identifier) {
                    throw new SyntacticException(String.format("Expected a field name for field selector(.)."));
                }
                String fieldName = (String) field.getVal();

                boolean found = false;
                typeAST fieldType = null;

                if (!(type.type instanceof recordType)) {
                    throw new TypeMismatchedException("RECORD selector(.) can only be applied to record type.");
                }

                for (fieldList fd : ((recordType) type.type).fieldLists) {
                    if (found) 
                        break;
                    for (String idinField : fd.ids.identifiers) {
                        if (idinField.equals(fieldName)) {
                            fieldType = fd.type;
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    throw new SyntacticException(String.format("Field %s not found in record %s.", fieldName, type.name));
                }

                type = fieldType;

                sel.selectors.add(fieldName);
                sel.types.add(0);
            }
            else if (next.getType() == TokenType.tok_lbracket) {
                // array
                if (var == null) {
                    throw new SyntacticException("CONSTANT or TYPE cannot be selected.");
                }
                if (!(type.type instanceof arrayType)) {
                    throw new TypeMismatchedException("ARRAY selector([]) can only be applied to array type.");
                }
                if (!type.name.equals("ARRAY")) {
                    throw new TypeMismatchedException(String.format("Expected a array type, but got %s. (Variable: %s)", type.name, ident));
                }

                expr index = this.parseExpression();
                if (!index.getType().name.equals("INTEGER")) {
                    throw new TypeMismatchedException(String.format("Expected a integer type for array index, but got %s.", index.getType().name));
                }

                sel.selectors.add(index);
                sel.types.add(1);

                Token rbracket = this.next();
                if (rbracket.getType() != TokenType.tok_rbracket) {
                    throw new SyntacticException(String.format("Expected a ']' for array selector([])."));
                }

                type = ((arrayType) type.type).type;
            }
            next = this.next();
        }
        this.selectTypes = type;
        this.freeze = true;
        return sel;
    }

    private expr parseExpression() throws Exception {
        simpleExpr left = this.parseSimpleExpr();
        Token op = this.next();
        String opString = null;

        switch (op.getType()) {
            case tok_equal:
                opString = "=";
                break;
            case tok_not_equal:
                opString = "#";
                break;
            case tok_less:
                opString = "<";
                break;
            case tok_less_equal:
                opString = "<=";
                break;
            case tok_greater:
                opString = ">";
                break;
            case tok_greater_equal:
                opString = ">=";
                break;
            case tok_number:
            case tok_identifier:
                throw new MissingOperatorException();
            default:
                break;
        }

        if (opString == null) {
            this.freeze = true;
            return new expr(left);
        }

        simpleExpr right = this.parseSimpleExpr();

        if (left.getType().name.equals("BOOLEAN") || right.getType().name.equals("BOOLEAN")) {
            throw new TypeMismatchedException(String.format("Boolean expression cannot be compared with %s.", opString));
        }

        return new expr(left, opString, right);
    }

    private simpleExpr parseSimpleExpr() throws Exception {
        Token sign = this.next();
        String signed = null;
        if (sign.getType() != TokenType.tok_plus || sign.getType() != TokenType.tok_minus) {
            this.freeze = true;
            sign = null;
        }
        else {
            signed = (String) sign.getVal();
        }

        termAST left = this.parseTerm();

        if (signed != null && !left.getType().name.equals("INTEGER")) {
            throw new TypeMismatchedException("Signed expression cannot be applied to arith expression.");
        }
        Token op = this.next();
        String opString = null;

        switch (op.getType()) {
            case tok_plus:
                opString = "+";
                break;
            case tok_minus:
                opString = "-";
                break;
            case tok_or:
                opString = "OR";
                break;
            case tok_number:
            case tok_identifier:
                throw new MissingOperatorException();
            default:
                break;
        }

        if (opString == null) {
            this.freeze = true;
            return new simpleExpr(signed, left);
        }

        simpleExpr ret = new simpleExpr(signed, left);

        while (op.getType() == TokenType.tok_plus || op.getType() == TokenType.tok_minus || op.getType() == TokenType.tok_or) {
            opString = (String) op.getVal();
            termAST right = this.parseTerm();
            
            if (op.getType() == TokenType.tok_plus || op.getType() == TokenType.tok_minus) {
                // arith
                if (!left.getType().name.equals("INTEGER") || !right.getType().name.equals("INTEGER")) {
                    throw new TypeMismatchedException("Arith expression cannot be applied to boolean expression.");
                }
            }
            else {
                // boolean
                if (!left.getType().name.equals("BOOLEAN") || !right.getType().name.equals("BOOLEAN")) {
                    throw new TypeMismatchedException("Boolean expression cannot be applied to arith expression.");
                }
            }

            ret.op.add(opString);
            ret.rhs.add(right);

            op = this.next();
        }

        this.freeze = true;
        return ret;
    }

    private termAST parseTerm() throws Exception {
        factorAST lhs = this.parseFactor();
        Token op = this.next();
        String opString = null;

        if (op.getType() == TokenType.tok_identifier || op.getType() == TokenType.tok_number) {
            throw new MissingOperatorException();
        }
        
        if (op.getType() == TokenType.tok_multiply || op.getType() == TokenType.tok_divide || op.getType() == TokenType.tok_mod || op.getType() == TokenType.tok_and) {
            opString = (String) op.getVal();
        }
        else {
            this.freeze = true;
            return new termAST(lhs);
        }

        termAST ret = new termAST(lhs);

        while (op.getType() == TokenType.tok_multiply || op.getType() == TokenType.tok_divide || op.getType() == TokenType.tok_mod || op.getType() == TokenType.tok_and) {
            opString = (String) op.getVal();
            
            factorAST rhs = this.parseFactor();

            if (op.getType() == TokenType.tok_identifier || op.getType() == TokenType.tok_number) {
                throw new MissingOperatorException();
            }

            if (op.getType() == TokenType.tok_multiply || op.getType() == TokenType.tok_divide || op.getType() == TokenType.tok_mod) {
                // arith
                if (!lhs.getType().name.equals("INTEGER") || !rhs.getType().name.equals("INTEGER")) {
                    throw new TypeMismatchedException("Arith expression cannot be applied to boolean expression.");
                }
            }
            else {
                // boolean
                if (!lhs.getType().name.equals("BOOLEAN") || !rhs.getType().name.equals("BOOLEAN")) {
                    throw new TypeMismatchedException("Boolean expression cannot be applied to arith expression.");
                }
            }

            ret.op.add(opString);
            ret.rhs.add(rhs);
            op = this.next();
        }

        this.freeze = true;

        return ret;
    }

    private factorAST parseFactor() throws Exception {
        Token flag = this.next();

        switch (flag.getType()) {
            case tok_and:
            case tok_or:
            case tok_plus:
            case tok_minus:
            case tok_multiply:
            case tok_divide:
            case tok_greater:
            case tok_greater_equal:
            case tok_less:
            case tok_less_equal:
            case tok_equal:
            case tok_not_equal:
            case tok_mod:
            case tok_then:
            case tok_do:
                throw new MissingOperandException();
            default:
                break;
        }

        if (flag.getType() == TokenType.tok_identifier) {
            // identifier selector
            // first find the identifier
            String id = (String) flag.getVal();
            // check if the identifier is declared
            Integer idx = null;
            varDec var = null;
            constDec constV = null;
            typeAST generate = null;
            idx = this.localVarMap.get(id);
            
            generate = this.params.get(id);

            if (generate == null) {
                if (idx == null) {
                    // try global
                    idx = this.globalVarMap.get(id);
                    if (idx == null) {
                        // try const
                        idx = this.localConstMap.get(id);
                        if (idx == null) {
                            idx = this.globalConstMap.get(id);
                            if (idx == null) {
                                throw new SyntacticException(String.format("Identifier %s is not declared.", id));
                            }
                            else {
                                constV = this.globalConstList.get(idx);
                            }
                        }
                        else {
                            constV = this.localConstList.get(idx);
                        }
                    }
                    else {
                        var = this.globalVarList.get(idx);
                    }
                }
                else {
                    var = this.localVarList.get(idx);
                }
            }

            selectorAST select = this.parseSelect(id, var);
            
            if (generate != null) {
                factorAST f = new factorAST(id, select, false);
                f.typeGenerate = generate;
                return f;
            }

            if (var != null) {
                if (this.selectTypes != null) {
                    generate = this.selectTypes;
                }
                else {
                    generate = var.type;
                }
            }
            else {
                generate = new typeAST("INTEGER");
            }


            factorAST f = new factorAST(id, select, false);
            f.isConstant = (constV != null);
            f.typeGenerate = generate;
            return f;
        }
        else if (flag.getType() == TokenType.tok_number) {
            // number
            numberAST num = new numberAST((Integer) flag.getVal());
            factorAST f = new factorAST(num, false);
            f.isConstant = true;
            f.typeGenerate = new typeAST("INTEGER");
            return f;
        }
        else if (flag.getType() == TokenType.tok_lparen) {
            // ( expression )
            expr e = this.parseExpression();
            Token rparen = this.next();
            if (rparen.getType() != TokenType.tok_rparen) {
                throw new MissingRightParenthesisException("Expected a )");
            }
            return new factorAST(e, false);
        }
        else if (flag.getType() == TokenType.tok_not) {
            // ~ factor
            factorAST f = this.parseFactor();

            if (!f.getType().name.equals("BOOLEAN")) {
                throw new TypeMismatchedException("Arith expression cannot be applied to boolean expression.");
            }

            f.negated = true;
            return f;
        }
        else {
            throw new MissingLeftParenthesisException("Empty Expression Detected.");
        }
    }
    
    private typeAST parseType() throws Exception {
        Token next = this.next();
        if (next.getType() == TokenType.tok_identifier) {
            // identifier
            String ident = (String) next.getVal();
            // check if the type has been declarated
            Integer idx = null;
            idx = this.localTypeMap.get(ident);
            typeDec type = null;
            if (idx == null) {
                idx = this.globalTypeMap.get(ident);
                if (idx == null) {
                    throw new SyntacticException(String.format("Type %s is not declarated.", ident));
                }
                type = this.globalTypeList.get(idx);
            }
            else {
                type = this.localTypeList.get(idx);
            }
            
            return new typeAST(type.type.name, type.type.type);
        }
        else if (next.getType() == TokenType.tok_integer) {
            return new typeAST("INTEGER");
        }
        else if (next.getType() == TokenType.tok_boolean) {
            return new typeAST("BOOLEAN");
        }
        else if (next.getType() == TokenType.tok_array) {
            // array
            this.freeze = true;
            arrayType ar = this.parseArrayType();
            return new typeAST("ARRAY", ar);
        }
        else if (next.getType() == TokenType.tok_record) {
            // record
            this.freeze = true;
            recordType rec = this.parseRecordType();
            return new typeAST("RECORD", rec);
        }
        else {
            throw new SyntacticException("Missing Type info.");
        }
    }
    
    private arrayType parseArrayType() throws Exception {
        Token array = this.next();

        if (array.getType() != TokenType.tok_array) {
            throw new SyntacticException("Expected 'array' when declarated a array type.");
        }

        expr length = this.parseExpression();

        Token of = this.next();
        if (of.getType() != TokenType.tok_of) {
            throw new SyntacticException("Expected 'of' when declarated a array type.");
        }

        typeAST type = this.parseType();
        return new arrayType(length, type);
    }

    private recordType parseRecordType() throws Exception {
        Token record = this.next();
        if (record.getType() != TokenType.tok_record) {
            throw new SyntacticException("Expected 'record' when declarated a record type.");
        }

        Token isEnd = null;
        ArrayList<fieldList> fieldList = new ArrayList<>();
        do {
            fieldList fl = this.parseFieldList();
            fieldList.add(fl);
            isEnd = this.next();
            if (isEnd.getType() == TokenType.tok_end) {
                break;
            }
            else if (isEnd.getType() != TokenType.tok_semicolon) {
                throw new SyntacticException("Expected ';' when declarated a record type.");
            }
        } while(isEnd.getType() != TokenType.tok_end);

        return new recordType(fieldList);
    }

    private fieldList parseFieldList() throws Exception {
        identifierList idList = this.parseIdentifierList();
        Token colon = this.next();
        if (colon.getType() != TokenType.tok_colon) {
            throw new SyntacticException("Expected ':' when declarated a field list.");
        }
        typeAST type = this.parseType();
        return new fieldList(idList, type);
    }

    private identifierList parseIdentifierList() throws Exception {
        Token Id = this.next();
        ArrayList<String> idList = new ArrayList<>();
        if (Id.getType() == TokenType.tok_begin) {
            throw new MissingRightParenthesisException();
        }
        if (Id.getType() != TokenType.tok_identifier) {
            throw new SyntacticException("Expected a identifier when declarated a identifier list.");
        }
        idList.add((String) Id.getVal());
        Token isComma = this.next();
        while (isComma.getType() == TokenType.tok_comma) {
            Id = this.next();
            if (Id.getType() == TokenType.tok_begin) {
                throw new MissingRightParenthesisException();
            }
            if (Id.getType() != TokenType.tok_identifier) {
                throw new SyntacticException("Expected a identifier when declarated a identifier list.");
            }            
            idList.add((String) Id.getVal());
            isComma = this.next();
        }
        this.freeze = true;
        return new identifierList(idList);
    }

    private formalParameters parseFormalParam() throws Exception {
        this.params = new HashMap<>();
        Token lparen = this.next();
        ArrayList<fpSection> fpList = new ArrayList<>();
        if (lparen.getType() != TokenType.tok_lparen) {
            if (lparen.getType() != TokenType.tok_begin && lparen.getType() != TokenType.tok_end && lparen.getType() != TokenType.tok_semicolon) {
                throw new MissingLeftParenthesisException();
            }
            this.freeze = true;
            return new formalParameters(fpList);
        }
        Token rp = this.next();
        if (rp.getType() == TokenType.tok_rparen) {
            return new formalParameters(fpList);
        }

        this.freeze = true;

        // fpSection fp = this.parseFpSection();
        fpSection fp = null;
        Token semicolon = null;

        do {
            fp = this.parseFpSection();
            fpList.add(fp);
            semicolon = this.next();
        } while(semicolon.getType() == TokenType.tok_semicolon);

        this.freeze = true;

        Token rparen = this.next();
        if (rparen.getType() != TokenType.tok_rparen) {
            throw new MissingRightParenthesisException("Expected a ')' when declarated a formal parameters.");
        }

        for (fpSection fpst : fpList) {
            for (String paramName : fpst.identifierList.identifiers) {
                if (this.params.containsKey(paramName)) {
                    throw new SyntacticException(String.format("Parameter %s has been declarated.", paramName));
                }
                this.params.put(paramName, fpst.type);
            }
        }

        return new formalParameters(fpList);
    }

    private fpSection parseFpSection() throws Exception {
        Token isVar = this.next();
        boolean var = false;
        if (isVar.getType() == TokenType.tok_var) {
            var = true;
        }
        else {
            this.freeze = true;
        }

        identifierList idList = this.parseIdentifierList();

        Token colon = this.next();
        if (colon.getType() != TokenType.tok_colon) {
            throw new SyntacticException("Expected ':' when declarated a formal parameters.");
        }

        typeAST type = this.parseType();
        // Token semi = this.next();

        // if (semi.getType() != TokenType.tok_semicolon) {
        //     throw new SyntacticException("Expected ';' when declarated a formal parameters.");
        // }

        return new fpSection(var, idList, type);
    }

    private actualParameters parseActualParam() throws Exception {
        Token lparen = this.next();
        ArrayList<expr> acList = new ArrayList<>();
        if (lparen.getType() != TokenType.tok_lparen) {
            throw new MissingLeftParenthesisException("Expected a '(' when declarated a formal parameters.");
        }

        // expr e = this.parseExpression();

        Token comma = null;
        expr e = null;

        do {
            e = this.parseExpression();
            acList.add(e);
            comma = this.next();
        } while (comma.getType() == TokenType.tok_comma);

        this.freeze = true;

        Token rparen = this.next();
        if (rparen.getType() != TokenType.tok_rparen) {
            throw new MissingRightParenthesisException("Expected a ')' when declarated a formal parameters.");
        }

        return new actualParameters(acList);
    }

    private void parseElseIf() throws Exception {
        if (currentIf == null) {
            throw new SyntacticException("Expected a if statement when declarated a elsif statement.");
        }

        Token elsif = this.next();
        if (elsif.getType() != TokenType.tok_elsif) {
            throw new SyntacticException("Expected 'elsif' when declarated a elsif statement.");
        }

        while (elsif.getType() == TokenType.tok_elsif) {
            expr e = this.parseExpression();
            if (!e.getType().name.equals("BOOLEAN")) {
                throw new TypeMismatchedException("Expected a boolean expression when declarated a elsif statement.");
            }

            Token then = this.next();
            if (then.getType() != TokenType.tok_then) {
                throw new SyntacticException("Expected 'then' when declarated a elsif statement.");
            }

            stmts s = this.parseStatements();

            currentIf.elseIfs.add(new ifStmt(e, s));

            elsif = this.next();
        }
        this.freeze = true;
    }

    private void parseElse() throws Exception {
        if (currentIf == null) {
            throw new SyntacticException("Expected a if statement when declarated a else statement.");
        }

        Token elseP = this.next();
        if (elseP.getType() != TokenType.tok_else) {
            throw new SyntacticException("Expected 'else' when declarated a else statement.");
        }

        stmts s = this.parseStatements();
        currentIf.elseBody = s;
    }
}
