package ast;

/**
 * factor AST node in the oberon-0 AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class factorAST implements ast {
    /**
     * the identifier 
     */
    public String identifier;
    public selectorAST sel;

    /**
     * the number
     */
    public numberAST number;
    
    /**
     * the expression, used in (expr)
     */
    public expr exp;

    /**
     * if the factor is negated
     */
    public boolean negated;

    /**
     * Used to do type checking
     */
    public typeAST typeGenerate;

    /**
     * if the factor is constant
     */
    public boolean isConstant;

    public factorAST(String identifier, selectorAST sel, boolean negated) {
        this.identifier = identifier;
        this.sel = sel;
        this.number = null;
        this.exp = null;
        this.negated = negated;
        this.typeGenerate = null;
        this.isConstant = false;
    }

    public factorAST(numberAST number, boolean negated) {
        this.identifier = null;
        this.sel = null;
        this.number = number;
        this.exp = null;
        this.negated = negated;
        this.typeGenerate = null;
        this.isConstant = false;
    }

    public factorAST(expr exp, boolean negated) {
        this.identifier = null;
        this.sel = null;
        this.number = null;
        this.exp = exp;
        this.negated = negated;
        this.typeGenerate = null;
        this.isConstant = false;
    }

    public boolean isVar() {
        if (this.isConstant)
            return false;
        return identifier != null || (exp != null && exp.isVar());
    }

    public void convert() {
        if (sel != null)
            sel.convert();
        if (exp != null)
            exp.convert();
    }

    @Override
    public String toString() {
        if (identifier != null) {
            return (negated ? "-" : "") + identifier + (sel != null ? sel.toString() : "");
        } else if (number != null) {
            return (negated ? "-" : "") + number.toString();
        } else if (exp != null) {
            return (negated ? "~" : "") + "(" + exp.toString() + ")";
        } else {
            return "";
        }
    }

    public typeAST getType() {
        if (number != null)
            return new typeAST("INTEGER");
        if (exp != null) 
            return exp.getType();
        if (identifier != null) {
            if (this.typeGenerate == null)
            // needs to be judges until the symbol table is ready
                return new typeAST("UNKNOWN");
        }
        return this.typeGenerate;
    }
}
