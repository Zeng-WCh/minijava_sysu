package ast;

/**
 * callStmt class
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class callStmt implements ast {
    /**
     * name of the procedure being called
     */
    public String name;
    /**
     * actual parameters
     */
    public actualParameters params;

    /**
     * constructor for callStmt
     */
    public callStmt() {
        this(null, null);
    }

    /**
     * constructor for callStmt
     * 
     * @param name, name of the procedure being called
     */
    public callStmt(String name) {
        this(name, null);
    }

    /**
     * constructor for callStmt
     * 
     * @param name,  name of the procedure being called
     * @param params, actual parameters
     */
    public callStmt(String name, actualParameters params) {
        this.name = name;
        this.params = params;
    }

    /**
     * make it in right order
     */
    public void convert() {
        if (params != null)
            params.convert();
    }

    /**
     * @return the string representation of callStmt
     */
    @Override
    public String toString() {
        return name + "(" + (params != null ? params.toString() : "") + ")";
    }
}
