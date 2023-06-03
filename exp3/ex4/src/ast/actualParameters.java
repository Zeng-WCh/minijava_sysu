package ast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * actual parameters AST, used when calling a procedure
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class actualParameters implements ast {
    /**
     * the actual parameters
     */
    public ArrayList<expr> exprs;

    /**
     * constructor for actualParameters
     */
    public actualParameters() {
        this.exprs = new ArrayList<>();
    }

    /**
     * constructor for actualParameters
     * 
     * @param exprs, the actual parameters
     */
    public actualParameters(ArrayList<expr> exprs) {
        this.exprs = exprs;
    }

    /**
     * Used to make the actual parameters in reverse order,
     * because we use a LR Parser, so the actual parameters
     * are in reverse order
     */
    public void convert() {
        if (exprs == null || exprs.isEmpty()) return;
        Collections.reverse(exprs);
    }

    /**
     * 
     * @return String representation of the actual parameters
     */
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

    // @Override
    // public void print(int dep) {
    //     for (int i = 0; i < dep; ++i) {
    //         System.out.print("  ");
    //     }
    //     System.out.printf("- actualParameters: %s\n", this.toString());
    // }
}
