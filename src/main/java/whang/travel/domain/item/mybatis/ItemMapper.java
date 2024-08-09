package whang.travel.domain.item.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.item.Item;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(Item item);

    void update(@Param("itemId") Long itemId, @Param("updateParam") ItemUpdateForm updateParam);

    Optional<Item> findById(Long itemId);

    List<Item> findAll(ItemSearchCond itemSearch);
}
