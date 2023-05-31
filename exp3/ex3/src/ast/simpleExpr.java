package ast;

import java.util.ArrayList;
import java.util.Collections;

public class simpleExpr implements ast {
    public String signed;
    public termAST lhs;
    public ArrayList<String> op;
    public ArrayList<termAST> rhs;

    public simpleExpr() {
        this.signed = null;
        this.lhs = null;
        this.op = new ArrayList<>();
        this.rhs = new ArrayList<>();
    }

    public simpleExpr(String signed, termAST lhs) {
        this.signed = signed;
        this.lhs = lhs;
        this.op = new ArrayList<>();
        this.rhs = new ArrayList<>();
    }

    public simpleExpr(String signed, termAST lhs, ArrayList<String> op, ArrayList<termAST> rhs) {
        this.signed = signed;
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

    @Override
    public String toString() {
        if (lhs == null)
            return "";
        if (rhs == null) {
            if (signed == null || signed == "") {
                return lhs.toString();
            }
            return signed + lhs.toString();
        }
        StringBuilder strbd = new StringBuilder();
        if (signed != null && signed != "")
            strbd.append(signed + lhs.toString());
        else{
            strbd.append(lhs.toString());
        }
        for (int i = 0; i < rhs.size(); ++i) {
            strbd.append(op.get(i));
            strbd.append(rhs.get(i).toString());
        }
        return strbd.toString();
    }

    public void convert() {
        if (op == null || op.isEmpty())
            return;
        Collections.reverse(op);
        Collections.reverse(rhs);
    }
}
