import java.io.*;
import exceptions.*;
import ast.*;

public class Main {
    public static void main(String[] argv) throws Exception {
        if (argv.length == 0) {
            System.out.println("Usage: java Main <inputfile>");
            System.exit(1);
        }

        for (int i = 0; i < argv.length; ++i) {
            OberonScanner scanner = new OberonScanner(new FileReader(argv[i]));

        }
    }
}
