package ast;

/**
 * moduleBlock class
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class moduleBlock implements ast {
    public String name;
    public declarations declarations;
    public stmts stmts;

    public moduleBlock() {
        this("", null, null);
    }

    public moduleBlock(String name) {
        this(name, null, null);
    }

    public moduleBlock(String name, declarations declarations) {
        this(name, declarations, null);
    }
    
    public moduleBlock(String name, declarations declarations, stmts stmts) {
        this.name = name;
        this.declarations = declarations;
        this.stmts = stmts;
    }

    public void convert() {
        if (declarations != null) {
            declarations.convert();
        }
        if (stmts != null) {
            stmts.convert();
        }
    }
}
