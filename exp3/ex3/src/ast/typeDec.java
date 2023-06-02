package ast;

/**
 * type declaration AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class typeDec implements ast {
    public String name;
    public typeAST type;

    public typeDec(String name, typeAST type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("type %s = %s", name, type.toString());
    }
}
