package whang.travel.domain.accommodation.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccommodationMapper {

    void save(Accommodation accommodation);

    void update(@Param("accommoId") Long accommoId, @Param("updateAccommo") Accommodation updateAccommo);

    Optional<Accommodation> findAccommoById(Long accommoId);

    List<Accommodation> findAccommoList(AccommoSearchCond accommoSearchCond);

    void delete(Long accommoId);
}
