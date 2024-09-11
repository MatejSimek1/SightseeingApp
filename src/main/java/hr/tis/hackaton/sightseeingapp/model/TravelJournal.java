package hr.tis.hackaton.sightseeingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRAVEL_JOURNAL")
public class TravelJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "travelJournal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TravelJournalAttractions> attractions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TravelJournalAttractions> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<TravelJournalAttractions> attractions) {
        this.attractions = attractions;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
