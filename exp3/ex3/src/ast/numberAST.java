package ast;

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
