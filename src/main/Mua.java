import exception.MuaException;
import interpretation.Interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Mua {
    public static void main(String[] args) {
        Scanner scanner;
        int lineNum = 0;

        if (args.length == 0) {
            Interpreter.setIsInteractive(true);

            // Code from keyboard
            scanner = new Scanner(System.in);

            System.out.println("Press Ctrl + C to exit");
            while (true) {
                // Print command line prompt
                if (Interpreter.finished()) {
                    System.out.print("(mua) ");
                } else {
                    System.out.print("  ... ");
                }

                // Read code input
                String line;
                try {
                    line = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    break;
                }
                lineNum++;

                // Interpret
                try {
                    Interpreter.interpret(line);
                } catch (MuaException e) {
                    System.out.println(e.getType() + " in line " + String.valueOf(lineNum) + ": " + e.getMessage());
                }
            }
        } else {
            Interpreter.setIsInteractive(false);

            // Code from file
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("No such file called `" + args[0] + "`.");
                return;
            }

            while (scanner.hasNextLine()) {
                // Read code input
                String line = scanner.nextLine();
                lineNum++;

                // Interpret
                try {
                    Interpreter.interpret(line);
                } catch (MuaException e) {
                    System.out.println(e.getType() + " in line " + String.valueOf(lineNum) + ": " + e.getMessage());
                    break;
                }
            }
        }
    }
}
