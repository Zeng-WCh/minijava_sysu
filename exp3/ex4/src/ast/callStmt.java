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
     * formal parameters
     */
    public formalParameters fps;

    /**
     * constructor for callStmt
     */
    public callStmt() {
        this(null, null, null);
    }

    /**
     * constructor for callStmt
     * 
     * @param name, name of the procedure being called
     */
    public callStmt(String name) {
        this(name, null, null);
    }

    /**
     * constructor for callStmt
     * 
     * @param name,  name of the procedure being called
     * @param params, actual parameters
     * @param fps,    formal parameters
     */
    public callStmt(String name, actualParameters params, formalParameters fps) {
        this.name = name;
        this.params = params;
        this.fps = fps;
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
        // special case for built-in func
        if (name.equals("READ")) {
            return String.format("READ(VAR %s)", params != null ? params.toString() : "");
        }
        else if (name.equals("WRITE")) {
            return String.format("WRITE(%s)", params != null ? params.toString() : "");
        }
        else if (name.equals("WRITELN")) {
            return name;
        }
        return name + "(" + (params != null ? params.buildStr(fps) : "") + ")";
    }
}
