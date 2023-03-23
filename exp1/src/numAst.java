public class numAst implements ast {
    private final int val;

    public numAst() {
        this.val = 0;
    }

    public numAst(int val) {
        this.val = val;
    }

    public String toString() {
        return String.format("%d", this.val);
    }

    public void visit(int v) {
        for (int i = 0; i < v; ++i) {
            System.out.write('|');
        }
        System.out.print("-");
        System.out.print(this.val);
        System.out.println();
    }

    public int eval() {
        return this.val;
    }

    public String backTrace() {
        return String.format("(%d)", this.val);
    }
}
