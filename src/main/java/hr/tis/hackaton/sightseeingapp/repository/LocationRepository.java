package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByName(String name);

}
