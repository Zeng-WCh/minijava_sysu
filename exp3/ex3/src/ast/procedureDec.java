package ast;

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
}
