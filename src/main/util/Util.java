package util;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    private Util() {
    }

    static public ArrayList<String> splitStringToArrayList(String str) {
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(str.split("\\s+")));
        while (tmp.remove("")) ;
        return tmp;
    }

    static public String[] splitStringToArray(String str) {
        ArrayList<String> tmp = splitStringToArrayList(str);
        return tmp.toArray(new String[tmp.size()]);
    }
}
