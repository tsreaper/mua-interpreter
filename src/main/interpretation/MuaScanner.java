package interpretation;

import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class MuaScanner {
    static private Scanner scanner = new Scanner(System.in);
    static private ArrayList<String> tokens = new ArrayList<>();

    private MuaScanner() {
    }

    static public String next() {
        String token;
        while (true) {
            if (tokens.size() > 0) {
                token = tokens.remove(0);
                if (!token.equals("\n")) {
                    break;
                }
            } else {
                read();
            }
        }
        return token;
    }

    static public String nextLine() {
        while (tokens.size() > 0 && tokens.get(0).equals("\n")) {
            tokens.remove(0);
        }
        if (tokens.size() == 0) {
            read();
        }

        StringBuilder ret = new StringBuilder();
        while (true) {
            String token = tokens.remove(0);
            if (token.equals("\n")) {
                break;
            } else {
                ret.append(token);
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    static public void clear() {
        scanner = new Scanner(System.in);
        tokens.clear();
    }

    static private void read() {
        String line = scanner.nextLine();
        if (line.length() > 0) {
            tokens.addAll(Util.splitStringToArrayList(line));
            tokens.add("\n");
        }
    }
}
