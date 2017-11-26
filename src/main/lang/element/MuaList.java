package lang.element;

import java.util.ArrayList;

public class MuaList extends MuaElement {
    private ArrayList<MuaElement> value;

    public MuaList() {
        value = new ArrayList<>();
    }

    @Override
    public String getValue() {
        StringBuilder ret = new StringBuilder();
        for (MuaElement element : value) {
            if (ret.length() > 0) {
                ret.append(' ');
            }
            if (element instanceof MuaWord) {
                // Special output for word
                ret.append('"');
                ret.append(element.getValue());
            } else {
                ret.append(element.getValue());
            }
        }
        return '[' + ret.toString() + ']';
    }

    public int size() {
        return value.size();
    }

    public MuaElement get(int index) {
        return value.get(index);
    }

    public void appendElement(MuaElement element) {
        value.add(element);
    }
}
