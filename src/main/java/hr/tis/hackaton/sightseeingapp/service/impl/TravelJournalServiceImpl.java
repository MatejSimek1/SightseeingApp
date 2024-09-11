package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.exception.AttractionNotFoundException;
import hr.tis.hackaton.sightseeingapp.exception.TravelJournalNotFoundException;
import hr.tis.hackaton.sightseeingapp.exception.UserEntityNotFoundException;
import hr.tis.hackaton.sightseeingapp.model.TravelJournal;
import hr.tis.hackaton.sightseeingapp.model.UserEntity;
import hr.tis.hackaton.sightseeingapp.repository.AttractionRepositoryJpa;
import hr.tis.hackaton.sightseeingapp.repository.TravelJournalRepository;
import hr.tis.hackaton.sightseeingapp.repository.UserEntityRepository;
import hr.tis.hackaton.sightseeingapp.service.TravelJournalService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TravelJournalServiceImpl implements TravelJournalService {

    private final TravelJournalRepository travelJournalRepository;
    private final AttractionRepositoryJpa attractionRepositoryJpa;
    private final UserEntityRepository userEntityRepository;

    public TravelJournalServiceImpl(TravelJournalRepository travelJournalRepository,
                                    AttractionRepositoryJpa attractionRepositoryJpa,
                                    UserEntityRepository userEntityRepository
    ) {
        this.travelJournalRepository = travelJournalRepository;
        this.attractionRepositoryJpa = attractionRepositoryJpa;
        this.userEntityRepository = userEntityRepository;
    }

    @Transactional
    @Override
    public Long createTravelJournal(TravelJournal travelJournal, Long userId) {
        UserEntity userEntity = userEntityRepository.findById(userId).orElseThrow(() -> new UserEntityNotFoundException("User with id " + userId + " not found"));

        travelJournal.setUserEntity(userEntity);
        travelJournal.getAttractions().forEach(attraction -> {
            attractionRepositoryJpa
                    .findByAttractionNameAndLocationName(attraction.getAttraction(), attraction.getLocation())
                        .orElseThrow(() -> new AttractionNotFoundException("Attraction " + attraction.getAttraction() + " not found"));
                    attraction.setTravelJournal(travelJournal);
        });

        //Bacamo exception za attraction i hvata ga exception handler koji vraća 404 i
        // mislim da je to možda greška u 9. zadatku što se traži status 400 ako nešto nije nađeno

        TravelJournal savedTravelJournal = travelJournalRepository.save(travelJournal);

        return savedTravelJournal.getId();

    }

    @Transactional
    @Override
    public void updateTravelJournal(TravelJournal travelJournal, Long travelJournalId) {
        TravelJournal travelJournalToUpdate = travelJournalRepository.findById(travelJournalId).orElseThrow(() -> new TravelJournalNotFoundException("Travel journal with id " + travelJournalId + " not found"));

        travelJournal.getAttractions().forEach(attraction -> {
            attractionRepositoryJpa
                    .findByAttractionNameAndLocationName(attraction.getAttraction(), attraction.getLocation())
                    .orElseThrow(() -> new AttractionNotFoundException("Attraction " + attraction.getAttraction() + " not found"));
        });

        travelJournalToUpdate.setAttractions(travelJournal.getAttractions());

        travelJournalRepository.save(travelJournalToUpdate);
    }

    @Override
    public TravelJournal getTravelJournalById(Long travelJournalId) {
        return travelJournalRepository.findById(travelJournalId).orElseThrow(() -> new TravelJournalNotFoundException("Travel journal with id " + travelJournalId + " not found"));
    }
}
