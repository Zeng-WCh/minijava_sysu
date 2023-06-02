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
        if (!op.isEmpty() || !rhs.isEmpty()) {
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

    public typeAST getType(){
        if (this.signed != null && this.signed != "") {
            return new typeAST("INTEGER");
        }

        boolean isLogical = false;
        boolean isNumber = false;

        if (this.op == null || this.op.isEmpty()) {
            return lhs.getType();
        }

        for (int i = 0; i < op.size(); ++i) {
            if (op.get(i).equals("OR")) {
                isLogical = true;
            }
            if (op.get(i).equals("+") || op.get(i).equals("-")) {
                isNumber = true;
            }
        }

        if (isNumber) {
            return new typeAST("INTEGER");
        } else if (isLogical) {
            return new typeAST("BOOLEAN");
        } else {
            return null;
        }
    }
}
