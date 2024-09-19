package hr.tis.hackaton.sightseeingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ATTRACTION_ID", referencedColumnName = "ID")
    private Attraction attraction;
    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;
    @Column(name = "RATING")
    private BigDecimal rating;
    @Column(name = "REVIEW_TEXT")
    private String reviewText;

    public Long getId() {
        return id;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(attraction, review.attraction) && Objects.equals(timestamp, review.timestamp) && Objects.equals(rating, review.rating) && Objects.equals(reviewText, review.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attraction, timestamp, rating, reviewText);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                //", attraction=" + attraction +
                ", timestamp=" + timestamp +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
