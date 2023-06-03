package ast;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

/**
 * the procedure body AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class procedureBody implements ast {
    public declarations declarations;
    public stmts stmts;
    public String name;
    public ArrayList<callStmt> calls;
    public HashMap<callStmt, String> callsPos;

    public procedureBody() {
        this(null, null, "");
    }

    public procedureBody(declarations declarations) {
        this(declarations, null, "");
    }

    public procedureBody(declarations declarations, stmts stmts) {
        this(declarations, stmts, "");
    }

    public procedureBody(declarations declarations, stmts stmts, String name) {
        this(declarations, stmts, name, new ArrayList<>());
    }

    public procedureBody(declarations declarations, stmts stmts, String name, ArrayList<callStmt> calls) {
        this(declarations, stmts, name, calls, new HashMap<>());
    }

    public procedureBody(declarations declarations, stmts stmts, String name, ArrayList<callStmt> calls, HashMap<callStmt, String> callsPos) {
        this.declarations = declarations;
        this.stmts = stmts;
        this.name = name;
        this.calls = calls;
        this.callsPos = callsPos;
    }

    public void convert() {
        if (declarations != null) {
            declarations.convert();
        }
        if (stmts != null) {
            stmts.convert();
        }
        if (calls != null) {
            Collections.reverse(calls);
        }
    }
}
