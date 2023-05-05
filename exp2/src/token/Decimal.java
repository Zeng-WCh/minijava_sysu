package token;

public class Decimal extends TokenBase {
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
        return this.integerPart + "." + this.fractionPart + this.exponentPart;
    }
}
