public class opAst implements ast {
    ast left, right;
    char op;

    public opAst() {
        this.op = 0;
        this.left = null;
        this.right = null;
    }

    public opAst(char op, ast left, ast right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public double eval() {
        double l = 0;
        if (this.left != null) {
            l = this.left.eval();
        }
        double r = 0;
        if (this.right != null) {
            r = this.right.eval();
        }
        switch (this.op) {
            case '+':
                return l + r;
            case '-':
                return l - r;
            case '*':
                return l * r;
            case '/':
                return l / r;
            default:
                return .0f;
        }
    }

    public String postFix() {
        // if (this.left == null || this.right == null) {
        //     return "Error";
        // }
        assert this.left != null && this.right != null;
        return String.format("%s%s%c", this.left.postFix(), this.right.postFix(), this.op);
    }
}
