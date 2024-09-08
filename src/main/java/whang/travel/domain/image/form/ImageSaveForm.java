package whang.travel.domain.image.form;

import lombok.Data;

@Data
public class ImageSaveForm {

    private String originalName;
    private String saveName;
    private String entityType;
    private Long entityId;

    public ImageSaveForm(String originalName, String saveName, String entityType, Long entityId) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.entityType = entityType;
        this.entityId = entityId;
    }
}
