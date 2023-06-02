package ast;

public class factorAST implements ast {
    public String identifier;
    public selectorAST sel;

    public numberAST number;
    
    public expr exp;

    public boolean negated;

    public typeAST typeGenerate;

    public factorAST(String identifier, selectorAST sel, boolean negated) {
        this.identifier = identifier;
        this.sel = sel;
        this.number = null;
        this.exp = null;
        this.negated = negated;
        this.typeGenerate = null;
    }

    public factorAST(numberAST number, boolean negated) {
        this.identifier = null;
        this.sel = null;
        this.number = number;
        this.exp = null;
        this.negated = negated;
        this.typeGenerate = null;
    }

    public factorAST(expr exp, boolean negated) {
        this.identifier = null;
        this.sel = null;
        this.number = null;
        this.exp = exp;
        this.negated = negated;
        this.typeGenerate = null;
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
