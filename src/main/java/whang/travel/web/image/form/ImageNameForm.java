package whang.travel.web.image.form;

import lombok.Data;

@Data
public class ImageNameForm {

    private String originalName;
    private String saveName;

    public ImageNameForm(String originalName, String saveName) {
        this.originalName = originalName;
        this.saveName = saveName;
    }
}
