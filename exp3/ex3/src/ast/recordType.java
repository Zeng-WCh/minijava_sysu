package ast;

import java.util.ArrayList;
import java.util.Collections;

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
