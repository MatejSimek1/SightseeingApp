package hr.tis.hackaton.sightseeingapp.controller;

import hr.tis.hackaton.sightseeingapp.dto.AttractionDetailsDto;
import hr.tis.hackaton.sightseeingapp.dto.AttractionDto;
import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;
import hr.tis.hackaton.sightseeingapp.model.Attraction;
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
    private final AttractionService attractionService;

    public AttractionController(LocationService locationService,
                                ReviewService reviewService,
                                AttractionService attractionService
    ) {
        this.locationService = locationService;
        this.reviewService = reviewService;
        this.attractionService = attractionService;
    }

    @GetMapping("/{location}")
    public ResponseEntity<LocationDto> getAttraction(
            @PathVariable String location
    ) {
        LocationDto locationDto = locationService.getLocation(location);
        if (locationDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<Void> saveReview(@Valid @RequestBody ReviewDto reviewDto) {

        reviewService.saveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/{location}/{attractionUrlName}")
    public ResponseEntity<AttractionDetailsDto> getAttraction(
            @PathVariable String location,
            @PathVariable String attractionUrlName,
            @RequestParam(defaultValue = "false") boolean excludeReviews,
            @RequestParam(required = false, defaultValue = "1") Integer reviewsFrom,
            @RequestParam(required = false, defaultValue = "3") Integer reviewsTo
    ) {
        AttractionDetailsDto attractionDetailsDto = reviewService.getAttractionDetails(location, attractionUrlName, excludeReviews, reviewsFrom, reviewsTo);

        return new ResponseEntity<>(attractionDetailsDto, HttpStatus.OK);
    }

    //ovo je samo proof of concept za settanje urlName-a
    @PostMapping("/save")
    public ResponseEntity<Void> saveAttraction(@RequestBody Attraction attraction) {
        attractionService.saveAttraction(attraction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}