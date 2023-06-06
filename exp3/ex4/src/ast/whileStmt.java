package ast;

import flowchart.IfStatement;
import flowchart.WhileStatement;
import flowchart.PrimitiveStatement;

/**
 * while statement AST
 * 
 * @author Weichao Zeng
 * @version 2.0 (Last updated: 2023/06/06)
 */
public class whileStmt implements ast {
    public expr condition;
    public stmts body;

    public whileStmt() {
        this(null, null);
    }

    public whileStmt(expr condition) {
        this(condition, null);
    }

    public whileStmt(expr condition, stmts body) {
        this.condition = condition;
        this.body = body;
    }

    public void convert() {
        if (condition != null)
            condition.convert();
        if (body != null)
            body.convert();
    }

    @Override
    public String toString() {
        return "whileStmt(" +condition.toString() + ")\n{\n" + (body != null ? body.toString()+"\n}" : "\n}");
    }

    /**
     * to eval the while state and return a CFG node
     * 
     * @return the eval result that can be used in CFG
     */
    public WhileStatement eval() {
        WhileStatement wstmt = new WhileStatement(condition.toString());

        for (stmt s : this.body.statements) {
            Object val = s.eval();

            if (val instanceof WhileStatement) {
                wstmt.getLoopBody().add((WhileStatement) val);
            }
            else if (val instanceof PrimitiveStatement) {
                wstmt.getLoopBody().add((PrimitiveStatement) val);
            }
            else if (val instanceof IfStatement) {
                wstmt.getLoopBody().add((IfStatement) val);
            }
        }
        return wstmt;
    }
}
