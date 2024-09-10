package hr.tis.hackaton.sightseeingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

public class LocationDto {
    private String location;
    private List<AttractionDto> attractions;

    public LocationDto(String location, List<AttractionDto> attractions) {
        this.location = location;
        this.attractions = attractions;
    }

    public LocationDto() {
    }

    @Override
    public String toString() {
        return "LocationDto{" +
                "location='" + location + '\'' +
                ", attractions=" + attractions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(location, that.location) && Objects.equals(attractions, that.attractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, attractions);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAttractions(List<AttractionDto> attractions) {
        this.attractions = attractions;
    }

    public String getLocation() {
        return location;
    }

    public List<AttractionDto> getAttractions() {
        return attractions;
    }
}
