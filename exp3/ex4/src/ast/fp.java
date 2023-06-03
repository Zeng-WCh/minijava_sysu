package ast;

/**
 * formal parameters AST, but in a simpler form
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class fp implements ast {
    /**
     * name is the name of the formal parameter
     */
    public String name;
    /**
     * isVar is true if the formal parameter is a left value
     */
    public boolean isVar;
    /**
     * type is the type of the formal parameter
     */
    public typeAST type;

    public fp(String name, boolean isVar, typeAST type) {
        this.name = name;
        this.isVar = isVar;
        this.type = type;
    }
}
