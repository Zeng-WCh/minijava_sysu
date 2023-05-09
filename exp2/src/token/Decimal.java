package token;

/**
 * Decimal Token class.
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class Decimal extends Token {
    private String integerPart, fractionPart, exponentPart;

    public Decimal(String integerPart, String fractionPart, String exponentPart) {
        super(TokenType.tok_decimal);
        this.integerPart = integerPart;
        this.fractionPart = fractionPart;
        this.exponentPart = exponentPart;
    }

    public Decimal(String integerPart, String fractionPart) {
        super(TokenType.tok_decimal);
        this.integerPart = integerPart;
        this.fractionPart = fractionPart;
        this.exponentPart = "";
    }

    public Decimal(String integerPart) {
        super(TokenType.tok_decimal);
        this.integerPart = integerPart;
        this.fractionPart = "";
        this.exponentPart = "";
    }

    @Override
    public String toString() {
        if (this.fractionPart.equals("") && this.exponentPart.equals(""))
            return this.integerPart;
        if (this.exponentPart.equals(""))
            return this.integerPart + "." + this.fractionPart;
        if (this.fractionPart.equals(""))
            return this.integerPart + "e" + this.exponentPart;
        return this.integerPart + "." + this.fractionPart + "e" + this.exponentPart;
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}
