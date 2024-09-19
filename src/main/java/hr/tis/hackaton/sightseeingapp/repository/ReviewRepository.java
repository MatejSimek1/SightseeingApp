package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.attraction.location.name = :locationName AND r.attraction.urlName = :attractionUrlName")
    List<Review> findByLocationNameAndAttractionUrlName(String locationName, String attractionUrlName);

}
