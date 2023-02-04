package md2html;

import java.io.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static void main(final String[] args) {
        try {
            String lineSeparator = System.lineSeparator();
            char lineSep = lineSeparator.charAt(0);
            final Scanner sc = new Scanner(args[0], "utf-8");
            try (final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), "utf-8")
            )) {
                StringBuilder s = new StringBuilder();
                StringBuilder parOrHead = new StringBuilder();
                while (sc.hasNextLine()) {
                    final String line = sc.nextLine();
                    if (!line.isEmpty()) {
                        parOrHead.append(line).append(lineSep);
                    }
                    if (line.isEmpty() && !parOrHead.isEmpty() || !sc.hasNextLine()) {
                        new ParseTree(parOrHead).el.toHTML(s);
                        out.write(s.toString());
                        out.newLine();
                        parOrHead.setLength(0);
                        s.setLength(0);
                    }
                }
            } finally {
                sc.close();
            }
        } catch (final IOException e) {
            System.out.println("Input/Output exception" + e.getMessage());
        }
    }

}