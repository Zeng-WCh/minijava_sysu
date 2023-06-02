package ast;

/**
 * fpSection class
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class fpSection implements ast {
    public boolean isVar;
    public identifierList identifierList;
    public typeAST type;

    public fpSection() {
        this(false, null, null);
    }

    public fpSection(boolean isVar) {
        this(isVar, null, null);
    }

    public fpSection(boolean isVar, identifierList identifierList) {
        this(isVar, identifierList, null);
    }

    public fpSection(boolean isVar, identifierList identifierList, typeAST type) {
        this.isVar = isVar;
        this.identifierList = identifierList;
        this.type = type;
    }

    public int getSize() {
        return this.identifierList.getSize();
    }
}
