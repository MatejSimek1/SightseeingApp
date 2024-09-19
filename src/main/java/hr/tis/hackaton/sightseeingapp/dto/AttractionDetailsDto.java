package hr.tis.hackaton.sightseeingapp.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AttractionDetailsDto {

    private String name;
    private String description;
    private String type;
    private BigDecimal averageRating;
    private List<ReviewDto> reviewDtoList = new ArrayList<>();

    public AttractionDetailsDto() {
    }

    public AttractionDetailsDto(String name, String description, String type, BigDecimal averageRating, List<ReviewDto> reviewDtoList) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.averageRating = averageRating;
        this.reviewDtoList = reviewDtoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public List<ReviewDto> getReviewDtoList() {
        return reviewDtoList;
    }

    public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
        this.reviewDtoList = reviewDtoList;
    }
}
