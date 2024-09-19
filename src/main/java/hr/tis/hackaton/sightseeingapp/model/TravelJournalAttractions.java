package hr.tis.hackaton.sightseeingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRAVEL_JOURNAL_DETAILS")
public class TravelJournalAttractions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "ATTRACTION")
    private String attraction;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "TRAVEL_JOURNAL_ID")
    private TravelJournal travelJournal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TravelJournal getTravelJournal() {
        return travelJournal;
    }

    public void setTravelJournal(TravelJournal travelJournal) {
        this.travelJournal = travelJournal;
    }
}
