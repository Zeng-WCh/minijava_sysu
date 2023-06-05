package ast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * formal parameters AST, used when a procedure is declared
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class formalParameters implements ast {
    public ArrayList<fpSection> fpList;
    public ArrayList<fp> fps;

    public formalParameters() {
        this.fpList = new ArrayList<>();
        this.fps = new ArrayList<>();
    }

    public formalParameters(ArrayList<fpSection> fpList) {
        this.fpList = fpList;
        this.fps = new ArrayList<>();
    }

    public void convert() {
        if (fpList.isEmpty()) return;
        for (fpSection f : fpList) {
            identifierList list = f.identifierList;
            boolean isVar = f.isVar;
            typeAST type = f.type;
            for (String s : list.identifiers) {
                fps.add(new fp(s, isVar, type));
            }
        }
        Collections.reverse(fps);
    }

    public void trans() {
        if (fpList.isEmpty()) return;
        for (fpSection f : fpList) {
            identifierList list = f.identifierList;
            boolean isVar = f.isVar;
            typeAST type = f.type;
            for (String s : list.identifiers) {
                fps.add(new fp(s, isVar, type));
            }
        }
    }
}
