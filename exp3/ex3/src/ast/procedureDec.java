package ast;

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
