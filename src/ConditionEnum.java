// Note : enum is available from JDK 5 onwards

public enum ConditionEnum {
    HOT("This is hot"),
    COLD("This is cold"),
    WARM("This is Warm");
    String text;

    private ConditionEnum(String text) {

        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
