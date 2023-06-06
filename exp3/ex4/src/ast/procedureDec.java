package ast;
import flowchart.Procedure;
import flowchart.PrimitiveStatement;
import flowchart.IfStatement;
import flowchart.WhileStatement;

/**
 * procedure declaration AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class procedureDec implements ast {
    public procedureHead head;
    public procedureBody body;

    public procedureDec() {
        this(null, null);
    }

    public procedureDec(procedureHead head) {
        this(head, null);
    }

    public procedureDec(procedureHead head, procedureBody body) {
        this.head = head;
        this.body = body;
    }

    public void convert() {
        if (head != null)
            head.convert();
        if (body != null)
            body.convert();
    }

    public void eval(Procedure proc) {
        for (stmt s : this.body.stmts.statements) {
            Object val = s.eval();
            if (val instanceof PrimitiveStatement) {
                proc.add((PrimitiveStatement) val);
            }
            else if (val instanceof WhileStatement) {
                proc.add((WhileStatement) val);
            }
            else if (val instanceof IfStatement) {
                proc.add((IfStatement) val);
            }
        }
    }
}
