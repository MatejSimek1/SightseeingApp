package hr.tis.hackaton.sightseeingapp.controller;

import hr.tis.hackaton.sightseeingapp.model.TravelJournal;
import hr.tis.hackaton.sightseeingapp.service.TravelJournalService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-journal")
public class TravelJournalController {

    private final TravelJournalService travelJournalService;

    public TravelJournalController(TravelJournalService travelJournalService) {
        this.travelJournalService = travelJournalService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveTravelJournal(
            @PathVariable Long userId,
            @RequestBody TravelJournal travelJournal
    ){
        Long id = travelJournalService.createTravelJournal(travelJournal, userId);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/travel-journal/" + id);

        return ResponseEntity.status(201)
                .headers(responseHeaders)
                .body("Travel journal successfully created with id: " + id);
    }

    @PatchMapping("/{travelJournalId}")
    public ResponseEntity<?> updateTravelJournal(
            @PathVariable Long travelJournalId,
            @RequestBody TravelJournal travelJournal
    ){
        travelJournalService.updateTravelJournal(travelJournal, travelJournalId);

        return ResponseEntity.ok("Travel journal successfully updated");
    }

    @GetMapping("/{travelJournalId}")
    public ResponseEntity<TravelJournal> getTravelJournal(
            @PathVariable Long travelJournalId
    ){
        TravelJournal travelJournal = travelJournalService.getTravelJournalById(travelJournalId);

        return ResponseEntity.ok(travelJournal);
    }

}
