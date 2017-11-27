import exception.MuaException;
import exception.operation.OperandNumberException;
import interpreter.Interpreter;
import interpreter.MuaScanner;
import lang.namespace.NamespaceManager;
import service.GlobalSettings;
import service.namespace.NamespaceService;
import service.scanner.ScannerService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Mua {
    static public void main(String[] args) {
        // Provide service
        NamespaceService.provide(new NamespaceManager());
        ScannerService.provide(new MuaScanner());

        // Code from keyboard or from file
        if (args.length == 0) {
            interactiveMode();
        } else {
            fileMode(args[0]);
        }
    }

    static private void interactiveMode() {
        GlobalSettings.interactive = true;

        Interpreter interpreter = new Interpreter();
        Scanner scanner = new Scanner(System.in);
        int lineNum = 0;

        System.out.println("Use `stop` operation or press Ctrl + C to exit");
        while (!interpreter.shouldStop()) {
            // Print command line prompt
            if (interpreter.finished()) {
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
                interpreter.interpret(line);
            } catch (MuaException e) {
                System.out.println(e.getType() + " in line " + String.valueOf(lineNum) + ": " + e.getMessage());
            }
        }
    }

    static private void fileMode(String filename) {
        GlobalSettings.interactive = false;

        Interpreter interpreter = new Interpreter();
        Scanner scanner;
        int lineNum = 0;

        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("No such file called `" + filename + "`.");
            return;
        }

        while (!interpreter.shouldStop() && scanner.hasNextLine()) {
            // Read code input
            String line = scanner.nextLine();
            lineNum++;

            // Interpret
            try {
                interpreter.interpret(line);
            } catch (MuaException e) {
                System.out.println(e.getType() + " in line " + String.valueOf(lineNum) + ": " + e.getMessage());
                return;
            }
        }

        // Force execute to finish
        try {
            interpreter.forceExecute();
        } catch (OperandNumberException e) {
            System.out.println(e.getType() + " in line " + String.valueOf(lineNum) + ": " + e.getMessage());
        }
    }
}
