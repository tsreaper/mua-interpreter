package util;

import exception.element.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    private Util() {
    }

    static public ArrayList<String> splitString(String str) {
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(str.split("\\s+")));
        while (tmp.remove("")) ;
        return tmp;
    }
}
