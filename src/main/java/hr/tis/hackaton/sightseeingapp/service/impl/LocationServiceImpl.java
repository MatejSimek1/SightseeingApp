package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.mapper.LocationMapper;
import hr.tis.hackaton.sightseeingapp.model.Location;
import hr.tis.hackaton.sightseeingapp.repository.AttractionRepositoryJpa;
import hr.tis.hackaton.sightseeingapp.repository.LocationRepository;
import hr.tis.hackaton.sightseeingapp.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final AttractionRepositoryJpa attractionRepositoryJpa;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(AttractionRepositoryJpa attractionRepositoryJpa,
                                LocationRepository locationRepository,
                                LocationMapper locationMapper

    ) {
        this.attractionRepositoryJpa = attractionRepositoryJpa;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationDto getLocation(String name) {
        Optional<Location> optionalLocation = locationRepository.findByName(name);
        return optionalLocation.map(locationMapper::toDto).orElse(null);
    }
}
