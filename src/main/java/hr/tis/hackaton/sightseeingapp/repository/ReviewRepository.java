package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
