package whang.travel.domain.image.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.image.Image;
import whang.travel.domain.image.ImageRepository;
import whang.travel.domain.image.form.ImageSaveForm;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisImageRepository implements ImageRepository {

    private final ImageMapper imageMapper;

    @Override
    public void saveImage(ImageSaveForm saveImage) {
        imageMapper.saveImage(saveImage);
    }

    @Override
    public List<Image> findImages(String entityType, Long entityId) {
        return imageMapper.findImages(entityType, entityId);
    }

    @Override
    public Image findImage(Long imageId) {
        return imageMapper.findImage(imageId);
    }
}
