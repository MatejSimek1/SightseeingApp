package hr.tis.hackaton.sightseeingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME")
    private String name;

    @JsonIgnore //kod testiranja vidjeti treba li @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attraction> attractions = new ArrayList<>();

    public Location(long id, String name, List<Attraction> attractions) {
        this.id = id;
        this.name = name;
        this.attractions = attractions;
    }

    public Location() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && Objects.equals(name, location.name) && Objects.equals(attractions, location.attractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attractions);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attractions=" + attractions +
                '}';
    }
}
