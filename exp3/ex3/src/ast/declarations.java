package ast;

import java.util.ArrayList;
import java.util.Collections;

public class declarations implements ast {
    public ArrayList<constDec> constDecs;
    public ArrayList<typeDec> typeDecs;
    public ArrayList<varDec> varDecs;
    public ArrayList<procedureDec> procDecs;

    public declarations() {
        this.constDecs = new ArrayList<>();
        this.typeDecs = new ArrayList<>();
        this.varDecs = new ArrayList<>();
        this.procDecs = new ArrayList<>();
    }

    public declarations(ArrayList<constDec> constDecs, ArrayList<typeDec> typeDecs, ArrayList<varDec> varDecs, ArrayList<procedureDec> procDecs) {
        this.constDecs = constDecs;
        this.typeDecs = typeDecs;
        this.varDecs = varDecs;
        this.procDecs = procDecs;
    }

    public void convert() {
        if(constDecs != null && !constDecs.isEmpty()) {
            Collections.reverse(constDecs);
            for (constDec cd : constDecs) {
                cd.convert();
            }
        }
        if(typeDecs != null && !typeDecs.isEmpty()) {
            Collections.reverse(typeDecs);
        }
        if(varDecs != null && !varDecs.isEmpty()) {
            Collections.reverse(varDecs);
            for (varDec vd : varDecs) {
                vd.convert();
            }
        }
        
        if(procDecs != null && !procDecs.isEmpty()) {
            Collections.reverse(procDecs);
            for (procedureDec pd : procDecs) {
                pd.convert();
            }
        }
    }
}
