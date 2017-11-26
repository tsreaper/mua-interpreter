package lang.element;

public class MuaBool extends MuaElement {
    private boolean value;

    public MuaBool(boolean value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }
}
