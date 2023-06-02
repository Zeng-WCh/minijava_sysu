package ast;

/**
 * assignment statement
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class assignmentStmt implements ast {
    /**
     * the name of the variable
     */
    public String name;
    /**
     * the selector of the variable, used only if the variable is an array or a record
     */
    public selectorAST sel;
    /**
     * the value to be assigned to the variable
     */
    public expr value;

    /**
     * constructor for assignment statement
     */
    public assignmentStmt() {
        this(null, null, null);
    }

    /**
     * constructor for assignment statement
     * 
     * @param name, the name of the variable
     */
    public assignmentStmt(String name) {
        this(name, null, null);
    }

    /**
     * constructor for assignment statement
     * 
     * @param name, the name of the variable
     * @param sel,  the selector of the variable
     */
    public assignmentStmt(String name, selectorAST sel) {
        this(name, sel, null);
    }

    /**
     * constructor for assignment statement
     * 
     * @param name, the name of the variable
     * @param sel, the selector of the variable
     * @param value, the value to be assigned to the variable
     */
    public assignmentStmt(String name, selectorAST sel, expr value) {
        this.name = name;
        this.sel = sel;
        this.value = value;
    }

    /**
     * make the assignment statement in right order
     */
    public void convert() {
        if (sel != null)
            sel.convert();
        if (value != null)
            value.convert();
    }

    /**
     * @return the string representation of the assignment statement
     */
    @Override
    public String toString() {
        return String.format("%s%s := %s", name, sel == null ? "" : sel.toString(), value.toString());
    }
}
