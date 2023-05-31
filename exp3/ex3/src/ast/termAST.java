package ast;

import java.util.ArrayList;
import java.util.Collections;

public class termAST implements ast {
    public factorAST lhs;
    public ArrayList<String> op;
    public ArrayList<factorAST> rhs;

    public termAST() {
        this.lhs = null;
        this.op = new ArrayList<>();
        this.rhs = new ArrayList<>();
    }

    public termAST(factorAST lhs) {
        this.lhs = lhs;
        this.op = new ArrayList<>();
        this.rhs = new ArrayList<>();
    }

    public termAST(factorAST lhs, ArrayList<String> op, ArrayList<factorAST> rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }

    public boolean isVar() {
        if (!op.isEmpty() && !rhs.isEmpty()) {
            return false;
        }
        return lhs.isVar();
    }

    public void convert() {
        if (op == null || op.isEmpty())
            return;
        Collections.reverse(op);
        Collections.reverse(rhs);
    }

    @Override
    public String toString() {
        if (lhs == null)
            return "";
        if (rhs == null) {
            return lhs.toString();
        }
        StringBuilder strbd = new StringBuilder();
        strbd.append(lhs.toString());
        for (int i = 0; i < rhs.size(); ++i) {
            strbd.append(op.get(i));
            strbd.append(rhs.get(i));
        }
        return strbd.toString();
    }
}
