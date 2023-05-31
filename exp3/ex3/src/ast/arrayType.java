package ast;

public class arrayType implements ast {
    public expr expr;
    public typeAST type;

    public arrayType() {
        this(null, null);
    }

    public arrayType(expr expr) {
        this(expr, null);
    }

    public arrayType(expr expr, typeAST type) {
        this.expr = expr;
        this.type = type;
    }

    public void convert() {
        if (expr != null)
            expr.convert();
    }
}
