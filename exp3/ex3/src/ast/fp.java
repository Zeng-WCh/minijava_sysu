package ast;

public class fp implements ast {
    public String name;
    public boolean isVar;
    public typeAST type;

    public fp(String name, boolean isVar, typeAST type) {
        this.name = name;
        this.isVar = isVar;
        this.type = type;
    }
}
