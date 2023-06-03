package ast;

/**
 * variable declaration
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class varDec implements ast {
    public identifierList idList;
    public typeAST type;

    public varDec() {
        this(null, null);
    }

    public varDec(identifierList idList) {
        this(idList, null);
    }

    public varDec(identifierList idList, typeAST type) {
        this.idList = idList;
        this.type = type;
    }

    public void convert() {
        if (idList != null) {
            idList.convert();
        }
    }
}
