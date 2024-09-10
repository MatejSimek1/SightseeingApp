package hr.tis.hackaton.sightseeingapp.service;

import hr.tis.hackaton.sightseeingapp.dto.LocationDto;

public interface LocationService {
    LocationDto getLocation(String name);

}