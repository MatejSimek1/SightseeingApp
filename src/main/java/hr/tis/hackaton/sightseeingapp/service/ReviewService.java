package hr.tis.hackaton.sightseeingapp.service;

import hr.tis.hackaton.sightseeingapp.dto.AttractionDetailsDto;
import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;

public interface ReviewService {

    public void saveReview(ReviewDto reviewDto);

    AttractionDetailsDto getAttractionDetails(String location, String attractionUrlName, boolean excludeReviews, Integer reviewsFrom, Integer reviewsTo);
}
