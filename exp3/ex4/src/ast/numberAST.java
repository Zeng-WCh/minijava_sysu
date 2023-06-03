package ast;

/**
 * the number AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class numberAST implements ast {
    public Integer value;

    public numberAST() {
        this(0);
    }

    public numberAST(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
