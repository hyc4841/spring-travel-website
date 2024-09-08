package whang.travel.domain.image.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.image.Image;
import whang.travel.domain.image.form.ImageSaveForm;

import java.util.List;

@Mapper
public interface ImageMapper {

    void saveImage(ImageSaveForm saveImage);

    List<Image> findImages(@Param("entityType") String entityType, @Param("entityId") Long entityId);

    Image findImage(Long imageId);
}
