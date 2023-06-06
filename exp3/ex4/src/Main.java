import java.io.*;

import ast.moduleBlock;
import flowchart.Module;

public class Main {
    public static void main(String[] argv) throws Exception {
        if (argv.length == 0) {
            System.out.println("Usage: java Main <inputfile>");
            System.exit(1);
        }

        for (int i = 0; i < argv.length; ++i) {
            OberonScanner scanner = new OberonScanner(new FileReader(argv[i]));
            Parser parser = new Parser(scanner);
            System.out.printf(argv[i] + ": ");
            try {
                parser.parse();
            } catch (Exception e) {
                System.err.println(String.format("Exception occured when parse near by %s", scanner.getPos()));
                System.err.println(e.getMessage());
                System.exit(1);
            }
            System.out.println("Parse successfully");

            moduleBlock mb = parser.getResult();

            Module m = mb.eval();
            m.show();
        }
    }
}
