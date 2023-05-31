package ast;

public class fieldList implements ast {
    public identifierList ids;
    public typeAST type;

    public fieldList() {
        this(null, null);
    }

    public fieldList(identifierList ids) {
        this(ids, null);
    }

    public fieldList(identifierList ids, typeAST type) {
        this.ids = ids;
        this.type = type;
    }

    public void convert() {
        if (ids == null)
            return;
        ids.convert();
    }
}
