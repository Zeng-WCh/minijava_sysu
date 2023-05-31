package ast;

public class constDec implements ast {
    public String name;
    // 0 for boolean, 1 for integer
    public int type;
    public expr value;

    public constDec(String name, expr value) {
        this(name, value, 0);
    }

    public constDec(String name, expr value, int type) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public void convert() {
        if (value != null)
            value.convert();
    }
}
