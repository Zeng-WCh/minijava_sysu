package ast;

import java.util.ArrayList;
import java.util.Collections;

public class actualParameters implements ast {
    public ArrayList<expr> exprs;

    public actualParameters() {
        this.exprs = new ArrayList<>();
    }

    public actualParameters(ArrayList<expr> exprs) {
        this.exprs = exprs;
    }

    public void convert() {
        if (exprs == null || exprs.isEmpty()) return;
        Collections.reverse(exprs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (exprs != null && !exprs.isEmpty()) {
            for (expr e : exprs) {
                boolean isVar = e.isVar();
                if (isVar) {
                    sb.append("var ");
                }
                sb.append(e.toString());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
