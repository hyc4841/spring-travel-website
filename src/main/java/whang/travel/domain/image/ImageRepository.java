package whang.travel.domain.image;

import whang.travel.domain.image.form.ImageSaveForm;

import java.util.List;

public interface ImageRepository {

    void saveImage(ImageSaveForm saveImage);

    List<Image> findImages(String entityType, Long entityId);

    Image findImage(Long imageId);
}
