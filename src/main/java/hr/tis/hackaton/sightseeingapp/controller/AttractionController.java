package hr.tis.hackaton.sightseeingapp.controller;

import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;
import hr.tis.hackaton.sightseeingapp.exception.NoAttractionFoundException;
import hr.tis.hackaton.sightseeingapp.service.AttractionService;
import hr.tis.hackaton.sightseeingapp.service.LocationService;
import hr.tis.hackaton.sightseeingapp.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final LocationService locationService;
    private final ReviewService reviewService;

    public AttractionController(LocationService locationService,
                                ReviewService reviewService
    ) {
        this.locationService = locationService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{location}")
    public ResponseEntity<LocationDto> getAttraction(@PathVariable String location) {
        LocationDto locationDto = locationService.getLocation(location);
        if (locationDto == null) {
            return new ResponseEntity<>(HttpStatus. NOT_FOUND);
        }
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<Void> saveReview(
            @Valid @RequestBody ReviewDto reviewDto
    ) {
        try{
            reviewService.saveReview(reviewDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if(e instanceof NoAttractionFoundException) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.status(500).build();
        }
    }


}