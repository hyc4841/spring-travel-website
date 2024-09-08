package whang.travel.domain.image;

import lombok.Data;

@Data
public class Image {

    private Long imageId;
    private String originalName;
    private String saveName;
    private String entityType;
    private Long entityId;

}
