package hr.tis.hackaton.sightseeingapp.service;

import hr.tis.hackaton.sightseeingapp.model.Attraction;
import jakarta.validation.Valid;

public interface AttractionService {

    void saveAttraction(Attraction attraction);
}
