package whang.travel.domain.accommodation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommoRepository;


    @Override
    public Optional<Accommodation> findAccommoById(Long accommoId) {
        return accommoRepository.findAccommoById(accommoId);
    }

    @Override
    public List<Accommodation> findAccommoList(AccommoSearchCond accommoSearchCond) {
        return accommoRepository.findAccommoList(accommoSearchCond);
    }
}
