package ast;

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
