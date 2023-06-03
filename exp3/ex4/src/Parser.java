import exceptions.*;
import java.io.IOException;

/**
 * the Oberon Parser Class
 */
public class Parser {
    /**
     * the scanner of the Oberon, return the TokenType by method yylex()
     */
    private OberonScanner scanner;

    /**
     * return the next token from the file
     * 
     * @return the next token in Token Form
     * @throws Exception, if IO error or Lex Error
     */
    private Token next() throws Exception {
        if (this.scanner == null) {
            return null;
        }
        return this.scanner.yylex();
    }

    public Parser(OberonScanner scanner) {
        this.scanner = scanner;
    }

    public void parse() throws Exception {

    }
}
