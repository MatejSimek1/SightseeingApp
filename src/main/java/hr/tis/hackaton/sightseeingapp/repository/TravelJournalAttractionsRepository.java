package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.TravelJournalAttractions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelJournalAttractionsRepository extends JpaRepository<TravelJournalAttractions, Long> {

}
