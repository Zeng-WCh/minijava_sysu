package scanner;

import exceptions.*;
import token.*;

/**
 * @author Weichao Zeng
 */
public class Scanner {
    private int lastChar;
    private final Buffer bf;

    public Scanner(Buffer bf) {
        this.lastChar = ' ';
        this.bf = bf;
    }

    private void readIn() {
        this.lastChar = this.bf.next();
    }

    private String parseInteger() {
        StringBuilder integerPart = new StringBuilder();
        while (Character.isDigit(this.lastChar)) {
            integerPart.append((char) this.lastChar);
            readIn();
        }
        return integerPart.toString();
    }

    public TokenBase next() throws LexicalException {
        if (this.lastChar == 0) {
            return null;
        }

        while (Character.isWhitespace(this.lastChar)) {
            readIn();
        }

        if (Character.isDigit(lastChar)) {
            String fractionPart = "";
            String exponentPart = "";
            String integerPart = this.parseInteger();
            if (this.lastChar == '.') {
                readIn();
                if (Character.isDigit(lastChar))
                    fractionPart = this.parseInteger();
                else
                    throw new IllegalDecimalException();
            }
            if (this.lastChar == 'e') {
                readIn();
                if (this.lastChar == '+' || this.lastChar == '-') {
                    exponentPart += (char) this.lastChar;
                    readIn();
                }
                if (Character.isDigit(lastChar))
                    exponentPart += this.parseInteger();
                else
                    throw new IllegalDecimalException();
            }
            return new Decimal(integerPart, fractionPart, exponentPart);
        }

        if (this.lastChar == '+') {
            readIn();
            return new Operator(TokenType.tok_add, "+");
        }

        if (this.lastChar == '-') {
            readIn();
            return new Operator(TokenType.tok_minus, "-");
        }

        if (this.lastChar == '*') {
            readIn();
            return new Operator(TokenType.tok_star, "*");
        }
        
        if (this.lastChar == '/') {
            readIn();
            return new Operator(TokenType.tok_slash, "/");
        }

        if (this.lastChar == '(') {
            readIn();
            return new Operator(TokenType.tok_lparen, "(");
        }

        if (this.lastChar == ')') {
            readIn();
            return new Operator(TokenType.tok_rparen, ")");
        }

        if (this.lastChar == ':') {
            readIn();
            return new Operator(TokenType.tok_colon, ":");
        }

        if (this.lastChar == '^') {
            readIn();
            return new Operator(TokenType.tok_caret, "^");
        }

        if (this.lastChar == ',') {
            readIn();
            return new Operator(TokenType.tok_comma, ",");
        }

        if (this.lastChar == '?') {
            readIn();
            return new Operator(TokenType.tok_question, "?");
        }

        if (this.lastChar == '&') {
            readIn();
            return new Operator(TokenType.tok_and, "&");
        }

        if (this.lastChar == '|') {
            readIn();
            return new Operator(TokenType.tok_or, "|");
        }

        if (this.lastChar == '!') {
            readIn();
            return new Operator(TokenType.tok_not, "!");
        }

        if (this.lastChar == '=') {
            readIn();
            return new Operator(TokenType.tok_equal, "=");
        }

        if (this.lastChar == '>') {
            readIn();
            if (this.lastChar == '=') {
                readIn();
                return new Operator(TokenType.tok_greater_equal, ">=");
            }
            return new Operator(TokenType.tok_greater, ">");
        }

        if (this.lastChar == '<') {
            readIn();
            if (this.lastChar == '=') {
                readIn();
                return new Operator(TokenType.tok_less_equal, "<=");
            }
            else if (this.lastChar == '>') {
                readIn();
                return new Operator(TokenType.tok_not_equal, "<>");
            }
            return new Operator(TokenType.tok_less, "<");
        }

        if (this.lastChar == 'm') {
            readIn();
            if (this.lastChar == 'i') {
                readIn();
                if (this.lastChar == 'n') {
                    readIn();
                    return new Function(TokenType.tok_min, "min");
                }
            }
            else if (this.lastChar == 'a') {
                readIn();
                if (this.lastChar == 'x') {
                    readIn();
                    return new Function(TokenType.tok_max, "max");
                }
            }
            else {
                throw new IllegalIdentifierException();
            }
        }

        if (this.lastChar == 't') {
            readIn();
            if (this.lastChar == 'r') {
                readIn();
                if (this.lastChar == 'u') {
                    readIn();
                    if (this.lastChar == 'e') {
                        readIn();
                        return new Bool(TokenType.tok_true, "true");
                    }
                    else {
                        throw new IllegalIdentifierException();
                    }
                }
                else {
                    throw new IllegalIdentifierException();
                }
            }
            else {
                throw new IllegalIdentifierException();
            }
        }

        if (this.lastChar == 'f') {
            readIn();
            if (this.lastChar == 'a') {
                readIn();
                if (this.lastChar == 'l') {
                    readIn();
                    if (this.lastChar == 's') {
                        readIn();
                        if (this.lastChar == 'e') {
                            readIn();
                            return new Bool(TokenType.tok_false, "false");
                        }
                        else {
                            throw new IllegalIdentifierException();
                        }
                    }
                    else {
                        throw new IllegalIdentifierException();
                    }
                }
                else {
                    throw new IllegalIdentifierException();
                }
            }
            else {
                throw new IllegalIdentifierException();
            }
        }

        if (this.lastChar == 's') {
            readIn();
            if (this.lastChar == 'i') {
                readIn();
                if (this.lastChar == 'n') {
                    readIn();
                    return new Function(TokenType.tok_sin, "sin");
                }
                else {
                    throw new IllegalIdentifierException();
                }
            }
            else {
                throw new IllegalIdentifierException();
            }
        }

        if (this.lastChar == 'c') {
            readIn();
            if (this.lastChar == 'o') {
                readIn();
                if (this.lastChar == 's') {
                    readIn();
                    return new Function(TokenType.tok_cos, "cos");
                }
                else {
                    throw new IllegalIdentifierException();
                }
            }
            else {
                throw new IllegalIdentifierException();
            }
        }

        throw new IllegalSymbolException();
    }
}
