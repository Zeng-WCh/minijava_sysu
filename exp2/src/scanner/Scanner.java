package scanner;

import exceptions.*;
import token.*;

/**
 * Scanner class for the Calculator
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/06)
 */
public class Scanner {
    private int lastChar;
    private final Buffer bf;

    /**
     * Use to determine whether the current - is a unary minus or a binary minus
     */
    private TokenType lastToken;


    /**
     * Constructor for Scanner class
     * 
     * @param bf, a buffer class used to store the input expression string
     */
    public Scanner(Buffer bf) {
        this.lastChar = ' ';
        this.bf = bf;
        this.lastToken = null;
    }

    /**
     * To read in the next character from the buffer
     */
    private void readIn() {
        this.lastChar = this.bf.next();
    }

    /**
     * Methods used to parse Integer part of a number, can be used in string like "123.456".
     * First it will return 123, then 456
     * 
     * @return Integer String that have been parsed
     */
    private String parseInteger() {
        StringBuilder integerPart = new StringBuilder();
        while (Character.isDigit(this.lastChar)) {
            integerPart.append((char) this.lastChar);
            readIn();
        }
        return integerPart.toString();
    }

    /**
     * The main method of Scanner class, used to get the next token from the buffer
     * 
     * @return the next token if buffer is not empty, else tok_eof
     * @throws LexicalException if Scanner can not read in the token successfully
     */
    public Token next() throws LexicalException {
        // EOF is important, so check it first
        if (this.lastChar == 0) {
            return new EOFToken();
        }

        // Skip all the white spaces
        while (Character.isWhitespace(this.lastChar)) {
            readIn();
        }

        // Parse the Decimal Token Type
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
            this.lastToken = TokenType.tok_decimal;
            return new Decimal(integerPart, fractionPart, exponentPart);
        }

        if (this.lastChar == '+') {
            readIn();
            this.lastToken = TokenType.tok_plus;
            return new Operator(TokenType.tok_plus, "+");
        }

        if (this.lastChar == '-') {
            readIn();
            if (this.lastToken == TokenType.tok_decimal || this.lastToken == TokenType.tok_rparen) {
                this.lastToken = TokenType.tok_minus;
                return new Operator(TokenType.tok_minus, "-");
            }
            this.lastToken = TokenType.tok_unary_minus;
            return new Operator(TokenType.tok_unary_minus, "-");
        }

        if (this.lastChar == '*') {
            readIn();
            this.lastToken = TokenType.tok_star;
            return new Operator(TokenType.tok_star, "*");
        }
        
        if (this.lastChar == '/') {
            readIn();
            this.lastToken = TokenType.tok_slash;
            return new Operator(TokenType.tok_slash, "/");
        }

        if (this.lastChar == '(') {
            readIn();
            this.lastToken = TokenType.tok_lparen;
            return new Operator(TokenType.tok_lparen, "(");
        }

        if (this.lastChar == ')') {
            readIn();
            this.lastToken = TokenType.tok_rparen;
            return new Operator(TokenType.tok_rparen, ")");
        }

        if (this.lastChar == ':') {
            readIn();
            this.lastToken = TokenType.tok_colon;
            return new Operator(TokenType.tok_colon, ":");
        }

        if (this.lastChar == '^') {
            readIn();
            this.lastToken = TokenType.tok_caret;
            return new Operator(TokenType.tok_caret, "^");
        }

        if (this.lastChar == ',') {
            readIn();
            this.lastToken = TokenType.tok_comma;
            return new Operator(TokenType.tok_comma, ",");
        }

        if (this.lastChar == '?') {
            readIn();
            this.lastToken = TokenType.tok_question;
            return new Operator(TokenType.tok_question, "?");
        }

        if (this.lastChar == '&') {
            readIn();
            this.lastToken = TokenType.tok_and;
            return new Operator(TokenType.tok_and, "&");
        }

        if (this.lastChar == '|') {
            readIn();
            this.lastToken = TokenType.tok_or;
            return new Operator(TokenType.tok_or, "|");
        }

        if (this.lastChar == '!') {
            readIn();
            this.lastToken = TokenType.tok_not;
            return new Operator(TokenType.tok_not, "!");
        }

        if (this.lastChar == '=') {
            readIn();
            this.lastToken = TokenType.tok_equal;
            return new Operator(TokenType.tok_equal, "=");
        }

        if (this.lastChar == '>') {
            readIn();
            if (this.lastChar == '=') {
                readIn();
                this.lastToken = TokenType.tok_greater_equal;
                return new Operator(TokenType.tok_greater_equal, ">=");
            }
            this.lastToken = TokenType.tok_greater;
            return new Operator(TokenType.tok_greater, ">");
        }

        if (this.lastChar == '<') {
            readIn();
            if (this.lastChar == '=') {
                readIn();
                this.lastToken = TokenType.tok_less_equal;
                return new Operator(TokenType.tok_less_equal, "<=");
            }
            else if (this.lastChar == '>') {
                readIn();
                this.lastToken = TokenType.tok_not_equal;
                return new Operator(TokenType.tok_not_equal, "<>");
            }
            this.lastToken = TokenType.tok_less;
            return new Operator(TokenType.tok_less, "<");
        }

        if (this.lastChar == 'm') {
            readIn();
            if (this.lastChar == 'i') {
                readIn();
                if (this.lastChar == 'n') {
                    readIn();
                    this.lastToken = TokenType.tok_min;
                    return new Function(TokenType.tok_min, "min");
                }
                else {
                    throw new IllegalIdentifierException();
                }
            }
            else if (this.lastChar == 'a') {
                readIn();
                if (this.lastChar == 'x') {
                    readIn();
                    this.lastToken = TokenType.tok_max;
                    return new Function(TokenType.tok_max, "max");
                }
                else {
                    throw new IllegalIdentifierException();
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
                        this.lastToken = TokenType.tok_true;
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
                            this.lastToken = TokenType.tok_false;
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
                    this.lastToken = TokenType.tok_sin;
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
                    this.lastToken = TokenType.tok_cos;
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
