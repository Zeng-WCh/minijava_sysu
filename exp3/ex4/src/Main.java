import java.io.*;
import exceptions.*;
import ast.*;
import flowchart.*;

public class Main {
    public static void main(String[] argv) throws Exception {
        if (argv.length == 0) {
            System.out.println("Usage: java Main <inputfile>");
            System.exit(1);
        }

        for (int i = 0; i < argv.length; ++i) {
            OberonScanner scanner = new OberonScanner(new FileReader(argv[i]));
            Parser parser = new Parser(scanner);
            System.out.println(argv[i] + ":");
            try {
                parser.parse();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.err.println(String.format("Exception occured when parse near by %s", scanner.getPos()));
                System.err.println(e.getMessage());
                System.exit(1);
            }
            System.out.println("Parse successfully");


        }
    }
}
