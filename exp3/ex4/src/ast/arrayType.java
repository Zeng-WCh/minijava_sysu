package ast;

/**
 * array type ast 
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class arrayType implements ast {
    /**
     * expr is the size of the array
     */
    public expr expr;
    /**
     * type is the type of the array
     */
    public typeAST type;

    /**
     * constructor for arrayType
     */
    public arrayType() {
        this(null, null);
    }

    /**
     * constructor for arrayType
     * 
     * @param expr, the size of the array
     */
    public arrayType(expr expr) {
        this(expr, null);
    }

    /**
     * constructor for arrayType
     * 
     * @param expr, the size of the array
     * @param type, the type of the array
     */
    public arrayType(expr expr, typeAST type) {
        this.expr = expr;
        this.type = type;
    }

    /**
     * make the order of the arrayType ast correct
     */
    public void convert() {
        if (expr != null)
            expr.convert();
    }
}
