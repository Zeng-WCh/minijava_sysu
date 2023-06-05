package ast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * term AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class termAST implements ast {
    public factorAST lhs;
    public ArrayList<String> op;
    public ArrayList<factorAST> rhs;
    public boolean isConstant;

    public termAST() {
        this.lhs = null;
        this.op = new ArrayList<>();
        this.rhs = new ArrayList<>();
        this.isConstant = false;
    }

    public termAST(factorAST lhs) {
        this.lhs = lhs;
        this.op = new ArrayList<>();
        this.rhs = new ArrayList<>();
        this.isConstant = lhs.isConstant;
    }

    public termAST(factorAST lhs, ArrayList<String> op, ArrayList<factorAST> rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;

        if (lhs == null && rhs == null) {
            this.isConstant = false;
        }
        else if (rhs == null) {
            this.isConstant = lhs.isConstant;
        }
        else {
            this.isConstant = lhs.isConstant;

            for (factorAST factor : rhs) {
                if (!factor.isConstant) {
                    this.isConstant = false;
                    break;
                }
            }
        }
    }

    public boolean isVar() {
        if (isConstant)
            return false;
        if (!op.isEmpty() || !rhs.isEmpty()) {
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

    public typeAST getType() {
        if (this.rhs == null || this.rhs.isEmpty()) {
            if (this.lhs == null)
                return null;

            return lhs.getType();
        }
        boolean isNumber = false;
        boolean isLogical = false;
        
        for (int i = 0; i < op.size(); ++i) {
            if (op.get(i).equals("&")) {
                isLogical = true;
            }
            else {
                isNumber = true;
            }
        }

        if (isNumber) {
            return new typeAST("INTEGER", null);
        }
        else if (isLogical) {
            return new typeAST("BOOLEAN", null);
        }
        else {
            return null;
        }
    }
}
