package ast;

/**
 * const declaration
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class constDec implements ast {
    /**
     * the name of the constant
     */
    public String name;
    /**
     * the type of the constant;
     * 0 for boolean, 1 for integer
     */
    public int type;
    /**
     * the value of the constant
     */
    public expr value;

    /**
     * constructor for constDec
     * 
     * @param name, the name of the constant
     * @param value, the value of the constant
     */
    public constDec(String name, expr value) {
        this(name, value, 0);
    }

    /**
     * constructor for constDec
     * 
     * @param name, the name of the constant
     * @param value, the value of the constant
     * @param type, the type of the constant
     */
    public constDec(String name, expr value, int type) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * make it in right order
     */
    public void convert() {
        if (value != null)
            value.convert();
    }
}
