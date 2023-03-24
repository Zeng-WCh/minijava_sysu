public class numAst implements ast {
    int num;

    public numAst(int num) {
        this.num = num;
    }
    public numAst() {
        this.num = 0;
    }

    public int eval() {
        return this.num;
    }

    public String postFix() {
        return String.format("(%d)", this.num);
    }
}
