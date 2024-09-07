package whang.travel.domain.accommodation;

public enum ServiceList {
    조식("fa-solid fa-mug-saucer"),
    피트니스("fa-solid fa-dumbbell"),
    수영장("fa-solid fa-person-swimming"),
    샤워장("fa fa-shower"),
    와이파이("fa-solid fa-wifi"),
    레스토랑("fa-solid fa-utensils"),
    금연("fa-solid fa-ban-smoking"),
    TV("fa-solid fa-tv"),
    스파("fa-solid fa-spa"),
    바베큐("fa-solid fa-bacon");

    private String iconClass;

    ServiceList(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getIconClass() {
        return iconClass;
    }
}
