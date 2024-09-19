package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.Location;
import hr.tis.hackaton.sightseeingapp.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {


    @Query(value = "SELECT p FROM Picture p where p.attraction.urlName = :attractionURLName AND p.attraction.location.name = :locationName AND p.id = :id")
    Optional<Picture> findByAttractionNameAndLocation(String attractionURLName, String locationName, Long id);
}
