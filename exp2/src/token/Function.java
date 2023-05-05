package token;

public class Function extends TokenBase {
    private String value;
    
    public Function(TokenType type, String value) {
        super(type);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
