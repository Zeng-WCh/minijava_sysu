package ast;

public class procedureBody implements ast {
    public declarations declarations;
    public stmts stmts;
    public String name;

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
        this.declarations = declarations;
        this.stmts = stmts;
        this.name = name;
    }

    public void convert() {
        if (declarations != null) {
            declarations.convert();
        }
        if (stmts != null) {
            stmts.convert();
        }
    }
}
