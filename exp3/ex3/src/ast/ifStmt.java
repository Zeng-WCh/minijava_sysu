package ast;

import java.util.ArrayList;
import java.util.Collections;

public class ifStmt implements ast {
    public expr condition;
    public stmts body;
    public ArrayList<ifStmt> elseIfs;
    public stmts elseBody;
    public boolean isElseIf;

    public ifStmt() {
        this.condition = null;
        this.body = null;
        this.elseIfs = new ArrayList<>();
        this.elseBody = null;
        this.isElseIf = false;
    }

    public ifStmt(expr condition, stmts body) {
        this.condition = condition;
        this.body = body;
        this.elseIfs = new ArrayList<>();
        this.elseBody = null;
        this.isElseIf = false;
    }

    public ifStmt(expr condition, stmts body, boolean isElseIf) {
        this.condition = condition;
        this.body = body;
        this.elseIfs = new ArrayList<>();
        this.elseBody = null;
        this.isElseIf = isElseIf;
    }

    public ifStmt(expr condition, stmts body, ArrayList<ifStmt> elseIfs, stmts elseBody) {
        this.condition = condition;
        this.body = body;
        this.elseIfs = elseIfs;
        this.elseBody = elseBody;
    }

    public void convert() {
        if (condition != null)
            condition.convert();
        if (body != null)
            body.convert();
        if (elseIfs != null && !elseIfs.isEmpty()) {
            Collections.reverse(elseIfs);
            for (ifStmt i : elseIfs) {
                i.convert();
            }
        }
        if (elseBody != null)
            elseBody.convert();
    }

    @Override
    public String toString() {
        if (this.isElseIf) {
            StringBuilder sb = new StringBuilder();
            sb.append("else if (");
            sb.append(condition.toString());
            sb.append(") {\n");
            sb.append(body.toString());
            sb.append("\n}\n");
            return sb.toString();
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("if (");
            sb.append(condition.toString());
            sb.append(") {\n");
            sb.append(body.toString());
            sb.append("\n}\n");
            if (elseIfs != null && !elseIfs.isEmpty()) {
                for (ifStmt i : elseIfs) {
                    sb.append(i.toString());
                }
            }
            if (elseBody != null) {
                sb.append("else {\n");
                sb.append(elseBody.toString());
                sb.append("\n}\n");
            }
            return sb.toString();
        }
    }
}
