package hr.tis.hackaton.sightseeingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "ATTRACTIONS")
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "URL_NAME")
    private String urlName;
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER) //mby dodat @JoinColumn
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;
    @Column(name = "TYPE")
    private String type;

    public Attraction(Long id, String name, String urlName, String description, Location location, String type) {
        this.id = id;
        this.name = name;
        this.urlName = urlName;
        this.description = description;
        this.location = location;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(urlName, that.urlName) && Objects.equals(description, that.description) && Objects.equals(location, that.location) && Objects.equals(type, that.type);
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urlName='" + urlName + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, urlName, description, location, type);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlName() {
        return urlName;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Attraction() {
    }
}

