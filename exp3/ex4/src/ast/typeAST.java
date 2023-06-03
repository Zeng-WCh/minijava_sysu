package ast;

/**
 * type AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class typeAST implements ast {
    public String name;
    // type can be like arrayType or recordType
    public ast type;

    public typeAST() {
        this("", null);
    }

    public typeAST(String name) {
        this(name, null);
    }

    public typeAST(String name, ast type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == null)
            return name;
        return name + " " + type.toString();
    }
}