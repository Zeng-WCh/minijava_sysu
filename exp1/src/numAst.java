/**
 * the AST Node that represents a number
 */
public class numAst implements ast {
    double num;

    public numAst(double num) {
        this.num = num;
    }
    public numAst() {
        this.num = 0;
    }

    public double eval() {
        return this.num;
    }

    public String postFix() {
        return String.format("(%.2f)", this.num);
    }
}
