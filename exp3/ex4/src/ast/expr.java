package ast;

/**
 * the expression class
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class expr implements ast {
    /**
     * the left hand side of the expression
     */
    public simpleExpr lhs, rhs;
    /**
     * the operator of the expression
     */
    public String op;
    /**
     * the constant flag
     */
    public boolean isConstant;

    public expr() {
        this(null, "", null);
    }

    public expr(simpleExpr lhs) {
        this(lhs, "", null);
        this.isConstant = lhs.isConstant;
    }

    public expr(simpleExpr lhs, String op, simpleExpr rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
        this.isConstant = false;
    }

    /**
     * check if the expression is variable
     * 
     * @return true if the expression is variable, false otherwise
     */
    public boolean isVar() {
        if (isConstant) 
            return false;
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
