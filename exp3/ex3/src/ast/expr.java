package ast;

public class expr implements ast {
    public simpleExpr lhs, rhs;
    public String op;

    public expr() {
        this(null, "", null);
    }

    public expr(simpleExpr lhs) {
        this(lhs, "", null);
    }

    public expr(simpleExpr lhs, String op, simpleExpr rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }

    public boolean isVar() {
        if (rhs != null && op != "") {
            return false;
        }
        return lhs.isVar();
    }

    @Override
    public String toString() {
        if (lhs == null) {
            return "";
        }
        if (op == null || op == "") {
            return lhs.toString();
        }
        return lhs.toString() + op + rhs.toString();
    }

    public void convert() {
        if (lhs != null) {
            lhs.convert();
        }
        if (rhs != null) {
            rhs.convert();
        }
    }

    public typeAST getType() {
        if (this.rhs == null) {
            if (this.lhs == null)
                return null;

            return lhs.getType();
        }
        return new typeAST("BOOLEAN", null);
    }
}
