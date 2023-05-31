package ast;

import java.util.ArrayList;
import java.util.Collections;

public class identifierList implements ast {
    public ArrayList<String> identifiers;

    public identifierList() {
        this.identifiers = new ArrayList<>();
    }

    public identifierList(ArrayList<String> identifiers) {
        this.identifiers = identifiers;
    }

    public int getSize() {
        return this.identifiers.size();
    }

    public void convert() {
        if (identifiers == null || identifiers.isEmpty())
            return;
        Collections.reverse(identifiers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : identifiers) {
            sb.append(s).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
