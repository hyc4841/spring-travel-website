package whang.travel.web.reservation.form;

public enum VisitType {
    WALK("도보"), VEHICLE("차량");
    
    private final String description;

    private VisitType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
