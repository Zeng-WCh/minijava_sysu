package ast;

public class factorAST implements ast {
    public String identifier;
    public selectorAST sel;

    public numberAST number;
    
    public expr exp;

    public boolean negated;

    public factorAST(String identifier, selectorAST sel, boolean negated) {
        this.identifier = identifier;
        this.sel = sel;
        this.number = null;
        this.exp = null;
        this.negated = negated;
    }

    public factorAST(numberAST number, boolean negated) {
        this.identifier = null;
        this.sel = null;
        this.number = number;
        this.exp = null;
        this.negated = negated;
    }

    public factorAST(expr exp, boolean negated) {
        this.identifier = null;
        this.sel = null;
        this.number = null;
        this.exp = exp;
        this.negated = negated;
    }

    public boolean isVar() {
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
            return (negated ? "-" : "") + "(" + exp.toString() + ")";
        } else {
            return "";
        }
    }
}
