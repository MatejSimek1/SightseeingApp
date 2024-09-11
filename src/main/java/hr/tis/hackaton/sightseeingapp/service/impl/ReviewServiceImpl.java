package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.dto.AttractionDetailsDto;
import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;
import hr.tis.hackaton.sightseeingapp.exception.AttractionNotFoundException;
import hr.tis.hackaton.sightseeingapp.mapper.ReviewMapper;
import hr.tis.hackaton.sightseeingapp.model.Attraction;
import hr.tis.hackaton.sightseeingapp.model.Review;
import hr.tis.hackaton.sightseeingapp.repository.AttractionRepositoryJpa;
import hr.tis.hackaton.sightseeingapp.repository.ReviewRepository;
import hr.tis.hackaton.sightseeingapp.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional
    @Override
    public void saveReview(ReviewDto reviewDto) {
        Attraction attraction = attractionRepositoryJpa.findByAttractionNameAndLocationName(reviewDto.getAttractionName(), reviewDto.getLocation()).orElseThrow(() -> new AttractionNotFoundException("Attraction not found"));

        Review review = new Review();
        review.setAttraction(attraction);
        review.setRating(reviewDto.getRating());
        review.setReviewText(reviewDto.getReviewText());
        review.setTimestamp(LocalDateTime.now());
        reviewRepository.save(review);

    }

    @Override
    public AttractionDetailsDto getAttractionDetails(String location, String attractionUrlName, boolean excludeReviews, Integer reviewsFrom, Integer reviewsTo) {
        List<Review> reviews = reviewRepository.findByLocationNameAndAttractionUrlName(location, attractionUrlName);

        if(reviews == null || reviews.isEmpty()) {
            throw new AttractionNotFoundException("Attraction not found");
        }

        AttractionDetailsDto attractionDetailsDto = new AttractionDetailsDto();
        if(!excludeReviews) {
            if(reviewsTo > reviews.size()) {
                reviewsTo = reviews.size();
            }
            reviews = reviews.subList(reviewsFrom - 1, reviewsTo);

            List<ReviewDto> reviewDtoList = reviews.stream().map(reviewMapper::toDto).toList();
            attractionDetailsDto.setReviewDtoList(reviewDtoList);
        }

        attractionDetailsDto.setName(reviews.getFirst().getAttraction().getName());
        attractionDetailsDto.setDescription(reviews.getFirst().getAttraction().getDescription());
        attractionDetailsDto.setType(reviews.getFirst().getAttraction().getType());
        attractionDetailsDto.setAverageRating(average(reviews.stream().map(Review::getRating).toList()));

        return attractionDetailsDto;

    }

    public static BigDecimal average(List<BigDecimal> values) {
        double sum = 0;
        for (BigDecimal val : values) {
            sum += val.doubleValue();
        }
        return new BigDecimal(sum / values.size());
    }
}
