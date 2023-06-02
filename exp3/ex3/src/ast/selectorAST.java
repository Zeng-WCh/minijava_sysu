package ast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * selector AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class selectorAST implements ast {
    public ArrayList<Object> selectors;
    // 0 for field, 1 for array
    public ArrayList<Integer> types;

    public selectorAST() {
        this.selectors = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    public void convert() {
        if (selectors.isEmpty()) return;
        Collections.reverse(selectors);
        Collections.reverse(types);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectors.size(); i++) {
            if (types.get(i) == 0) {
                sb.append(".").append(selectors.get(i));
            } else {
                sb.append("[").append(selectors.get(i)).append("]");
            }
        }
        return sb.toString();
    }
}
