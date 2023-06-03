package ast;

/**
 * procedure head AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class procedureHead implements ast {
    public String name;
    public formalParameters fp;

    public procedureHead() {
        this("", null);
    }

    public procedureHead(String name) {
        this(name, null);
    }

    public procedureHead(String name, formalParameters fp) {
        this.name = name;
        this.fp = fp;
    }

    public void convert() {
        if (fp != null)
            fp.convert();
    }
}
