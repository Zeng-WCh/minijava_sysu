package ast;

public class assignmentStmt implements ast {
    public String name;
    public selectorAST sel;
    public expr value;

    public assignmentStmt() {
        this(null, null, null);
    }

    public assignmentStmt(String name) {
        this(name, null, null);
    }

    public assignmentStmt(String name, selectorAST sel) {
        this(name, sel, null);
    }

    public assignmentStmt(String name, selectorAST sel, expr value) {
        this.name = name;
        this.sel = sel;
        this.value = value;
    }

    public void convert() {
        if (sel != null)
            sel.convert();
        if (value != null)
            value.convert();
    }

    @Override
    public String toString() {
        return String.format("%s%s := %s", name, sel == null ? "" : sel.toString(), value.toString());
    }
}
