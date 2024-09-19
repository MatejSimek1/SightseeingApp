package hr.tis.hackaton.sightseeingapp.service;

import hr.tis.hackaton.sightseeingapp.model.TravelJournal;

public interface TravelJournalService {

    public Long createTravelJournal(TravelJournal travelJournal, Long userId);

    public void updateTravelJournal(TravelJournal travelJournal, Long travelJournalId);

    TravelJournal getTravelJournalById(Long travelJournalId);
}
