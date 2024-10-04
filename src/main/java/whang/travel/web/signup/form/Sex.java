package whang.travel.web.signup.form;

public enum Sex {
    MAN("남자"), WOMEN("여자");

    private final String description;

    Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
