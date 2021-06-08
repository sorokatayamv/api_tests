package constant;

public enum TrelloConstant {

    ID("id"),
    NAME("name");

    String constantName;

    TrelloConstant(String constantName) {
        this.constantName = constantName;
    }

    public String getConstantName() {
        return constantName;
    }
    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

}
