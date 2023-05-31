package ast;

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
}
