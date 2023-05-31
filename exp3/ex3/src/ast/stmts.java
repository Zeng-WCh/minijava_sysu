package ast;

import java.util.ArrayList;
import java.util.Collections;

public class stmts implements ast {
    public ArrayList<stmt> statements;

    public stmts() {
        this.statements = new ArrayList<>();
    }

    public stmts(ArrayList<stmt> statements) {
        this.statements = statements;
    }

    public void convert() {
        if (statements.isEmpty()) return;
        Collections.reverse(statements);
        for (stmt s : statements) {
            s.convert();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (stmt s : statements) {
            sb.append(s.toString()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
