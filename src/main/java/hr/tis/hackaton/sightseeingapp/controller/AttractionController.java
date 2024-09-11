package hr.tis.hackaton.sightseeingapp.controller;

import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;
import hr.tis.hackaton.sightseeingapp.exception.NoAttractionFoundException;
import hr.tis.hackaton.sightseeingapp.service.AttractionService;
import hr.tis.hackaton.sightseeingapp.service.LocationService;
import hr.tis.hackaton.sightseeingapp.service.PictureService;
import hr.tis.hackaton.sightseeingapp.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final LocationService locationService;
    private final ReviewService reviewService;
    private final PictureService pictureService;

    public AttractionController(LocationService locationService,
                                ReviewService reviewService, PictureService pictureService
    ) {
        this.locationService = locationService;
        this.reviewService = reviewService;
        this.pictureService = pictureService;
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
        return ResponseEntity.ok().build();

    }





    @GetMapping ("/{location}/{attractionURLname}/picture/{picture_id}")
    public ResponseEntity<?> savePicture(@PathVariable String location, @PathVariable String attractionURLname, @PathVariable Long picture_id)  {
        try {
            byte[] image = pictureService.getPictureByLocationAndAttraction(location,attractionURLname, picture_id);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


}