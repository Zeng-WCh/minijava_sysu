public class opAst implements ast {
    private final char op;
    private final ast left, right;

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

    public String toString() {
        return String.format("%s %c %s", this.left.eval(), this.op, this.right.eval());
    }

    public void visit(int v) {
        for (int i = 0; i < v; ++i) {
            System.out.write('|');
        }
        System.out.print("-");
        System.out.print(this.op);
        System.out.print("\n|");
        this.left.visit(v + 1);
        this.right.visit(v + 1);
    }

    public int eval() {
        int l = this.left.eval();
        int r = this.right.eval();

        switch (this.op) {
            case '+':
                return l + r;
            case '-':
                return l - r;
            case '*':
                return l * r;
            case '/':
                return l / r;
        }
        return 0;
    }

    public String backTrace() {
        return String.format("%s%s%c", this.left.backTrace(), this.right.backTrace(), this.op);
    }
}
