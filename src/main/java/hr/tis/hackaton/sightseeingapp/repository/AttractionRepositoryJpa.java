package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepositoryJpa extends JpaRepository<Attraction, Long> {

    @Query(value = "SELECT a from Attraction a where a.location.name = :name")
    List<Attraction> findByName(String name);

    @Query(value = "SELECT a from Attraction a where a.name = :attractionName and a.location.name = :locationName")
    Optional<Attraction> findByAttractionNameAndLocationName(String attractionName, String locationName);

}
