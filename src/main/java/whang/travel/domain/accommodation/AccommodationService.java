package whang.travel.domain.accommodation;

import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    public Optional<Accommodation> findAccommoById(Long accommoId);

    List<Accommodation> findAccommoList(AccommoSearchCond accommoSearchCond);

}
