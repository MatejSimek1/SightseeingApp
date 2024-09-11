package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;
import hr.tis.hackaton.sightseeingapp.exception.NoAttractionFoundException;
import hr.tis.hackaton.sightseeingapp.mapper.ReviewMapper;
import hr.tis.hackaton.sightseeingapp.model.Attraction;
import hr.tis.hackaton.sightseeingapp.model.Review;
import hr.tis.hackaton.sightseeingapp.repository.AttractionRepositoryJpa;
import hr.tis.hackaton.sightseeingapp.repository.ReviewRepository;
import hr.tis.hackaton.sightseeingapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final AttractionRepositoryJpa attractionRepositoryJpa;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ReviewMapper reviewMapper,
                             AttractionRepositoryJpa attractionRepositoryJpa
    ) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.attractionRepositoryJpa = attractionRepositoryJpa;
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Attraction attraction = attractionRepositoryJpa.findByAttractionNameAndLocationName(reviewDto.getAttractionName(), reviewDto.getLocation());
        if(attraction == null) {
            throw new NoAttractionFoundException("Attraction not found");
        }
        Review review = new Review();
        review.setAttraction(attraction);
        review.setRating(reviewDto.getRating());
        review.setReviewText(reviewDto.getReviewText());
        review.setTimestamp(LocalDateTime.now());
        reviewRepository.save(review);

    }
}
