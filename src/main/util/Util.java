package util;

import exception.file.OpenFileException;
import interpreter.Tokenizer;
import lang.element.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Util {
    private Util() {
    }

    static public ArrayList<String> splitString(String str) {
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(str.split("\\s+")));
        while (tmp.remove("")) ;
        return tmp;
    }

    static public String showNamespace(HashMap<String, MuaElement> wordMap) {
        StringBuilder ret = new StringBuilder();
        for (HashMap.Entry<String, MuaElement> entry : wordMap.entrySet()) {
            String key = entry.getKey();
            MuaElement value = entry.getValue();
            if (value instanceof MuaWord) {
                ret.append(String.format("%s\t\"%s\n", key, value.getValue()));
            } else {
                ret.append(String.format("%s\t%s\n", key, value.getValue()));
            }
        }
        return ret.toString();
    }

    static public void mnsWrite(String filename, HashMap<String, MuaElement> wordMap) throws OpenFileException {
        try {
            PrintWriter writer = new PrintWriter(filename + ".mns", "UTF-8");
            writer.write(showNamespace(wordMap));
            writer.close();
        } catch (IOException e) {
            throw new OpenFileException(filename);
        }
    }

    static public HashMap<String, MuaElement> mnsRead(String filename) throws OpenFileException {
        HashMap<String, MuaElement> wordMap = new HashMap<>();

        Tokenizer tokenizer = new Tokenizer(false);
        try {
            for (String line : Files.readAllLines(Paths.get(filename + ".mns"), Charset.forName("UTF-8"))) {
                String[] tokens = line.split("\\t");
                tokenizer.tokenize(tokens[1]);
                wordMap.put(tokens[0], tokenizer.getElement());
            }
        } catch (IOException e) {
            throw new OpenFileException(filename);
        }

        return wordMap;
    }
}
