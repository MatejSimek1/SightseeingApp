package hr.tis.hackaton.sightseeingapp.controller;

import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.service.AttractionService;
import hr.tis.hackaton.sightseeingapp.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final LocationService locationService;
    public AttractionController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{location}")
    public ResponseEntity<LocationDto> getAttraction(@PathVariable String location) {
        LocationDto locationDto = locationService.getLocation(location);
        if (locationDto == null) {
            return new ResponseEntity<>(HttpStatus. NOT_FOUND);
        }
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }


}