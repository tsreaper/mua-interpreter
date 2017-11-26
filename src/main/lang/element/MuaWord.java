package lang.element;

public class MuaWord extends MuaElement {
    private String value;

    public MuaWord(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
