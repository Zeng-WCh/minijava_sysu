package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/**
 * the declarations class
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class declarations implements ast {
    /**
     * list of all declarations
     */
    public ArrayList<constDec> constDecs;
    public ArrayList<typeDec> typeDecs;
    public ArrayList<varDec> varDecs;
    public ArrayList<procedureDec> procDecs;
    
    public HashMap<String, Integer> constant;
    public HashMap<String, Integer> types;
    public HashMap<String, Integer> vars;
    public HashMap<String, Integer> pros;

    public declarations() {
        this.constDecs = new ArrayList<>();
        this.typeDecs = new ArrayList<>();
        this.varDecs = new ArrayList<>();
        this.procDecs = new ArrayList<>();
        this.constant = new HashMap<>();
        this.types = new HashMap<>();
        this.vars = new HashMap<>();
        this.pros = new HashMap<>();
    }

    public declarations(ArrayList<constDec> constDecs, ArrayList<typeDec> typeDecs, ArrayList<varDec> varDecs, ArrayList<procedureDec> procDecs) {
        this.constDecs = constDecs;
        this.typeDecs = typeDecs;
        this.varDecs = varDecs;
        this.procDecs = procDecs;
        this.constant = new HashMap<>();
        this.types = new HashMap<>();
        this.vars = new HashMap<>();
        this.pros = new HashMap<>();
    }

    public void convert() {
        if(constDecs != null && !constDecs.isEmpty()) {
            Collections.reverse(constDecs);
            for (constDec cd : constDecs) {
                cd.convert();
            }
            for (int i = 0; i < constDecs.size(); ++i) {
                this.constant.put(constDecs.get(i).name, i);
            }
        }
        if(typeDecs != null && !typeDecs.isEmpty()) {
            Collections.reverse(typeDecs);

            for (int i = 0; i < typeDecs.size(); ++i) {
                types.put(typeDecs.get(i).name, i);
            }
        }
        if(varDecs != null && !varDecs.isEmpty()) {
            Collections.reverse(varDecs);
            
            for (int i = 0; i < varDecs.size(); ++i) {
                for (String name : varDecs.get(i).idList.identifiers) {
                    vars.put(name, i);
                }
            }
        }
        
        if(procDecs != null && !procDecs.isEmpty()) {
            Collections.reverse(procDecs);
            for (procedureDec pd : procDecs) {
                pd.convert();
            }

            for (int i = 0; i < procDecs.size(); ++i) {
                pros.put(procDecs.get(i).head.name, i);
            }
        }

        
    }
}
