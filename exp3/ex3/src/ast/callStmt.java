package ast;

public class callStmt implements ast {
    public String name;
    public actualParameters params;

    public callStmt() {
        this(null, null);
    }

    public callStmt(String name) {
        this(name, null);
    }

    public callStmt(String name, actualParameters params) {
        this.name = name;
        this.params = params;
    }

    public void convert() {
        if (params != null)
            params.convert();
    }

    @Override
    public String toString() {
        return name + "(" + (params != null ? params.toString() : "") + ")";
    }
}
