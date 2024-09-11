package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.model.Attraction;
import hr.tis.hackaton.sightseeingapp.repository.AttractionRepositoryJpa;
import hr.tis.hackaton.sightseeingapp.service.AttractionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepositoryJpa attractionRepository;

    public AttractionServiceImpl(AttractionRepositoryJpa attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    @Transactional
    @Override
    public void saveAttraction(Attraction attraction) {
        attraction.setUrlName(Normalizer.normalize(attraction.getName(), Normalizer.Form.NFD));
        attractionRepository.save(attraction);
    }
}
