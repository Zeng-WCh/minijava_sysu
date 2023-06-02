package ast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * record type AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class recordType implements ast {
    public ArrayList<fieldList> fieldLists;

    public recordType() {
        this.fieldLists = new ArrayList<>();
    }

    public recordType(ArrayList<fieldList> fieldLists) {
        this.fieldLists = fieldLists;
    }

    public void convert() {
        if (fieldLists == null || fieldLists.isEmpty())
            return;
        Collections.reverse(fieldLists);
    }
}
